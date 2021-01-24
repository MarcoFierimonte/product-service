package com.lastminute.store.product.exception;

public class ProductAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -2551140042923807763L;

	public ProductAlreadyExistException() {
	}

	public ProductAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductAlreadyExistException(String message) {
		super(message);
	}

	public ProductAlreadyExistException(Throwable cause) {
		super(cause);
	}
}
