package org.finance.mybank.exception;

public class InvalidQueryParamException extends RuntimeException {
	public InvalidQueryParamException(String message) {
		super(message);
	}
}
