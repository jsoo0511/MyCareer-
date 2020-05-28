package com.mycareer.model.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.project.Api;

@Repository
public interface ApiRepository extends JpaRepository<Api, Integer>{
	Api findByApiNo(int apiNo);
	List<Api> findByaProjectProjectNo(int projectNo);
	@Transactional
	int deleteByApiNo(int apiNo);
}
