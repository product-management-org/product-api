package com.product.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND),
    PRODUCT_OUT_OF_STOCK(HttpStatus.BAD_REQUEST),
    INVALID_PRODUCT_ID(HttpStatus.BAD_REQUEST);

    private final HttpStatus status;

    ErrorCode(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}