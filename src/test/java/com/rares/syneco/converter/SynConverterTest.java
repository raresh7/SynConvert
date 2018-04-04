package com.rares.syneco.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rares.syneco.SynecoApplication;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
public class SynConverterTest {
	
	@Autowired
	@Mock
	SynecoApplication app;
	
	  @Autowired
	  private MockMvc mvc;
		
	  @Autowired
		private TestRestTemplate testRestTemplate;

	@Autowired
	private SynConverter converter;
	
	@Test
	public void converterTestYears() {
		assertThat(converter.respondToInput("2017")).isEqualTo("2017");
		assertThat(converter.respondToInput("2018")).isEqualTo("2018");
		assertThat(converter.respondToInput("2019")).isEqualTo("2019");
		assertThat(converter.respondToInput("2020")).isEqualTo("2020");
	}

	@Test
	public void converterTestQuarters() {
		assertThat(converter.respondToInput("Q-2018-2")).isEqualTo("Q2-18");
		assertThat(converter.respondToInput("Q-2018-3")).isEqualTo("Q3-18");
		assertThat(converter.respondToInput("Q-2018-4")).isEqualTo("Q4-18");
		assertThat(converter.respondToInput("Q-2019-1")).isEqualTo("Q1-19");
		assertThat(converter.respondToInput("Q-2019-2")).isEqualTo("Q2-19");
	}
	
	@Test
	public void converterTestSeasons() {
		assertThat(converter.respondToInput("SWS-2018-04")).isEqualTo("Sum-18");
		assertThat(converter.respondToInput("SWS-2018-10")).isEqualTo("Win-18/19");
		assertThat(converter.respondToInput("M-2018-05")).isEqualTo("May-2018");
		assertThat(converter.respondToInput("SWS-2019-10")).isEqualTo("Win-19/20");
	}
	
	@Test
	public void converterTestMonths() {
		assertThat(converter.respondToInput("M-2018-02")).isEqualTo("Feb-2018");
		assertThat(converter.respondToInput("M-2018-03")).isEqualTo("Mar-2018");
		assertThat(converter.respondToInput("M-2018-04")).isEqualTo("Apr-2018");
		assertThat(converter.respondToInput("M-2018-05")).isEqualTo("May-2018");
	}
	
//	public void negativeTests() {
//		assertThat(converter.respondToInput("M-2018-05")).isEqualTo("May-2018");
//		assertThat(converter.respondToInput("M-2018-05")).isEqualTo("May-2018");
//		assertThat(converter.respondToInput("Q-2018-4")).isEqualTo("Q4-18");
//		assertThat(converter.respondToInput("2019")).isEqualTo("2019");
//	}
//	
//	@Test
//	public void intTest() {
//		MockHttpServletResponse response = mvc.perform(RequestBuilderget("/convert/?=SWS-2018-10").accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//		
//
//		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//	}
}
