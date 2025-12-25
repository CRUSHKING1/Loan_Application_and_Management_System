package com.loanapp.loan.controller;



import com.loanapp.common.enums.LoanStatus;
import com.loanapp.loan.dto.LoanApplyRequestDto;
import com.loanapp.loan.dto.LoanResponseDto;
import com.loanapp.loan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/loans")
public class UserLoanController {

    private final LoanService loanService;

    public UserLoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<LoanResponseDto> applyLoan(@RequestParam Long userId,
                                                     @RequestBody LoanApplyRequestDto request) {
        return ResponseEntity.ok(loanService.applyLoan(userId, request));
    }

    @GetMapping("/view")
    public ResponseEntity<List<LoanResponseDto>> viewAllLoans(@RequestParam Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUser(userId));
    }

    @GetMapping("/view/active")
    public ResponseEntity<List<LoanResponseDto>> viewActiveLoans(@RequestParam Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUserAndStatus(userId, LoanStatus.ACTIVE));
    }

    @GetMapping("/view/closed")
    public ResponseEntity<List<LoanResponseDto>> viewClosedLoans(@RequestParam Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUserAndStatus(userId, LoanStatus.CLOSED));
    }
}