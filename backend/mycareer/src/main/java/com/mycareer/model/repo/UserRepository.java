package com.mycareer.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUserId(int userId);
	User findByEmail(String email);

	List<User> findUsers();

}
