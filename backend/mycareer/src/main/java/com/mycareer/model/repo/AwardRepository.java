package com.mycareer.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.user.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Integer>{

}
