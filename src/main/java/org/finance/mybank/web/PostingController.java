package org.finance.mybank.web;

import org.finance.mybank.services.PostingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("posting")
public class PostingController {

	private final PostingService postingService;

	public PostingController(PostingService postingService) {
		this.postingService = postingService;
	}

	@GetMapping
	public ResponseEntity<?> listAllPostings(@RequestParam String date) {
		return ResponseEntity.ok(postingService.listAllPostings(date));
	}
}
