package org.finance.mybank.exception.handlers;

import org.finance.mybank.exception.InvalidDateException;
import org.finance.mybank.exception.InvalidQueryParamException;
import org.finance.mybank.exception.NotEnoughBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.finance.mybank.exception.handlers.ApiResponseMessage.apiResponseMessage;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
	@ExceptionHandler({InvalidDateException.class, InvalidQueryParamException.class, NotEnoughBalanceException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<?> handleBadRequest(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.contentType(MediaType.APPLICATION_JSON)
				.body(apiResponseMessage(e.getMessage()));
	}
}
