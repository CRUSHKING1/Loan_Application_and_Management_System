package com.loanapp.emi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loanapp.common.enums.EmiStatus;
import com.loanapp.common.enums.LoanStatus;
import com.loanapp.emi.dto.EmiResponseDto;
import com.loanapp.emi.entity.Emi;
import com.loanapp.emi.exception.EmiException;
import com.loanapp.emi.exception.EmiNotFoundException;
import com.loanapp.emi.repository.EmiRepository;
import com.loanapp.emi.util.PenaltyCalculator;
import com.loanapp.loan.entity.Loan;
import com.loanapp.loan.repository.LoanRepository;

@Service
public class EmiServiceImpl implements EmiService {

    private final EmiRepository emiRepository;
    private final LoanRepository loanRepository;

    public EmiServiceImpl(
            EmiRepository emiRepository,
            LoanRepository loanRepository) {

        this.emiRepository = emiRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public void generateEmiSchedule(Loan loan) {

        for (int i = 1; i <= loan.getTenureMonths(); i++) {

        	Emi emi = new Emi();
        	emi.setLoanId(loan.getId());
        	emi.setEmiNumber(i);

        	// Convert double to BigDecimal to avoid precision issues
        	emi.setEmiAmount(BigDecimal.valueOf(loan.getEmiAmount()));
        	emi.setTotalPayable(BigDecimal.valueOf(loan.getEmiAmount()));

        	emi.setDueDate(LocalDate.now().plusMonths(i));
        	emi.setStatus(EmiStatus.PENDING);

            emiRepository.save(emi);
        }
    }

    @Override
    public void payEmi(Long emiId) {

        Emi emi = emiRepository.findById(emiId)
                .orElseThrow(() -> new EmiNotFoundException("EMI not found"));

        if (emi.getStatus() == EmiStatus.PAID) {
            throw new EmiException("EMI already paid");
        }

        BigDecimal penalty = BigDecimal.ZERO;

        if (LocalDate.now().isAfter(emi.getDueDate())) {

            long daysLate = ChronoUnit.DAYS.between(
                    emi.getDueDate(), LocalDate.now());

            penalty = PenaltyCalculator.calculate(
                    emi.getEmiAmount(), daysLate);

            emi.setStatus(EmiStatus.OVERDUE);
        }

        emi.setPenaltyAmount(penalty);
        emi.setTotalPayable(emi.getEmiAmount().add(penalty));
        emi.setPaidDate(LocalDate.now());
        emi.setStatus(EmiStatus.PAID);

        emiRepository.save(emi);

        closeLoanIfCompleted(emi.getLoanId());
    }

    private void closeLoanIfCompleted(Long loanId) {

        long pending = emiRepository.countByLoanIdAndStatus(
                loanId, EmiStatus.PENDING);

        long overdue = emiRepository.countByLoanIdAndStatus(
                loanId, EmiStatus.OVERDUE);

        if (pending == 0 && overdue == 0) {
            Loan loan = loanRepository.findById(loanId).get();
            loan.setStatus(LoanStatus.CLOSED);
            loanRepository.save(loan);
        }
    }

    @Override
    public List<EmiResponseDto> getLoanEmis(Long loanId) {

        return emiRepository.findByLoanId(loanId)
                .stream()
                .map(this::map)
                .toList();
    }

    private EmiResponseDto map(Emi emi) {
        EmiResponseDto dto = new EmiResponseDto();
        dto.setEmiId(emi.getId());
        dto.setEmiAmount(emi.getEmiAmount());
        dto.setPenaltyAmount(emi.getPenaltyAmount());
        dto.setTotalPayable(emi.getTotalPayable());
        dto.setDueDate(emi.getDueDate());
        dto.setStatus(emi.getStatus());
        return dto;
    }

	
}