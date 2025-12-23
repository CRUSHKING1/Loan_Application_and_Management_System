package com.loanapp.kyc.entity;

import com.loanapp.common.audit.Auditable;
import com.loanapp.common.enums.KycStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kyc")
@Getter
@Setter
public class Kyc extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 10)
    private String panNumber;

    @Column(nullable = false, length = 4)
    private String aadhaarLast4;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KycStatus status;

    
}