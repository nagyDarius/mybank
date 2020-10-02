package org.finance.mybank.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "List all postings for a given date (yyyy-MM-dd)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The list is returned"),
	})
	@GetMapping
	public ResponseEntity<?> listAllPostings(@RequestParam String date) {
		return ResponseEntity.ok(postingService.listAllPostings(date));
	}
}
