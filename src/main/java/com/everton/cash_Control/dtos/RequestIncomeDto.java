package com.everton.cash_Control.dtos;

import com.everton.cash_Control.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RequestIncomeDto {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private PaymentMethod paymentMethod;
    private Long userId;
}
