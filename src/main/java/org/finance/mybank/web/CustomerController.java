package org.finance.mybank.web;

import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.services.CustomerService;
import org.finance.mybank.services.ValidationErrorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("customer")
public class CustomerController {

	private final CustomerService customerService;
	private final ValidationErrorService validationErrorService;

	public CustomerController(CustomerService customerService, ValidationErrorService validationErrorService) {
		this.customerService = customerService;
		this.validationErrorService = validationErrorService;
	}

	@PostMapping
	public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
		ResponseEntity<?> errorMap = validationErrorService.mapValidationService(bindingResult);
		if (errorMap != null)
			return errorMap;
		customerService.createCustomer(customerDTO);
		return ResponseEntity.ok().build();
	}
}
