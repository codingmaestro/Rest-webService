package com.example.restwebservice.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Value("${server.port}")
	String serverPort;
	
	MessageSource msgSource;
	
	public HelloWorldController(MessageSource msgSource) {
		this.msgSource=msgSource;
	}
	
	@GetMapping(path="/hello-world")
	public String getHelloWorld() {
	System.out.println("My server port is"+serverPort);
		return "Hello world";
		
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean getbean() {
		return new HelloWorldBean("Hello bean!");
	}
	
	@GetMapping(path="/hello-world/{name}")
	public HelloWorldBean helloname(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello %s !", name));
	}
	
	@GetMapping(path="/hello-world/internationalized")
	public String getHelloWorldInternationalized() {
		Locale locale=LocaleContextHolder.getLocale();
		return msgSource.getMessage("good.morning.message", null,"Default message", locale);
	}
	
}

