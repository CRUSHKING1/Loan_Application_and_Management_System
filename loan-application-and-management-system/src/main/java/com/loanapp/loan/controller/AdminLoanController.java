package com.loanapp.loan.controller;



import com.loanapp.common.enums.LoanStatus;
import com.loanapp.loan.dto.LoanApprovalRequestDto;
import com.loanapp.loan.dto.LoanResponseDto;
import com.loanapp.loan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/loans")
public class AdminLoanController {

    private final LoanService loanService;

    public AdminLoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/view-all")
    public ResponseEntity<List<LoanResponseDto>> viewAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/view-approved")
    public ResponseEntity<List<LoanResponseDto>> viewApprovedLoans() {
        return ResponseEntity.ok(loanService.getLoansByStatus(LoanStatus.APPROVED));
    }

    @PostMapping("/{loanId}/activate")
    public ResponseEntity<LoanResponseDto> activateLoan(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.activateLoan(loanId));
    }

    @PostMapping("/{loanId}/reject")
    public ResponseEntity<LoanResponseDto> rejectLoan(@PathVariable Long loanId,
                                                      @RequestBody LoanApprovalRequestDto request) {
        request.setApproved(false);
        return ResponseEntity.ok(loanService.approveOrRejectLoan(loanId, request));
    }

    @PostMapping("/{loanId}/close")
    public ResponseEntity<LoanResponseDto> closeLoan(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.closeLoan(loanId));
    }
}