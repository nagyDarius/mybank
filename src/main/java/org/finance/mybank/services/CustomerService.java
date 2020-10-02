package org.finance.mybank.services;

import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.exception.InvalidQueryParamException;
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

	public List<CustomerDTO> getSortedCustomersByLastName(String lastName, String direction, String sort) {
		validateSortDirection(direction);
		validateSort(sort);
		return customerRepository.findAllByLastName(lastName, Sort.by(Sort.Direction.valueOf(direction), sort)).stream()
				.map(customer -> mapperService.mapFields(customer, Customer.class, "firstName", "lastName", "address"))
				.map(customer -> mapperService.map(customer, CustomerDTO.class)).collect(Collectors.toList());
	}

	private void validateSort(String sort) {
		if (sort.equals("firstName") || sort.equals("address")) {
			return;
		}
		throw new InvalidQueryParamException(String.format("Invalid value '%s' for sort given! Has to be one of 'firstName', 'address'.", sort));
	}

	private void validateSortDirection(String direction) {
		try {
			Sort.Direction.fromString(direction);
		} catch (IllegalArgumentException e) {
			throw new InvalidQueryParamException(e.getMessage());
		}
	}
}
