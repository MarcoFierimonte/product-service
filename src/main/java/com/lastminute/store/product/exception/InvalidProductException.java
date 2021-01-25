package com.lastminute.store.product.exception;

public class InvalidProductException extends RuntimeException {

	private static final long serialVersionUID = -2551140042923807763L;

	public InvalidProductException() {
	}

	public InvalidProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidProductException(String message) {
		super(message);
	}

	public InvalidProductException(Throwable cause) {
		super(cause);
	}
}
