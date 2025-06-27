package com.everton.cash_Control.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class CashRegister {

    @Id
    private Long id = 1L;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

}
