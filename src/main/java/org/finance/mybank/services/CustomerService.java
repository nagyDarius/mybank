package org.finance.mybank.services;

import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.mappers.MapperService;
import org.finance.mybank.model.Customer;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import static org.finance.mybank.util.Constants.DEFAULT_RATING_CLASS;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;
	private final MapperService mapperService;

	public CustomerService(CustomerRepository customerRepository, MapperService mapperService) {
		this.customerRepository = customerRepository;
		this.mapperService = mapperService;
	}

	public void createCustomer(CustomerDTO customerDTO) {
		final var customer = mapperService.map(customerDTO, Customer.class);
		if(customer.getRatingClass() == null){
			customer.setRatingClass(DEFAULT_RATING_CLASS);
		}
		final var customerEntity = mapperService.map(customer, CustomerEntity.class);
		customerRepository.save(customerEntity);
	}
}