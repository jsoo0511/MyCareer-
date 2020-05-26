package com.mycareer.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.user.Career;

@Repository
public interface CareerRepository extends JpaRepository<Career, Integer>{

	// 단일 개수로 찾기
	Career findByCareerNo(int careerNo);
	
	// 유저 아이디로 모든 경력 찾기
	List<Career> findBycUserUserNo(int userNo);
	
	
}

