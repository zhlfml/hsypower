package com.hsypower.epct.exception;

public class HsypowerException extends RuntimeException {

	private static final long serialVersionUID = -5625808981593340437L;

	public HsypowerException() {
		super();
	}

	public HsypowerException(String message, Throwable cause) {
		super(message, cause);
	}

	public HsypowerException(String message) {
		super(message);
	}

	public HsypowerException(Throwable cause) {
		super(cause);
	}

}
