package org.finance.mybank.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "Create a customer (date format is yyyy-MM-dd)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created customer"),
			@ApiResponse(responseCode = "400", description = "Please check the error messages to see what went wrong")
	})
	@PostMapping
	public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
		ResponseEntity<?> errorMap = validationErrorService.mapValidationService(bindingResult);
		if (errorMap != null)
			return errorMap;
		customerService.createCustomer(customerDTO);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Query customers by last name, last name is required. Sort can be firstName or address. Direction can be asc or desc (case insensitive).")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The list is returned"),
			@ApiResponse(responseCode = "400", description = "Please check the error messages to see what went wrong")
	})
	@GetMapping
	public ResponseEntity<?> queryByLastName(@RequestParam String lastName,
											 @RequestParam(required = false) String sort,
											 @RequestParam(required = false) String direction) {
		return ResponseEntity.ok(customerService.getSortedCustomersByLastName(lastName, direction, sort));
	}
}
