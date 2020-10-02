package org.finance.mybank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class PostingDTO {
	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be positive double")
	private Double amount;
	@NotNull(message = "From id is required")
	private Long fromId;
	@NotNull(message = "To id is required")
	private Long toId;
}
