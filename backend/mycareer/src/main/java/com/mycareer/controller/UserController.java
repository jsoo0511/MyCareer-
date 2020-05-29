
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
import org.springframework.web.bind.annotation.PutMapping;
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

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService us;

//	@RequestMapping(method = RequestMethod.GET, value = "user/{userNo}")
	@GetMapping(value = "users/{userNo}")
	@ApiOperation(value = "userNo 회원 정보 조회")
	public ResponseEntity<Object> findOne(@PathVariable int userNo) {
		User tUser = us.findByUserNo(userNo);
		if (Objects.isNull(tUser)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(tUser, HttpStatus.OK);
		}
	}

	@PutMapping(value = "users/{userNo}")
	@ApiOperation(value = "userNo 회원 정보 수정")
	public ResponseEntity<Object> updateUser(@PathVariable int userNo, @RequestBody User user) {
		User tUser = us.findByUserNo(userNo);
		if (Objects.isNull(tUser)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Object>(us.updateUser(user), HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "users/{userNo}")
	@ApiOperation(value = "userNo 회원 정보 삭제/탈퇴")
	public ResponseEntity<Object> deleteUser(@PathVariable int userNo){
		if(us.deleteUser(userNo) == 1) {
			return new ResponseEntity<Object>("탈퇴 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("탈퇴 실패", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "users")
	@ApiOperation(value = "모든 user 리스트 조회")
	public ResponseEntity<Object> findAll() {
		List<User> uList = us.findAll();
		if (Objects.isNull(uList)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(uList, HttpStatus.OK);
		}
	}

	@GetMapping(value = "users/login")
	@ApiOperation(value = "유저 로그인")
	public ResponseEntity<Object> login(@RequestParam String email, @RequestParam String password) {
		User loginUser = us.login(email, password);
		if (Objects.isNull(loginUser)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Object>(loginUser, HttpStatus.OK);

		}
	}

	@PostMapping(value = "users/register")
	@ApiOperation(value = "유저 회원가입")
	public ResponseEntity<Object> register(@RequestBody User user) {
		User registerUser = us.signUp(user);
		if (Objects.isNull(registerUser)) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Object>(registerUser, HttpStatus.CREATED);
		}
	}
	
	@GetMapping(value = "users/awards/{userNo}")
	@ApiOperation(value = "user에 해당되는 award 리스트 조회")
	public ResponseEntity<Object> findAllByAwardUserNo(@PathVariable int userNo) {
		List<Award> aList = us.findAllByUserNo(userNo);
		if (Objects.isNull(aList)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(aList, HttpStatus.OK);
		}
	}

	/** Qualification 관련 메서드 */
	@GetMapping(value = "users/qualifications/{userNo}")
	public ResponseEntity<Object> findAllByQualificationUserNo(int userNo) {
		List<Qualification> qList = us.findAllByqUserUserNo(userNo);
		if (Objects.isNull(qList))
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Object>(qList, HttpStatus.OK);

	}

	@PostMapping(value = "users/qualifications")
	public ResponseEntity<Object> saveQualifications(@RequestBody Qualification q) {
		System.out.println(q.getGainDay() + " " + q.getGrade() + " " + q.getQualificationNo());
		System.out.println(q.getQUser());
		try {
			Qualification result = us.saveQ(q);
			if (Objects.isNull(result))
				return new ResponseEntity<Object>("자격증 정보 등록 실패", HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
		}
	}

	@PutMapping(value = "users/qualifications")
	public ResponseEntity<Object> updaetQualifications(@RequestBody Qualification q) {
		System.out.println(q.getGainDay() + " " + q.getGrade() + " " + q.getQualificationNo());
		System.out.println(q.getQUser());
		try {
			Qualification result = us.saveQ(q);
			if (Objects.isNull(result))
				return new ResponseEntity<Object>("자격증 정보 수정 실패", HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping(value = "users/qualifications")
	public ResponseEntity<Object> deleteQualifications(@RequestParam int qNo) {
		int result = us.deleteByqualificationNo(qNo);
		if (result == 1)
			return new ResponseEntity<Object>("Delete success", HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Delete Fail", HttpStatus.CONFLICT);
	}

	/** URL 관련메서드 */
	@GetMapping(value = "users/url/{userNo}")
	public ResponseEntity<Object> findAllByUrlUserNo(int userNo) {
		List<Url> uList = us.findAllByuUserUserNo(userNo);
		if (Objects.isNull(uList))
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Object>(uList, HttpStatus.OK);

	}

	@PostMapping(value = "users/urls")
	public ResponseEntity<Object> saveUrl(@RequestBody Url u) {
		System.out.println(u.getUrl() + " " + u.getUrlInfo() + " " + u.getUrlNo());
		System.out.println(u.getUUser());
		try {
			Url result = us.saveUrl(u);
			if (Objects.isNull(result))
				return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
		}
	}

	@PutMapping(value = "users/urls")
	public ResponseEntity<Object> updateUrl(@RequestBody Url u) {
		System.out.println(u.getUrl() + " " + u.getUrlInfo() + " " + u.getUrlNo());
		System.out.println(u.getUUser());
		try {
			Url result = us.saveUrl(u);
			if (Objects.isNull(result))
				return new ResponseEntity<Object>("upate fail", HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping(value = "users/urls")
	public ResponseEntity<Object> deleteUrls(@RequestParam int uNo) {
		int result = us.deleteByurlNo(uNo);
		System.out.println(result);
		if (result == 1)
			return new ResponseEntity<Object>("Delete success", HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Delete Fail", HttpStatus.CONFLICT);
	}
}
