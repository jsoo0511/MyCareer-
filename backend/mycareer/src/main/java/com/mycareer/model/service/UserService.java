
package com.mycareer.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.user.Award;
import com.mycareer.model.dto.user.Qualification;
import com.mycareer.model.dto.user.Url;
import com.mycareer.model.dto.user.Career;
import com.mycareer.model.dto.user.Language;

public interface UserService {

	/** User 관련 메서드 **/
	
	User findByUserNo(int userNo);
	List<User> findAll();
	User login(String email, String password);
	User signUp(User user);
	
	// 유저 번호에 해당하는 수상내역 가져오기
	List<Award> findAllByUserNo(int userNo);
	List<Qualification> findAllByqUserUserNo(int userNo);
	List<Url> findAllByuUserUserNo(int userNo);
	int deleteByqualificationNo(int qualificationNo);
	int deleteByurlNo(int urlNo);
	Qualification saveQ(Qualification q);
	Url saveUrl(Url u);
	
	
	
	/** Career 관련 메서드 **/
	// 전체 조회
	List<Career> findAllByCareerUserNo(int userNo);
	
	// 입력
	Object inserIntoCareer(Career career, int userNo);
	
	// 삭제
	Object deleteAll(int careerNo);
	
	
	/** Language 관련 메서드 **/
	// 전체 조회
	List<Language> findAllByLanguageUserNo(int userNo);
	
	// 추가
	// 사용자 아이디
	Object insertIntoLanguage(Language lang, int userNo);
	
	// 삭제
	// 단일 삭제 or 다중 삭제
	Object delete(int languageNo);
}
