package org.finance.mybank.web;

import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping
	public ResponseEntity<?> queryByLastName(@RequestParam String lastName,
											 @RequestParam(required = false) String sort,
											 @RequestParam(required = false) String direction) {
		return ResponseEntity.ok(customerService.getSortedCustomersByLastName(lastName, direction, sort));
	}
}
