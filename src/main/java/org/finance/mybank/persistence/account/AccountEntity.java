package org.finance.mybank.persistence.account;

import lombok.*;
import org.finance.mybank.persistence.BaseEntity;
import org.finance.mybank.persistence.customer.CustomerEntity;
import org.finance.mybank.persistence.posting.PostingEntity;

import javax.persistence.*;
import java.util.List;

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
//	@JoinColumn(name = "customerId", nullable = false)
	private CustomerEntity customer;
	@OneToMany
	private List<PostingEntity> postings;
}
