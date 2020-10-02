package org.finance.mybank.persistence.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	List<AccountEntity> findAllByCustomer_Id(Long customerId);
}
