package org.finance.mybank.persistence.posting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.finance.mybank.persistence.BaseEntity;
import org.finance.mybank.persistence.account.AccountEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingEntity extends BaseEntity {
	@Column
	private Double amount;
	@ManyToOne
	@JoinColumn(name = "fromId", nullable = false)
	private AccountEntity from;
	@ManyToOne
	@JoinColumn(name = "toId", nullable = false)
	private AccountEntity to;
}
