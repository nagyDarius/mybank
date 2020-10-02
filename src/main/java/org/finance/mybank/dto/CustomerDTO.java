package org.finance.mybank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
	private String firstName;
	private String lastName;
	private String address;
	private String birthDate;
	private int ratingClass;
}
