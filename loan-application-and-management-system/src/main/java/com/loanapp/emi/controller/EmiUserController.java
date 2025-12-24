package com.loanapp.emi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanapp.emi.dto.EmiPaymentRequestDto;
import com.loanapp.emi.dto.EmiResponseDto;
import com.loanapp.emi.service.EmiService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/user/emis")
public class EmiUserController {

    private final EmiService emiService;

    public EmiUserController(EmiService emiService) {
        this.emiService = emiService;
    }

    @PostMapping("/pay")
    public void payEmi(@RequestBody EmiPaymentRequestDto dto) {
        emiService.payEmi(dto.getEmiId());
    }

    @GetMapping("/loan/{loanId}")
    public List<EmiResponseDto> viewLoanEmis(@PathVariable Long loanId) {
        return emiService.getLoanEmis(loanId);
    }
}