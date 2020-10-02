package org.finance.mybank.web;

import com.fasterxml.jackson.core.type.TypeReference;
import org.finance.mybank.dto.PostingDTO;
import org.finance.mybank.persistence.account.AccountEntity;
import org.finance.mybank.persistence.posting.PostingEntity;
import org.finance.mybank.persistence.posting.PostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.finance.mybank.util.Constants.DATE_FORMAT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostingControllerListTest extends BaseMvcIT {

	@MockBean
	private PostingRepository postingRepository;

	@BeforeEach
	public void init() throws ParseException {
		final AccountEntity account = new AccountEntity(1D, null, null);
		final PostingEntity e1 = new PostingEntity(12D, account, account);
		final PostingEntity e2 = new PostingEntity(12D, account, account);
		final PostingEntity e3 = new PostingEntity(12D, account, account);

		final ZonedDateTime created1 = ZonedDateTime.ofInstant(new SimpleDateFormat(DATE_FORMAT).parse("2020-09-30").toInstant(), ZoneId.systemDefault());
		final ZonedDateTime created2 = created1.plusDays(2);
		e1.setCreatedDate(created1);
		e1.setId(1L);
		e2.setCreatedDate(created1);
		e2.setId(2L);
		e3.setCreatedDate(created2);
		e3.setId(3L);
		when(postingRepository.findAll()).thenReturn(List.of(e1, e2, e3));
	}

	@Test
	@WithMockUser
	public void listAllPostingsForGivenDateShouldBeSuccessful() throws Exception {
		final List<PostingDTO> postings = mapper.readValue(mockMvc.perform(get("/posting").param("date", "2020-09-30"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(), new TypeReference<>() {
		});

		assertEquals(2, postings.size());
		assertThat(postings.stream().map(PostingDTO::getId).collect(Collectors.toList()), containsInAnyOrder(1L, 2L));
	}
}
