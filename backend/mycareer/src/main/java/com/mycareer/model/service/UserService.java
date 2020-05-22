
package com.mycareer.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.user.Award;

public interface UserService {
	User findByUserId(int userId);
	List<User> findAll();
	
	// 유저 번호에 해당하는 수상내역 가져오기
	List<Award> findAllByUserId(int userId);
}
