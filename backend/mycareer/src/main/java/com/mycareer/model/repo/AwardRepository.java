package com.mycareer.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.user.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Integer>{

	// 모든 결과를 가져와야 되는데, 해당하는 유저의 것만 가져와야 된다.
	List<Award> findAllByaUserUserId(int userId);
}

