
package com.mycareer.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.user.Award;
import com.mycareer.model.service.UserService;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService us;
	
//	@RequestMapping(method = RequestMethod.GET, value = "user/{userNo}")
	@GetMapping(value = "users/{userNo}")
	public ResponseEntity<Object> findOne(@PathVariable int userNo){
		User tUser = us.findByUserId(userNo);
		if(Objects.isNull(tUser)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Object>(tUser, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "users")
	public ResponseEntity<Object> findAll(){
		List<User> uList = us.findAll();
		if(Objects.isNull(uList)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Object>(uList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "users/awards")
	public ResponseEntity<Object> findAllByAwardUserId(int userId){
		List<Award> aList = us.findAllByUserId(userId);
		if(Objects.isNull(aList)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Object>(aList, HttpStatus.OK);
		}
	}
}
