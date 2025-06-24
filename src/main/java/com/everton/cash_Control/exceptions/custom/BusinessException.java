package com.everton.cash_Control.exceptions.custom;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
     private  final Integer status;

    public BusinessException(final String message, final Integer status) {
        super(message);
        this.status = status;
    }
}
