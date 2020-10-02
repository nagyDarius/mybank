package org.finance.mybank.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class BaseMvcIT {
	@Autowired
	protected MockMvc mockMvc;

	@PostConstruct()
	public void configureMockMvc() {

	}

	protected ObjectMapper mapper = new ObjectMapper();
}
