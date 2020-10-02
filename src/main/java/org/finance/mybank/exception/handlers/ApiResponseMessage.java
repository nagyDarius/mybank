package org.finance.mybank.exception.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseMessage {

	private String message;

	public ApiResponseMessage() {

	}

	public ApiResponseMessage(String message) {
		this.message = message;
	}

	public static ApiResponseMessage apiResponseMessage(String message) {
		return new ApiResponseMessage(message);
	}

	public String getMessage() {
		return message;
	}
}
