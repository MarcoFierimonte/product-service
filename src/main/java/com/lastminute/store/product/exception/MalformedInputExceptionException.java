package com.lastminute.store.product.exception;

public class MalformedInputExceptionException extends RuntimeException {

	private static final long serialVersionUID = -2551140042923807763L;

	public MalformedInputExceptionException() {
	}

	public MalformedInputExceptionException(String message, Throwable cause) {
		super(message, cause);
	}

	public MalformedInputExceptionException(String message) {
		super(message);
	}

	public MalformedInputExceptionException(Throwable cause) {
		super(cause);
	}
}
