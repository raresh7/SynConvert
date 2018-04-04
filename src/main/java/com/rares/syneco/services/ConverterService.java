package com.rares.syneco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rares.syneco.converter.SynConverter;

@RestController
public class ConverterService {

	@Autowired
	private SynConverter converter;
	
	@RequestMapping(value = "/convert", method = RequestMethod.GET)
	public String respondToInput(@RequestParam(name="input", defaultValue = "", required=true) String input) {
		return converter.convert(input);
	}
}
