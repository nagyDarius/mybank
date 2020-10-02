package org.finance.mybank.exception;

import static org.finance.mybank.util.Constants.DATE_FORMAT;

public class InvalidDateException extends RuntimeException {
	public InvalidDateException(String source) {
		super(String.format("%s does not match required date format %s", source, DATE_FORMAT));
	}
}
