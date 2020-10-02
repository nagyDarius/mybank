package org.finance.mybank.web;

import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerIT extends BaseMvcIT {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	@WithMockUser
	public void correctCreateCustomerRequestShouldAddItToDatabase() throws Exception {
		final CustomerDTO customer = CustomerDTO.builder()
				.firstName("Mario")
				.lastName("Speedwagon")
				.address("Train Station 2")
				.birthDate("2000-04-23")
				.build();

		mockMvc.perform(post("/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(customer))
				.with(csrf()))
				.andExpect(status().isOk());

		final List<CustomerEntity> customers = customerRepository.findAll();
		assertEquals(1, customers.size());
		assertEquals(customer.getFirstName(), customers.get(0).getFirstName());
	}
}
