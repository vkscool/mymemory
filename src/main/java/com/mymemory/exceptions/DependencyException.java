package com.mymemory.exceptions;

public class DependencyException extends Exception{

private static final long serialVersionUID = 1L;
	
    public DependencyException() {
        super();
    }

    public DependencyException(String message) {
        super(message);
    }

    public DependencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DependencyException(Throwable cause) {
        super(cause);
    }

    protected DependencyException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}
