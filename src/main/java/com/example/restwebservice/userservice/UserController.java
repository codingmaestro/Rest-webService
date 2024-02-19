package com.example.restwebservice.userservice;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restwebservice.userservice.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	UserDaoService dao;
	
	@GetMapping("/users")
	public List<User> fetchAllUsers(){
		return dao.findAll();
		
	}
	

	@GetMapping("/users/{id}")
	public EntityModel<User> fetchAllUsers(@PathVariable int id){
		 User findById = dao.findById(id);
		if(findById ==null) {
			throw new UserNotFoundException("id:"+id);
		}
		
		EntityModel<User> model = EntityModel.of(findById);
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).fetchAllUsers());
		model.add(link.withRel("all-users"));
		return model;
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> postAllUsers( @Valid @RequestBody User user){
		 User savedUser = dao.save(user);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable int id) {
		dao.deleteById(id);
	}
}
