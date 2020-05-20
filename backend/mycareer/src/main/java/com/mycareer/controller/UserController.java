package com.mycareer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import com.mycareer.model.dto.User;
import com.mycareer.model.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService us;
	
	@GetMapping("user/{userNo}")
	public ResponseEntity<Object> findOne(@PathVariable int userNo){
		User tUser=us.findByUserId(userNo);
		System.out.println(tUser);
		if(Objects.isNull(tUser)) {
			return new ResponseEntity<Object>(null,HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Object>(tUser,HttpStatus.OK);
		}
	}
}
