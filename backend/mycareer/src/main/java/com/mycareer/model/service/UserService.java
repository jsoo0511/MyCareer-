
package com.mycareer.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.user.Award;
import com.mycareer.model.dto.user.Career;
import com.mycareer.model.dto.user.Language;

public interface UserService {
	User findByUserId(int userId);
	
	/** 유저 관련 메서드 **/
	List<User> findAll();
	
	boolean login(String email, String password);
	
	// 유저 번호에 해당하는 수상내역 가져오기
	List<Award> findAllByUserId(int userId);
	
	
	
	/** Career 관련 메서드 **/
	// 전체 조회
	List<Career> findAllByCareerUserId(int userId);
	
	// 입력
	Object inserIntoCareer(Career career, int userId);
	
	// 삭제
	Object deleteAll(int careerId);
	
	
	/** Language 관련 메서드 **/
	// 전체 조회
	List<Language> findAllByLanguageUserId(int userId);
	
	// 추가
	// 사용자 아이디
	Object insertIntoLanguage(Language lang, int userId);
	
	// 삭제
	// 단일 삭제 or 다중 삭제
	Object delete(int languageId);
}
