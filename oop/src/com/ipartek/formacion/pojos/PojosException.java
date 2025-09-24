package com.ipartek.formacion.pojos;

public class PojosException extends RuntimeException {

	public PojosException() {
		super();
	}

	public PojosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PojosException(String message, Throwable cause) {
		super(message, cause);
	}

	public PojosException(String message) {
		super(message);
	}

	public PojosException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 2535900440002758654L;

}
