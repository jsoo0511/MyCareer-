package com.mycareer.model.service;

import com.mycareer.model.dto.User;

public interface UserService {

	User findByUserId(int userId);
}
