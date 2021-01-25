package com.lastminute.store.product.exception;

public class ReadFileException extends RuntimeException {

	private static final long serialVersionUID = -2551140042923807763L;

	public ReadFileException() {
	}

	public ReadFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReadFileException(String message) {
		super(message);
	}

	public ReadFileException(Throwable cause) {
		super(cause);
	}
}
