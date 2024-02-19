package com.example.restwebservice.userservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int count=0;
	
	static {
		users.add(new User(++count, "veni",LocalDate.now().minusYears(30)));
		users.add(new User(++count, "pranav",LocalDate.now().minusYears(20)));
		users.add(new User(++count, "deepu",LocalDate.now().minusYears(34)));
	}
	
	List<User> findAll(){
		return users;
	}
	
	User findById(int id){
		return users.stream().filter(user->user.getId().equals(id)).findFirst().orElse(null);
	}
	
	public User save(User user) {
		user.setId(++count);
		 users.add(user);
		 return user;
		
	}
	
	public User deleteById (int id) {
		 User delete = users.stream().filter(user->user.getId().equals(id)).findFirst().orElse(null);
		users.remove(delete);
		return delete;
	}
}
