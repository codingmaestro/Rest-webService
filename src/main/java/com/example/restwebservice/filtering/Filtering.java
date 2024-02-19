package com.example.restwebservice.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class Filtering {

	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		MappingJacksonValue mpv=new MappingJacksonValue(someBean);
	
		SimpleBeanPropertyFilter filter= 
				 SimpleBeanPropertyFilter.filterOutAllExcept("filter1","filter3");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mpv.setFilters(filters);
		return mpv;
	}
	
}
