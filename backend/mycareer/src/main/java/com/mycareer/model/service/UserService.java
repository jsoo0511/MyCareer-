
package com.mycareer.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycareer.model.dto.User;

public interface UserService {
	User findByUserId(int userId);
	List<User> findAll();
}
