package com.rares.syneco.converter;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
public class SynConverterTest {
	
	@Autowired
	private SynConverter converter;
	
	@Test
	public void converterTestYears() {
		assertThat(converter.convert("2017")).isEqualTo("2017");
		assertThat(converter.convert("2018")).isEqualTo("2018");
		assertThat(converter.convert("2019")).isEqualTo("2019");
		assertThat(converter.convert("2020")).isEqualTo("2020");
	}

	@Test
	public void converterTestQuarters() {
		assertThat(converter.convert("Q-2018-2")).isEqualTo("Q2-18");
		assertThat(converter.convert("Q-2018-3")).isEqualTo("Q3-18");
		assertThat(converter.convert("Q-2018-4")).isEqualTo("Q4-18");
		assertThat(converter.convert("Q-2019-1")).isEqualTo("Q1-19");
		assertThat(converter.convert("Q-2019-2")).isEqualTo("Q2-19");
	}
	
	@Test
	public void converterTestSeasons() {
		assertThat(converter.convert("SWS-2018-04")).isEqualTo("Sum-18");
		assertThat(converter.convert("SWS-2018-10")).isEqualTo("Win-18/19");
		assertThat(converter.convert("M-2018-05")).isEqualTo("May-2018");
		assertThat(converter.convert("SWS-2019-10")).isEqualTo("Win-19/20");
	}
	
	@Test
	public void converterTestMonths() {
		assertThat(converter.convert("M-2018-02")).isEqualTo("Feb-2018");
		assertThat(converter.convert("M-2018-03")).isEqualTo("Mar-2018");
		assertThat(converter.convert("M-2018-04")).isEqualTo("Apr-2018");
		assertThat(converter.convert("M-2018-05")).isEqualTo("May-2018");
	}
	
	public void negativeTests() {
		assertThat(converter.convert("MM-2018-05")).isEqualTo("Invalid Input!");
		assertThat(converter.convert("M-20148-05")).isEqualTo("Invalid Input!");
		assertThat(converter.convert("Q2018-4")).isEqualTo("Invalid Input!");
		assertThat(converter.convert("20 19")).isEqualTo("Invalid Input!");
	}

}
