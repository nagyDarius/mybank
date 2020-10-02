package org.finance.mybank.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.finance.mybank.dto.PostingDTO;
import org.finance.mybank.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("account")
public class AccountController {

	private final AccountService accountService;
	private final ValidationErrorService validationErrorService;

	public AccountController(AccountService accountService, ValidationErrorService validationErrorService) {
		this.accountService = accountService;
		this.validationErrorService = validationErrorService;
	}

	@Operation(summary = "Create an account with 0 balance for the customer with the given id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created account"),
			@ApiResponse(responseCode = "400", description = "Invalid customer id")
	})
	@PostMapping
	public void createAccountForCustomer(@RequestParam Long customerId) {
		accountService.createAccountForCustomer(customerId);
	}

	@Operation(summary = "Transfer an amount of money from one account to another")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Money was transferred"),
			@ApiResponse(responseCode = "400", description = "Could not transfer because an account was not found or the account from which the transfer is requested does not have enough balance")
	})
	@PostMapping("transfer")
	public ResponseEntity<?> transferTo(@Valid @RequestBody PostingDTO posting, BindingResult bindingResult) {
		ResponseEntity<?> errorMap = validationErrorService.mapValidationService(bindingResult);
		if (errorMap != null)
			return errorMap;
		accountService.transfer(posting);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	@Operation(summary = "List all accounts of the customer with the given id. Returns empty if the customer was not found")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The list is returned"),
	})
	public ResponseEntity<?> listAccountsForCustomer(@RequestParam Long customerId) {
		return ResponseEntity.ok(accountService.findByCustomerId(customerId));
	}
}
