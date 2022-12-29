package com.boutique.api.commons.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {

    private Date timestamp;
    private String exception;
    private String message;
    private String cause;

}
