package com.mymemory.exceptions;

public class WriterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public WriterException() {
        super();
    }

    public WriterException(String message) {
        super(message);
    }

    public WriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriterException(Throwable cause) {
        super(cause);
    }

    protected WriterException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
