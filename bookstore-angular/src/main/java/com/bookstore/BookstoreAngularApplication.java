package com.bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.config.SecurityUtility;
import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.service.UserService;

@SpringBootApplication
public class BookstoreAngularApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreAngularApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setId(0l);
		user.setFirstName("bharath");
		user.setLastName("shark");
		user.setUsername("j");
		user.setPassword(SecurityUtility.passwordEncoder().encode("abc"));
		user.setEmail("barath@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role=new Role();
		role.setRoleId(1);
		role.setName("ROLE_USER");
		userRoles.add(new UserRole(user,role));
		
		userService.createUser(user,userRoles);
		
		userRoles.clear();
		
		
		User user2 = new User();
		user2.setId(0l);
		user2.setFirstName("Admin");
		user2.setLastName("Admin");
		user2.setUsername("admin");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user2.setEmail("Admin@gmail.com");
		Set<UserRole> userRoles2 = new HashSet<>();
		Role role2=new Role();
		role2.setRoleId(0);
		role2.setName("ROLE_ADMIN");
		userRoles2.add(new UserRole(user2,role2));
		
		userService.createUser(user2,userRoles2);
	}
}
