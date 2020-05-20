
package com.mycareer.model.service;

import org.springframework.stereotype.Service;

import com.mycareer.model.dto.User;

public interface UserService {
	User findByUserId(int userId);
}
