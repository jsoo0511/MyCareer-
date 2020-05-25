
package com.mycareer.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.user.Award;
import com.mycareer.model.dto.user.Qualification;
import com.mycareer.model.dto.user.Url;
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
	
	@GetMapping(value = "users/qualifications/{userId}")
	public ResponseEntity<Object> findAllByQualificationUserId(int userId){
		List<Qualification> qList=us.findAllByqUserUserId(userId);
		if(Objects.isNull(qList)) 
			return new ResponseEntity<Object>(null,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Object>(qList,HttpStatus.OK);
		
	}
	
	@GetMapping(value= "users/url/{userId}")
	public ResponseEntity<Object> findAllByUrlUserId(int userId){
		List<Url> uList=us.findAllByuUserUserId(userId);
		if(Objects.isNull(uList)) 
			return new ResponseEntity<Object>(null,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Object>(uList,HttpStatus.OK);
		
	}
	
	@PostMapping(value="users/qualifications")
	public ResponseEntity<Object> saveQualifications(@RequestBody Qualification q){
		System.out.println(q.getGainDay()+" "+q.getGrade()+" "+q.getQualificationId());
		System.out.println(q.getQUser());
		try {			
			Qualification result=us.saveQ(q);
			if(Objects.isNull(result))
				return new ResponseEntity<Object>(null,HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result,HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail",HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping(value="users/urls")
	public ResponseEntity<Object> saveUrl(@RequestBody Url u){
		System.out.println(u.getUrl()+" "+u.getUrlInfo()+" "+u.getUrlId());
		System.out.println(u.getUUser());
		try {
			Url result=us.saveUrl(u);
			if(Objects.isNull(result))
				return new ResponseEntity<Object>(null,HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail",HttpStatus.CONFLICT);
		}
	}
	@DeleteMapping(value="users/qualifications")
	public ResponseEntity<Object> deleteQualifications(@RequestParam int qId){
		int result=us.deleteByqualificationId(qId);
		if(result==1)
			return new ResponseEntity<Object>("Delete success",HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Delete Fail",HttpStatus.CONFLICT);
	}
	
	@DeleteMapping(value="users/urls")
	public ResponseEntity<Object> deleteUrls(@RequestParam int uId){
		int result=us.deleteByurlId(uId);
		System.out.println(result);
		if(result==1)
			return new ResponseEntity<Object>("Delete success",HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Delete Fail",HttpStatus.CONFLICT);
	}
}
