package org.finance.mybank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	private String firstName;
	private String lastName;
	private String address;
	private Date birthDate;
	private Integer ratingClass;
}
