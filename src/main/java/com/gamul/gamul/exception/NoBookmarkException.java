package com.gamul.gamul.exception;

public class NoBookmarkException extends RuntimeException {
    public NoBookmarkException(){
        super();
    }

    public NoBookmarkException(String message) {
        super(message);
    }

    public NoBookmarkException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBookmarkException(Throwable cause) {
        super(cause);
    }

    protected NoBookmarkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
