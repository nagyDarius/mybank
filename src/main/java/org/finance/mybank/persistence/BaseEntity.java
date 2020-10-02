package org.finance.mybank.persistence;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
public class BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	@CreatedDate
	private ZonedDateTime createdDate;

	@LastModifiedDate
	private ZonedDateTime lastModifiedDate;
}
