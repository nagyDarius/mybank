package org.finance.mybank.web;

import com.fasterxml.jackson.core.type.TypeReference;
import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.customer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CustomerControllerQueryTest extends BaseMvcIT {

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	public void setUpTestMethod() throws ParseException {
		Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse("2000-02-02");
		final CustomerEntity customer1 = new CustomerEntity("Mario", "Cruiser", "Speedwagon address", birthDate, 3);
		final CustomerEntity customer2 = new CustomerEntity("Petey", "Cruiser", "Cruiser address", birthDate, 3);
		final CustomerEntity customer3 = new CustomerEntity("Anna", "Cruiser", "Sthesia address", birthDate, 3);
		final CustomerEntity customer4 = new CustomerEntity("Paul", "Cruiser", "Molive address", birthDate, 3);
		final CustomerEntity customer5 = new CustomerEntity("Gail", "Forcewind", "Forcewind address", birthDate, 3);
		customerRepository.saveAll(asList(customer1, customer2, customer3, customer4, customer5));
	}

	@Test
	@WithMockUser
	public void getCustomersByLastNameAndSortShouldBeSuccessful() throws Exception {
		final List<CustomerDTO> customerDTOS = mapper.readValue(mockMvc.perform(get("/customer")
				.param("lastName", "Cruiser")
				.param("sort", "firstName")
				.param("direction", "DESC")
				.with(csrf()))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(), new TypeReference<>() {
		});

		assertEquals(4, customerDTOS.size());
		assertEquals("Petey", customerDTOS.get(0).getFirstName());
		assertEquals("Paul", customerDTOS.get(1).getFirstName());
		assertEquals("Mario", customerDTOS.get(2).getFirstName());
		assertEquals("Anna", customerDTOS.get(3).getFirstName());
	}
}
