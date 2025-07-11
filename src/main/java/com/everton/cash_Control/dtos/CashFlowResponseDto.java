package com.everton.cash_Control.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CashFlowResponseDto {
    private List<CashFlowDto> cashFlowList;
    private BigDecimal totalEntries;
    private BigDecimal totalExits;
    private BigDecimal balance;
}
