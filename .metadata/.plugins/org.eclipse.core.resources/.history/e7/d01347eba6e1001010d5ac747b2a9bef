package com.loanapp.emi.service;

import java.util.List;

import com.loanapp.emi.dto.EmiResponseDto;
import com.loanapp.loan.entity.Loan;

public interface EmiService {

    void generateEmiSchedule(Loan loan);

    void payEmi(Long emiId);

    List<EmiResponseDto> getLoanEmis(Long loanId);
}