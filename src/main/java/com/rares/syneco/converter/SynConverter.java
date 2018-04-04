package com.rares.syneco.converter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SynConverter {

	@RequestMapping("/convert")
	public String respondToInput(@RequestParam(name="input", defaultValue = "", required=true) String input) {
		//verify input
		//convert to appropriate output
		return "salut" + input; //convert(input)
	}
	
	private String convert(String reqInput) {
		
		
		return null;
	}
}
