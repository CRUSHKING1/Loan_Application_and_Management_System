package com.loanapp.loan.entity;

import com.loanapp.common.audit.Auditable;
import com.loanapp.common.enums.LoanStatus;
import com.loanapp.common.enums.LoanType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loan")
@Getter
@Setter
public class Loan extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    private Double loanAmount;

    private Integer tenureMonths;

    private Double interestRate;

    private Double emiAmount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;
}
