package org.finance.mybank.services;

import org.finance.mybank.dto.PostingDTO;
import org.finance.mybank.mappers.MapperService;
import org.finance.mybank.persistence.posting.PostingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostingService {

	private final PostingRepository postingRepository;
	private final MapperService mapperService;

	public PostingService(PostingRepository postingRepository, MapperService mapperService) {
		this.postingRepository = postingRepository;
		this.mapperService = mapperService;
	}

	public List<PostingDTO> listAllPostings(String dateString) {
		final Date date = mapperService.map(dateString, Date.class);
		return postingRepository.findAll().stream()
				.filter(posting -> posting.getCreatedDate().toLocalDate().equals(LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault())))
				.map(posting -> mapperService.map(posting, PostingDTO.class)).collect(Collectors.toList());
	}
}
