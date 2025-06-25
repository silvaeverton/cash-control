package com.everton.cash_Control.dtos;

import com.everton.cash_Control.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class CashFlowDto {
    private LocalDate date;
    private String type; // "ENTRY" ou "EXIT"
    private String description;
    private BigDecimal value;
    private PaymentMethod paymentMethod;
}
