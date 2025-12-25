package com.loanapp.emi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.loanapp.common.enums.EmiStatus;
import com.loanapp.emi.entity.Emi;

public interface EmiRepository extends JpaRepository<Emi, Long> {

    List<Emi> findByLoanId(Long loanId);

    long countByLoanIdAndStatus(Long loanId, EmiStatus status);
}
