package com.gamul.gamul.exception;

public class DuplicateBookmarkException extends RuntimeException {
    public DuplicateBookmarkException(){
        super();
    }

    public DuplicateBookmarkException(String message) {
        super(message);
    }

    public DuplicateBookmarkException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateBookmarkException(Throwable cause) {
        super(cause);
    }

    protected DuplicateBookmarkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
