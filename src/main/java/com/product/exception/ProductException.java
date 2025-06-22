package com.product.exception;

import org.springframework.web.server.ResponseStatusException;

public class ProductException extends ResponseStatusException {
    private final ErrorCode code;

    public ProductException(ErrorCode code, String message) {
        super(code.getStatus(), message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}