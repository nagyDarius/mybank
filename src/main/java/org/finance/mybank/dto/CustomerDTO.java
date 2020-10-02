package org.finance.mybank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
	private Long id;
	@NotBlank(message = "First name is required")
	private String firstName;
	@NotBlank(message = "Last name is required")
	private String lastName;
	@NotBlank(message = "Address is required")
	private String address;
	@NotBlank(message = "Birth date is required")
	private String birthDate;
	private Integer ratingClass;
}
