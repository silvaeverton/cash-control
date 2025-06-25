package com.everton.cash_Control.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DailySummaryDTO {
    private BigDecimal totalIncomer;
    private BigDecimal totalExpense;
    private BigDecimal totalBalance;
}
