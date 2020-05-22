
package com.mycareer.model.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycareer.model.dto.User;
import com.mycareer.model.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository ur;
	@Override
	public User findByUserId(int userId) {
		try {
			User user=ur.findByUserId(userId);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	// 모든 유저 정보 찾기
	public List<User> findAll() {
		try {
			if(Objects.isNull(ur.findAll()))
				return null;
			else
				return ur.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
