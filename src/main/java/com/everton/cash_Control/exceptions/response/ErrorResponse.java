package com.everton.cash_Control.exceptions.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timesTamp;
    private Integer status;
    private String message;
    private String path;

    public ErrorResponse(LocalDateTime timesTamp, String message, String path, Integer status ) {
        this.timesTamp = timesTamp;
        this.status = status;
        this.message = message;
        this.path = path;

    }
}
