package com.everton.cash_Control.controllers;

import com.everton.cash_Control.dtos.CashFlowResponseDto;
import com.everton.cash_Control.dtos.DailySummaryDTO;
import com.everton.cash_Control.entities.CashRegister;
import com.everton.cash_Control.services.CashRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cashRegister")
public class CashRegisterController {

    private final   CashRegisterService cashRegisterService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance() {
        return cashRegisterService.getBalance();
    }

    @GetMapping("/cash")
    @ResponseStatus(HttpStatus.OK)
    public CashFlowResponseDto getCashFlowByPeriodWithTotals(@RequestParam("start") LocalDate startDate,@RequestParam("end") LocalDate endDate) {
        return cashRegisterService.getCashFlowByPeriodWithTotals(startDate, endDate);
    }

    @GetMapping("/day")
    @ResponseStatus(HttpStatus.OK)
    public DailySummaryDTO getDalySummer(LocalDate date) {
        return cashRegisterService.getDalySummer(date);
    }

    public CashRegister getCashRegister() {
        return cashRegisterService.getCashRegister();
    }

}
