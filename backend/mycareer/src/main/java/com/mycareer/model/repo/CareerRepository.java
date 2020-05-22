package com.mycareer.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.user.Career;

@Repository
public interface CareerRepository extends JpaRepository<Career, Integer>{

}
