package org.finance.mybank.exception.handlers;

import org.finance.mybank.exception.MyInvalidDateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.finance.mybank.exception.handlers.ApiResponseMessage.apiResponseMessage;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
	@ExceptionHandler({MyInvalidDateException.class})
	protected ResponseEntity handleBadRequest(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.contentType(MediaType.APPLICATION_JSON)
				.body(apiResponseMessage(e.getMessage()));
	}
}
