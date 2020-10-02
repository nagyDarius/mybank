package org.finance.mybank.services;

import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.mappers.MapperService;
import org.finance.mybank.model.Customer;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.customer.CustomerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
		if (customer.getRatingClass() == null) {
			customer.setRatingClass(DEFAULT_RATING_CLASS);
		}
		final var customerEntity = mapperService.map(customer, CustomerEntity.class);
		customerRepository.save(customerEntity);
	}

	public List<CustomerDTO> getSortedCustomersByLastName(String lastName, Sort.Direction direction, String... sort) {
		return customerRepository.findAllByLastName(lastName, Sort.by(direction, sort)).stream()
				.map(customer -> mapperService.mapFields(customer, Customer.class, "firstName", "lastName", "address"))
				.map(customer -> mapperService.map(customer, CustomerDTO.class)).collect(Collectors.toList());
	}
}
