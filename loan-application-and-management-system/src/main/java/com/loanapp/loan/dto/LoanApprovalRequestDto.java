package com.loanapp.loan.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanApprovalRequestDto {
    private boolean approved;
    private String remarks; // optional
}
