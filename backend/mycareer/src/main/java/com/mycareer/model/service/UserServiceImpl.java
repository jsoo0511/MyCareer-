
package com.mycareer.model.service;

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

}
