package com.lastminute.store.product.exception;

public class NoReceiptNumberAvailableException extends RuntimeException {

	private static final long serialVersionUID = -2551140042923807763L;

	public NoReceiptNumberAvailableException() {
	}

	public NoReceiptNumberAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoReceiptNumberAvailableException(String message) {
		super(message);
	}

	public NoReceiptNumberAvailableException(Throwable cause) {
		super(cause);
	}
}
