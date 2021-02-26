package com.jbac.task.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jbac.task.entity.User;
import com.jbac.task.model.JwtUser;
import com.jbac.task.security.JwtGenerator;
import com.jbac.task.services.IUserService;

@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.POST})
@RequestMapping("/auth")
public class UserRestController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@PostMapping("/user")
	public ResponseEntity<?> addUser(@RequestBody User user){
		if(userService.findUser(user)==null) {
			userService.save(user);
			User u = userService.checkUserLogin(user);
			JwtUser jwtUser = new JwtUser();
			jwtUser.setId(u.getId());
			jwtUser.setUserName(u.getEmail());
			return new ResponseEntity<>((Collections.singletonMap("jwtToken", jwtGenerator.generate(jwtUser))),HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
	}
	@PostMapping(value= "/login")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		User u = userService.checkUserLogin(user);
		if(u != null) {
			JwtUser jwtUser = new JwtUser();
			jwtUser.setId(u.getId());
			jwtUser.setUserName(u.getEmail());
			return new ResponseEntity<>((Collections.singletonMap("jwtToken", jwtGenerator.generate(jwtUser))),HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	} 

}
