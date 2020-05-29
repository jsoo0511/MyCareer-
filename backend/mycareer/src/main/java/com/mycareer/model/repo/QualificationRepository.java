package com.mycareer.model.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.user.Qualification;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer>{
	List<Qualification> findAll();
	List<Qualification> findAllByqUserUserNo(int userNo);
	@Transactional
	int deleteByqualificationNo(int qualificationNo);
}
