package com.everton.cash_Control.services;

import com.everton.cash_Control.dtos.CashFlowResponseDto;
import com.everton.cash_Control.dtos.DailySummaryDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public interface CashRegisterService {

    public BigDecimal getBalance();
    public void updateBalance(BigDecimal newBalance);
    public List<CashFlowResponseDto> getCashFlowByPeriodWithTotals(LocalDate startDate, LocalDate endDate);
    public DailySummaryDTO getDalySummer(LocalDate date);

}
