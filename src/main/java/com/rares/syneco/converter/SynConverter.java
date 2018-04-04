package com.rares.syneco.converter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class SynConverter {
	
	public String convert(String reqInput) {
		if(!verifyInput(reqInput)) {
			return "Invalid Input!"; //or throw exceptions
		}
		DateTimeFormatter yearFormatter = DateTimeFormat.forPattern("yyyy");
		DateTimeFormatter quarterFormatter = DateTimeFormat.forPattern("yyyy-M");
		DateTimeFormatter seasonMonthFormatter = DateTimeFormat.forPattern("yyyy-MM");
		
		DateTimeFormatter quarterOutFormatter = DateTimeFormat.forPattern("YY");
		DateTimeFormatter monthOutFormatter = DateTimeFormat.forPattern("MMM-yyyy");
		
		DateTime tempDate;
		StringBuilder res = new StringBuilder();
		if(reqInput.startsWith("Q-") ) {
			tempDate = quarterFormatter.parseDateTime(getRelevantInput(reqInput));
			res.append("Q");
			res.append(tempDate.getMonthOfYear());
			res.append('-');
			res.append(quarterOutFormatter.print(tempDate));
		} else if(reqInput.startsWith("SWS-")) {
			tempDate = seasonMonthFormatter.parseDateTime(getRelevantInput(reqInput));
			res.append(getSeasonFromMonth(tempDate.getMonthOfYear()));
			res.append('-');
			res.append(quarterOutFormatter.print(tempDate));
			if(res.toString().startsWith("Win")) {
				res.append('/');
				res.append(tempDate.getYearOfCentury() + 1);
			} 
		} else if(reqInput.startsWith("M-")) {
			tempDate = seasonMonthFormatter.parseDateTime(getRelevantInput(reqInput));
			res.append(monthOutFormatter.print(tempDate));
		}
		else {
			DateTime year = yearFormatter.parseDateTime(reqInput);
			if(yearFormatter.print(year).equals(reqInput)) {
				res.append(yearFormatter.print(year));
			} else {
				res.append("Invalid Input!");
			}
		} 
		return res.toString();
	}
	
	private String getRelevantInput (String input) {
		return input.substring(input.indexOf('-')+1);
	}
	
	private String getSeasonFromMonth(int month) {
		return month > 2 && month <9 ? "Sum" : "Win";
	}
	
	private boolean verifyInput(String reqInput) {
		if(reqInput.length() < 4 || reqInput.length() > 11) {
			return false;
		}
		return true;
			
	}
}
