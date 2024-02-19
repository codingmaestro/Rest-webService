package com.example.restwebservice.helloworld.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
//url versioning:twitter
	@GetMapping("/v1/person")
	public PersonV1 getpersonv1() {
		return new PersonV1("Veni Nigam");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getpersonv2() {
		return new PersonV2(new Name("Veni", "Nigam"));
	}
	//Request parameter versioning:amazon
	@GetMapping(path="/person", params = "version=1")
	public PersonV1 getpersonv1Request() {
		return new PersonV1("Veni Nigam");
	}
	
	@GetMapping(path="/person", params = "version=2")
	public PersonV2 getpersonv2request() {
		return new PersonV2(new Name("Veni", "Nigam"));
	}
	
	//header versioning:Microsoft
	@GetMapping(path="/person/headers",  headers =  "API-VERSION=1")
	public PersonV1 getpersonv1Requestheader() {
		return new PersonV1("Veni Nigam");
	}
	
	@GetMapping(path="/person/headers",  headers =  "API-VERSION=2")
	public PersonV2 getpersonv2requestheader() {
		return new PersonV2(new Name("Veni", "Nigam"));
	}
	
	//media type versioning:Github
		@GetMapping(path="/person/accept",  produces =    "application/API-VERSION-1+json")
		public PersonV1 getpersonAcceptRequestheader() {
			return new PersonV1("Veni Nigam");
		}
		
		@GetMapping(path="/person/accept",  produces =   "application/API-VERSION-2+json")
		public PersonV2 getpersonv2Acceptrequestheader() {
			return new PersonV2(new Name("Veni", "Nigam"));
		}
}
