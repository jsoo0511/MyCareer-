
package com.mycareer.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.user.Award;
import com.mycareer.model.dto.user.Qualification;
import com.mycareer.model.dto.user.Url;
import com.mycareer.model.dto.user.Career;
import com.mycareer.model.dto.user.Language;

public interface UserService {

	/** User 관련 메서드 **/
	
	// USER가 존재하는지 확인
	User findByUserNo(int userNo);
	List<User> findAll();
	// 로그인
	User login(String email, String password);
	// 회원가입
	User signUp(User user, String profile);
	// 회원 정보 수정
	User updateUser(User user, String profile);
	// 회원 정보 삭제(탈퇴)
	int deleteUser(int userNo);
	
	// 유저 번호에 해당하는 수상내역 가져오기
	List<Award> findAllByUserNo(int userNo);
	//int deleteByqualificationNo(int qualificationNo);
	
	/**Url 관련 메서드*/
	//전체 조회
	List<Url> findAllByuUserUserNo(int userNo);
	
	//입력, 수정
	Url saveUrl(Url u);
	
	//삭제
	int deleteByurlNo(int urlNo);
	
	/**Qualificatino 관련 메서드*/
	//전체 조회
	List<Qualification> findAllByqUserUserNo(int userNo);
	
	//입력, 수정
	Qualification saveQ(Qualification q);
	
	//삭제
	int deleteByqualificationNo(int qualificationNo);
	
	/** Career 관련 메서드 **/
	// 전체 조회
	List<Career> findAllByCareerUserNo(int userNo);
	
	// 입력
	Object inserIntoCareer(Career career, int userNo);
	
	// 삭제
	Object deleteCareer(int careerNo);
	
	
	/** Language 관련 메서드 **/
	// 전체 조회
	List<Language> findAllByLanguageUserNo(int userNo);
	
	// 추가
	// 사용자 아이디
	Object insertIntoLanguage(Language lang, int userNo);
	
	// 삭제
	// 단일 삭제 or 다중 삭제
	Object deleteLanguage(int languageNo);
}
