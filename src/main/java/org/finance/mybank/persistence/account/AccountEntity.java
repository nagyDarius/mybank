package org.finance.mybank.persistence.account;

import lombok.*;
import org.finance.mybank.persistence.BaseEntity;
import org.finance.mybank.persistence.customer.CustomerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity extends BaseEntity {
	@Column
	private Double balance;
	@ManyToOne
	@JoinColumn(name = "customerId", nullable = false)
	private CustomerEntity customer;
}
