package org.finance.mybank.persistence.customer;

import lombok.*;
import org.finance.mybank.persistence.BaseEntity;
import org.finance.mybank.persistence.account.AccountEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity extends BaseEntity {
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String address;
	@Column
	private Date birthDate;
	@Column
	private Integer ratingClass;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<AccountEntity> accounts;
}
