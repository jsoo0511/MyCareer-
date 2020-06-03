package com.mycareer.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	// 유저에 해당되는 프로젝트들을 다 가져온다.
	List<Project> findAllBypUserUserNo(int userNo);

	// 불러온 프로젝트 중에 하나만 가져온다.
//	Project findByProjectNoAndpUserUserNo(int projectNo, int userNo);
	
	// 해당 no에 속하는 프로젝트를 가져온다.
	Project findByProjectNo(int projectNo);
}
