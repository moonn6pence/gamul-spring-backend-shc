package com.gamul.gamul.exception;

public class NoSuchMarketException extends RuntimeException{
    public NoSuchMarketException(){
        super();
    }

    public NoSuchMarketException(String message) {
        super(message);
    }

    public NoSuchMarketException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchMarketException(Throwable cause) {
        super(cause);
    }

    protected NoSuchMarketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
