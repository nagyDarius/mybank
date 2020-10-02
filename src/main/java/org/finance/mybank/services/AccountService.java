package org.finance.mybank.services;

import org.finance.mybank.dto.AccountBalanceDTO;
import org.finance.mybank.dto.PostingDTO;
import org.finance.mybank.exception.InvalidQueryParamException;
import org.finance.mybank.exception.NotEnoughBalanceException;
import org.finance.mybank.persistence.account.AccountEntity;
import org.finance.mybank.persistence.account.AccountRepository;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.customer.CustomerRepository;
import org.finance.mybank.persistence.posting.PostingEntity;
import org.finance.mybank.persistence.posting.PostingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;
	private final PostingRepository postingRepository;

	public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository, PostingRepository postingRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
		this.postingRepository = postingRepository;
	}

	public void createAccountForCustomer(Long customerId) {
		final CustomerEntity customerEntity = customerRepository.findById(customerId)
				.orElseThrow(() -> new InvalidQueryParamException(String.format("Customer with id '%s' was not found!", customerId)));
		final AccountEntity accountEntity = AccountEntity.builder().balance(0D).customer(customerEntity).build();
		accountRepository.save(accountEntity);
	}

	public void transfer(PostingDTO posting) {
		final AccountEntity from = accountRepository.findById(posting.getFromId())
				.orElseThrow(() -> new InvalidQueryParamException(String.format("Account with id '%s' was not found!", posting.getFromId())));

		if (from.getBalance() < posting.getAmount()) {
			throw new NotEnoughBalanceException(from.getBalance(), posting.getAmount());
		}

		final AccountEntity to = accountRepository.findById(posting.getToId())
				.orElseThrow(() -> new InvalidQueryParamException(String.format("Account with id '%s' was not found!", posting.getToId())));

		applyTransfer(from, to, posting.getAmount());
	}

	private void applyTransfer(AccountEntity from, AccountEntity to, Double amount) {
		from.setBalance(from.getBalance() - amount);
		to.setBalance(to.getBalance() + amount);
		postingRepository.save(new PostingEntity(amount, from, to));
	}

	public List<AccountBalanceDTO> findByCustomerId(Long customerId) {
		return accountRepository.findAllByCustomer_Id(customerId).stream()
				.map(account -> new AccountBalanceDTO(account.getBalance(), account.getId()))
				.collect(Collectors.toList());
	}
}
