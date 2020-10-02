package org.finance.mybank.model;

import lombok.Data;

import java.util.List;

@Data
public class Account {
	private Double balance;
	private Customer customer;
	private List<Posting> postings;
}
