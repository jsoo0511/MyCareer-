
package com.mycareer.controller;

<<<<<<< HEAD
import java.io.IOException;
=======
import java.util.HashMap;
import java.util.LinkedList;
>>>>>>> de742809fc685cd7f1544574a2c804573393c296
import java.util.List;
import java.util.Map;
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
import org.springframework.web.multipart.MultipartFile;

import com.mycareer.model.dto.Project;
import com.mycareer.model.dto.User;
import com.mycareer.model.dto.project.Api;
import com.mycareer.model.dto.project.OnlyApi;
import com.mycareer.model.dto.project.OnlyProject;
import com.mycareer.model.dto.project.OnlyRole;
import com.mycareer.model.dto.project.OnlyTech;
import com.mycareer.model.dto.project.Role;
import com.mycareer.model.dto.project.Tech;
import com.mycareer.model.dto.user.Award;
import com.mycareer.model.dto.user.Career;
import com.mycareer.model.dto.user.Language;
import com.mycareer.model.dto.user.OnlyAward;
import com.mycareer.model.dto.user.OnlyCareer;
import com.mycareer.model.dto.user.OnlyLanguage;
import com.mycareer.model.dto.user.OnlyQualification;
import com.mycareer.model.dto.user.OnlyUrl;
import com.mycareer.model.dto.user.Qualification;
import com.mycareer.model.dto.user.Url;
import com.mycareer.model.service.ImageServiceImpl;
import com.mycareer.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService us;
	
	@GetMapping(value="user")
	@ApiOperation(value="userNo로 회원의 모든 정보 조회")
	public ResponseEntity<Map<String,Object>> User(@RequestParam int userNo){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			//Project에 관한 모든 정보들 가져옴
			List<Award> aList=us.findAllByUserNo(userNo);
			List<Qualification> qList = us.findAllByqUserUserNo(userNo);
			List<Url> uList = us.findAllByuUserUserNo(userNo);
			List<Career> cList=us.findAllByCareerUserNo(userNo);
			List<Language> lList=us.findAllByLanguageUserNo(userNo);
			User user=us.findByUserNo(userNo);
			
			//불필요한 정보들 제거후 필요한 정보들만 다시 추출
			List<OnlyAward> oaList=new LinkedList<OnlyAward>();
			List<OnlyQualification> oqList=new LinkedList<OnlyQualification>();
			List<OnlyUrl> ouList=new LinkedList<OnlyUrl>();
			List<OnlyCareer> ocList=new LinkedList<OnlyCareer>();
			List<OnlyLanguage> olList=new LinkedList<OnlyLanguage>();
			for(Award a:aList)
				oaList.add(new OnlyAward(a.getAwardNo(),a.getATitle(),a.getAInfo(),a.getGainDay(),a.getGrade()));
			for(Qualification q:qList)
				oqList.add(new OnlyQualification(q.getQualificationNo(),q.getQualificationTitle(),q.getGainDay(),q.getGrade(),q.getScore()));
			for(Url url:uList)
				ouList.add(new OnlyUrl(url.getUrlNo(),url.getUrl(),url.getUrlInfo()));
			for(Career c:cList)
				ocList.add(new OnlyCareer(c.getCareerNo(),c.getCareerTitle(),c.getStart(),c.getEnd(),c.getDetail()));
			for(Language l:lList)
				olList.add(new OnlyLanguage(l.getLanguageNo(),l.getLanguageName(),l.getLevel(),l.getReason()));
			resultMap.put("User", user);
			resultMap.put("Award", oaList);
			resultMap.put("Qualification",oqList);
			resultMap.put("Url", ouList);
			resultMap.put("Career",ocList);
			resultMap.put("Language",olList);
			return new ResponseEntity<>(resultMap,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Autowired
	private ImageServiceImpl is;

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
	public ResponseEntity<Object> updateUser(@PathVariable int userNo, @RequestBody User user, MultipartFile profile) throws IOException, Exception {
		User tUser = us.findByUserNo(userNo);
		if (Objects.isNull(tUser)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_ACCEPTABLE);
		} else {
			String file = is.upload(profile, "home\\ubuntu", "profile").getSrc();
			return new ResponseEntity<Object>(us.updateUser(user,file), HttpStatus.OK);
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
	public ResponseEntity<Object> register(@RequestBody User user, MultipartFile profile) throws IOException, Exception {
		User registerUser = us.findByUserNo(user.getUserNo());
		if (Objects.isNull(registerUser)) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		} else {
			String file = is.upload(profile, "home\\ubuntu", "profile").getSrc();
			return new ResponseEntity<Object>(us.signUp(user, file), HttpStatus.CREATED);
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
	
	@PostMapping(value = "img/{userNo}")
	public ResponseEntity<Object> addImg(@PathVariable int userNo, MultipartFile file) throws IOException, Exception{
		String profile = is.upload(file, "home\\ubuntu", "profile").getSrc();
		User user = us.findByUserNo(userNo);
		user.setProfile(profile);
//		us.updateUser(user);
		return new ResponseEntity<Object>(user, HttpStatus.OK);
		
	}

	/**Career 관련 메서드*/
	@GetMapping("users/career/{userNo}")
	public ResponseEntity<Object> findAllByCareerUserNo(@PathVariable int userNo){
		List<Career> cList = us.findAllByCareerUserNo(userNo);
		if (Objects.isNull(cList))
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Object>(cList, HttpStatus.OK);

	}
	
	
	/**Language 관련 메서드*/
	@GetMapping("users/language/{userNo}")
	public ResponseEntity<Object> findAllByLanguageUserNo(@PathVariable int userNo){
		List<Language> lList = us.findAllByLanguageUserNo(userNo);
		if (Objects.isNull(lList))
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Object>(lList, HttpStatus.OK);
	}

}
