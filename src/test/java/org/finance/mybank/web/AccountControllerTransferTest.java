package org.finance.mybank.web;

import org.finance.mybank.dto.PostingDTO;
import org.finance.mybank.persistence.account.AccountEntity;
import org.finance.mybank.persistence.account.AccountRepository;
import org.finance.mybank.persistence.posting.PostingEntity;
import org.finance.mybank.persistence.posting.PostingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTransferTest extends BaseMvcIT {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PostingRepository postingRepository;

	@Test
	@WithMockUser
	public void correctTransferShouldBeSuccessful() throws Exception {
		final AccountEntity from = accountRepository.save(new AccountEntity(10D, null, null));
		final AccountEntity to = accountRepository.save(new AccountEntity(0D, null, null));

		mockMvc.perform(post("/account/transfer").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new PostingDTO(5.5, from.getId(), to.getId())))
				.with(csrf()))
				.andExpect(status().isOk());

		final AccountEntity updatedFrom = accountRepository.findById(from.getId()).get();
		final AccountEntity updatedTo = accountRepository.findById(to.getId()).get();
		assertEquals(4.5, updatedFrom.getBalance());
		assertEquals(5.5, updatedTo.getBalance());

		final List<PostingEntity> postings = postingRepository.findAll();
		assertEquals(1, postings.size());
		assertEquals(from.getId(), postings.get(0).getFrom().getId());
		assertEquals(to.getId(), postings.get(0).getTo().getId());
		assertEquals(5.5, postings.get(0).getAmount());
	}
}
