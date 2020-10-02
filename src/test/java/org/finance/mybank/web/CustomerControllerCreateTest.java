package org.finance.mybank.web;

import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.customer.CustomerRepository;
import org.finance.mybank.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.finance.mybank.util.Constants.DATE_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerCreateTest extends BaseMvcIT {

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	public void setUpTestMethod() {
		customerRepository.deleteAll();
	}

	@Test
	@WithMockUser
	public void correctCreateCustomerRequestShouldAddItToDatabaseWithDefaultRatingClass() throws Exception {
		final CustomerDTO customer = CustomerDTO.builder()
				.firstName("Mario")
				.lastName("Speedwagon")
				.address("Train Station 2")
				.birthDate("2000-04-23")
				.build();

		postCustomer(customer, status().isOk());

		final List<CustomerEntity> customers = customerRepository.findAll();
		assertEquals(1, customers.size());
		final CustomerEntity actual = customers.get(0);
		assertEquals(customer.getFirstName(), actual.getFirstName());
		assertEquals(customer.getLastName(), actual.getLastName());
		assertEquals(customer.getAddress(), actual.getAddress());
		assertEquals(customer.getBirthDate(), new SimpleDateFormat(DATE_FORMAT).format(actual.getBirthDate()));
		assertEquals(Constants.DEFAULT_RATING_CLASS, actual.getRatingClass());
	}

	@Test
	@WithMockUser
	public void correctCreateCustomerRequestShouldAddItToDatabase() throws Exception {
		final CustomerDTO customer = CustomerDTO.builder()
				.firstName("Mario")
				.lastName("Speedwagon")
				.address("Train Station 2")
				.birthDate("2000-04-23")
				.ratingClass(4)
				.build();

		postCustomer(customer, status().isOk());

		final List<CustomerEntity> customers = customerRepository.findAll();
		assertEquals(1, customers.size());
		final CustomerEntity actual = customers.get(0);
		assertEquals(customer.getFirstName(), actual.getFirstName());
		assertEquals(customer.getLastName(), actual.getLastName());
		assertEquals(customer.getAddress(), actual.getAddress());
		assertEquals(customer.getBirthDate(), new SimpleDateFormat(DATE_FORMAT).format(actual.getBirthDate()));
		assertEquals(4, actual.getRatingClass());
	}

	@Test
	@WithMockUser
	public void createCustomerWithNoFieldsShouldReturnBadRequestAndErrorMessages() throws Exception {
		postCustomer(new CustomerDTO(), status().isBadRequest())
				.andExpect(jsonPath("$.firstName").value("First name is required"))
				.andExpect(jsonPath("$.lastName").value("Last name is required"))
				.andExpect(jsonPath("$.address").value("Address is required"))
				.andExpect(jsonPath("$.birthDate").value("Birth date is required"));
	}

	@Test
	@WithMockUser
	public void createUserWithIncorrectlyFormattedDateShouldReturnAppropriateMessage() throws Exception {
		final CustomerDTO customer = CustomerDTO.builder()
				.firstName("Mario")
				.lastName("Speedwagon")
				.address("Train Station 2")
				.birthDate("2000.04.23")
				.build();

		postCustomer(customer, status().isBadRequest())
				.andExpect(jsonPath("$.message").value("2000.04.23 does not match required date format yyyy-MM-dd"));
	}

	private ResultActions postCustomer(CustomerDTO customer, ResultMatcher resultMatcher) throws Exception {
		return mockMvc.perform(post("/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(customer))
				.with(csrf()))
				.andExpect(resultMatcher);
	}
}
