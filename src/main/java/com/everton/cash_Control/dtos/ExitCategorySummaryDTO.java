package com.everton.cash_Control.dtos;

import com.everton.cash_Control.enums.ExitCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExitCategorySummaryDTO {
    private ExitCategory exitCategory;
    private BigDecimal amount;
    private String description;




}
