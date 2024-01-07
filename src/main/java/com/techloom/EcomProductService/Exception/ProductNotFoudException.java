package com.techloom.EcomProductService.Exception;

public class ProductNotFoudException extends Exception {

    public ProductNotFoudException() {
    }

    public ProductNotFoudException(String message) {
        super(message);
    }

    public ProductNotFoudException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoudException(Throwable cause) {
        super(cause);
    }

    public ProductNotFoudException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
