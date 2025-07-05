package com.everton.cash_Control.services;

import com.everton.cash_Control.dtos.CashFlowResponseDto;
import com.everton.cash_Control.dtos.DailySummaryDTO;
import com.everton.cash_Control.entities.CashRegister;

import java.math.BigDecimal;
import java.time.LocalDate;


public interface CashRegisterService {

    public BigDecimal getBalance();
    public CashFlowResponseDto getCashFlowByPeriodWithTotals(LocalDate startDate, LocalDate endDate);
    public CashRegister getCashRegister();
    public DailySummaryDTO getDalySummer(LocalDate date);

}
