package org.finance.mybank.web;

import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.services.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping
	public void createCustomer(@RequestBody CustomerDTO customerDTO) {
		customerService.createCustomer(customerDTO);
	}
}
