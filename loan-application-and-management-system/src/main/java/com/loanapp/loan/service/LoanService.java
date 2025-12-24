package com.loanapp.loan.service;



import com.loanapp.common.enums.LoanStatus;
import com.loanapp.loan.dto.LoanApplyRequestDto;
import com.loanapp.loan.dto.LoanApprovalRequestDto;
import com.loanapp.loan.dto.LoanResponseDto;

import java.util.List;

public interface LoanService {

    LoanResponseDto applyLoan(Long userId, LoanApplyRequestDto request);

    LoanResponseDto approveOrRejectLoan(Long loanId, LoanApprovalRequestDto request);

    LoanResponseDto activateLoan(Long loanId);

    LoanResponseDto closeLoan(Long loanId);

    List<LoanResponseDto> getLoansByUser(Long userId);

    List<LoanResponseDto> getLoansByUserAndStatus(Long userId, LoanStatus status);

    List<LoanResponseDto> getAllLoans();

    List<LoanResponseDto> getLoansByStatus(LoanStatus status);
}