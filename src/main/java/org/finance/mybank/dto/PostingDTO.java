package org.finance.mybank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingDTO {
	private Long id;
	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be positive double")
	private Double amount;
	@NotNull(message = "From id is required")
	private Long fromId;
	@NotNull(message = "To id is required")
	private Long toId;
}
