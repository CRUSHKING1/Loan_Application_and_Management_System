package com.loanapp.loan.service;



import com.loanapp.common.enums.LoanStatus;
import com.loanapp.loan.dto.*;
import com.loanapp.loan.entity.Loan;
import com.loanapp.loan.exception.LoanException;
import com.loanapp.loan.repository.LoanRepository;
import com.loanapp.loan.service.LoanService;
import com.loanapp.loan.util.EmiCalculator;
import com.loanapp.loan.util.LoanRules;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public LoanResponseDto applyLoan(Long userId, LoanApplyRequestDto req) {
        LoanRules.validateAmount(req);
        LoanRules.validateTenure(req);

        if (loanRepository.existsByUserIdAndLoanTypeAndStatus(
                userId, req.getLoanType(), LoanStatus.ACTIVE)) {
            throw new LoanException("Active loan of same type exists");
        }

        double emi = EmiCalculator.calculate(
                req.getLoanAmount(),
                req.getInterestRate(),
                req.getTenureMonths());

        Loan loan = new Loan();
        loan.setUserId(userId);
        loan.setLoanType(req.getLoanType());
        loan.setLoanAmount(req.getLoanAmount());
        loan.setTenureMonths(req.getTenureMonths());
        loan.setInterestRate(req.getInterestRate());
        loan.setEmiAmount(emi);
        loan.setStatus(LoanStatus.APPLIED);

        loanRepository.save(loan);
        return mapToDto(loan);
    }

    @Override
    public LoanResponseDto approveOrRejectLoan(Long loanId, LoanApprovalRequestDto req) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found"));

        if (req.isApproved()) loan.setStatus(LoanStatus.ACTIVE);
        else loan.setStatus(LoanStatus.REJECTED);

        loanRepository.save(loan);
        return mapToDto(loan);
    }

    @Override
    public LoanResponseDto activateLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found"));
        loan.setStatus(LoanStatus.ACTIVE);
        loanRepository.save(loan);
        return mapToDto(loan);
    }

    @Override
    public LoanResponseDto closeLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found"));
        loan.setStatus(LoanStatus.CLOSED);
        loanRepository.save(loan);
        return mapToDto(loan);
    }

    @Override
    public List<LoanResponseDto> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId).stream()
                .map(this::mapToDto).toList();
    }

    @Override
    public List<LoanResponseDto> getLoansByUserAndStatus(Long userId, LoanStatus status) {
        return loanRepository.findByUserIdAndStatus(userId, status).stream()
                .map(this::mapToDto).toList();
    }

    @Override
    public List<LoanResponseDto> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::mapToDto).toList();
    }

    @Override
    public List<LoanResponseDto> getLoansByStatus(LoanStatus status) {
        return loanRepository.findByStatus(status).stream()
                .map(this::mapToDto).toList();
    }

    private LoanResponseDto mapToDto(Loan loan) {
        LoanResponseDto dto = new LoanResponseDto();
        dto.setLoanId(loan.getId());
        dto.setUserId(loan.getUserId());
        dto.setLoanType(loan.getLoanType());
        dto.setLoanAmount(loan.getLoanAmount());
        dto.setTenureMonths(loan.getTenureMonths());
        dto.setInterestRate(loan.getInterestRate());
        dto.setEmiAmount(loan.getEmiAmount());
        dto.setStatus(loan.getStatus());
        return dto;
    }
}