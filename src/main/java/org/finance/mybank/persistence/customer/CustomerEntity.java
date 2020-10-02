package org.finance.mybank.persistence.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.finance.mybank.persistence.BaseEntity;
import org.finance.mybank.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
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
}
