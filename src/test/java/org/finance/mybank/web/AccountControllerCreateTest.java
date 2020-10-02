package org.finance.mybank.web;

import org.finance.mybank.persistence.account.AccountEntity;
import org.finance.mybank.persistence.account.AccountRepository;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerCreateTest extends BaseMvcIT {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	@WithMockUser
	public void createAccountForCustomerShouldBeSuccessful() throws Exception {
		final CustomerEntity john = customerRepository.save(CustomerEntity.builder().firstName("John").build());
		mockMvc.perform(post("/account").param("customerId", john.getId().toString()).with(csrf()))
				.andExpect(status().isOk());

		final List<AccountEntity> accounts = accountRepository.findAll();
		assertEquals(1, accounts.size());
		assertEquals(0, accounts.get(0).getBalance());
		assertEquals(john.getFirstName(), accounts.get(0).getCustomer().getFirstName());
	}

	@Test
	@WithMockUser
	public void createAccountForNonExistingCustomerShouldReturnAppropriateMessage() throws Exception {
		mockMvc.perform(post("/account").param("customerId", "34").with(csrf()))
				.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("Customer with id '34' was not found!"));
	}

}
