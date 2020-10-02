package org.finance.mybank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	private String firstName;
	private String lastName;
	private String address;
	private LocalDateTime birthDate;
	private int ratingClass;
}
