package org.finance.mybank.web;

import com.fasterxml.jackson.core.type.TypeReference;
import org.finance.mybank.dto.AccountBalanceDTO;
import org.finance.mybank.persistence.account.AccountEntity;
import org.finance.mybank.persistence.account.AccountRepository;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerListTest extends BaseMvcIT {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	@WithMockUser
	public void listAccountsForOneCustomerShouldBeSuccessful() throws Exception {
		final CustomerEntity john = customerRepository.save(CustomerEntity.builder().firstName("John").build());
		final AccountEntity account1 = accountRepository.save(new AccountEntity(10.21, john, null));
		final AccountEntity account2 = accountRepository.save(new AccountEntity(10.21, john, null));

		final List<AccountBalanceDTO> accounts = mapper.readValue(mockMvc.perform(get("/account").param("customerId", john.getId().toString()))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(), new TypeReference<>() {
		});

		assertEquals(2, accounts.size());
		assertEquals(account1.getBalance(), accounts.stream()
				.filter(account -> account.getId().equals(account1.getId()))
				.findFirst().get().getBalance());
		assertEquals(account2.getBalance(), accounts.stream()
				.filter(account -> account.getId().equals(account2.getId()))
				.findFirst().get().getBalance());
	}

}
