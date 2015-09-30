package com.hsypower.epct.exception;

public class AccessDeniedException extends HsypowerException {

	private static final long serialVersionUID = -6925933860392925970L;

	public AccessDeniedException() {
		super();
	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException(Throwable cause) {
		super(cause);
	}

	
}
