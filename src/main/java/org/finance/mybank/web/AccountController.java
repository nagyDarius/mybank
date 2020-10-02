package org.finance.mybank.web;

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

	@PostMapping
	public void createAccountForCustomer(@RequestParam Long customerId) {
		accountService.createAccountForCustomer(customerId);
	}

	@PostMapping("transfer")
	public ResponseEntity<?> transferTo(@Valid @RequestBody PostingDTO posting, BindingResult bindingResult) {
		ResponseEntity<?> errorMap = validationErrorService.mapValidationService(bindingResult);
		if (errorMap != null)
			return errorMap;
		accountService.transfer(posting);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> listAccountsForCustomer(@RequestParam Long customerId){
		return ResponseEntity.ok(accountService.findByCustomerId(customerId));
	}
}
