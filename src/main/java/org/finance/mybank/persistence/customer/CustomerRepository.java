package org.finance.mybank.persistence.customer;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	public List<CustomerEntity> findAllByLastName(String lastName, Sort sort);
}
