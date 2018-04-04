package com.rares.syneco;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SynecoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SynecoApplicationTests {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test //possible improvement -> parametrization
	public void integrationTSeason() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/convert?input=SWS-2018-10"),
				HttpMethod.GET, entity, String.class);

		String expected = "Win-18/19";

		assertThat(response.getBody()).isEqualTo(expected);

	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
