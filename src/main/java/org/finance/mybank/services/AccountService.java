package org.finance.mybank.services;

import org.finance.mybank.exception.InvalidQueryParamException;
import org.finance.mybank.persistence.account.AccountEntity;
import org.finance.mybank.persistence.account.AccountRepository;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.customer.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;

	public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}

	public void createAccountForCustomer(Long customerId) {
		final CustomerEntity customerEntity = customerRepository.findById(customerId)
				.orElseThrow(() -> new InvalidQueryParamException(String.format("Customer with id %s was not found!", customerId)));
		final AccountEntity accountEntity = AccountEntity.builder().balance(0D).customer(customerEntity).build();
		accountRepository.save(accountEntity);
	}
}
