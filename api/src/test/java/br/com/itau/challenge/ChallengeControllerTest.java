package br.com.itau.challenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.itau.challenge.parameter.InputPasswordParameter;

@ActiveProfiles(profiles = { "test" })
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = { ItauChallengeApplication.class })
@RunWith(SpringRunner.class)
public class ChallengeControllerTest {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void passwordInvalidvalid400Errors() throws JsonProcessingException, Exception {
		checkInvalidsInputs400Errors(new InputPasswordParameter(""));
		checkInvalidsInputs400Errors(new InputPasswordParameter("aa"));
		checkInvalidsInputs400Errors(new InputPasswordParameter("ab"));
		checkInvalidsInputs400Errors(new InputPasswordParameter("AAAbbbCc"));
		checkInvalidsInputs400Errors(new InputPasswordParameter("AbTp9!foo"));
		checkInvalidsInputs400Errors(new InputPasswordParameter("AbTp9!foA"));
		checkInvalidsInputs400Errors(new InputPasswordParameter("AbTp9 fok"));
	}

	@Test
	public void passwordValid200Success() throws JsonProcessingException, Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/v1/validity/password")
						.content(mapper.writeValueAsString(new InputPasswordParameter("AbTp9!fok")))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
	}

	private void checkInvalidsInputs400Errors(InputPasswordParameter input) throws JsonProcessingException, Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/v1/validity/password").content(mapper.writeValueAsString(input))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
	}

}
