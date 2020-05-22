package com.mycareer.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.user.Qualification;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer>{

}
