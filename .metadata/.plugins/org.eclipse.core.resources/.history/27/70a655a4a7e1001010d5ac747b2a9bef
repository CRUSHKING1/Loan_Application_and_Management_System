package com.loanapp.emi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanapp.emi.dto.EmiResponseDto;
import com.loanapp.emi.service.EmiService;

@RestController
@RequestMapping("/api/admin/emis")
public class EmiAdminController {

    private final EmiService emiService;

    public EmiAdminController(EmiService emiService) {
        this.emiService = emiService;
    }

    @GetMapping("/loan/{loanId}")
    public List<EmiResponseDto> getAllEmis(@PathVariable Long loanId) {
        return emiService.getLoanEmis(loanId);
    }
}
