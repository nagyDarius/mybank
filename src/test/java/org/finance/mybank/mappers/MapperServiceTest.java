package org.finance.mybank.mappers;

import org.finance.mybank.dto.CustomerDTO;
import org.finance.mybank.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class MapperServiceTest {

	@Autowired
	private MapperService mapperService;

	@Test
	public void mapFromCustomerDtoToCustomerAndBack() {
		final CustomerDTO customerDTO = CustomerDTO.builder()
				.firstName("Mario")
				.lastName("Speedwagon")
				.address("Train Station 2")
				.birthDate("2000-04-23").build();

		final Customer customer = mapperService.map(customerDTO, Customer.class);
		final CustomerDTO mappedCustomerDTO = mapperService.map(customer, CustomerDTO.class);

		assertEquals(customerDTO.getFirstName(), mappedCustomerDTO.getFirstName());
		assertEquals(customerDTO.getLastName(), mappedCustomerDTO.getLastName());
		assertEquals(customerDTO.getAddress(), mappedCustomerDTO.getAddress());
		assertEquals(customerDTO.getBirthDate(), mappedCustomerDTO.getBirthDate());
		assertEquals(customerDTO.getRatingClass(), mappedCustomerDTO.getRatingClass());
	}
}
