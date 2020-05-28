package com.mycareer.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.project.ProjectImg;

@Repository
public interface ProjectImgRepository extends JpaRepository<ProjectImgRepository, Integer>{

	//이미지 전체 조회
	// findAll
	
	// 이미지 프로젝트 넘버로 가져오기
	List<ProjectImg> findAllByiProjectProjectNo(int projectNo);
	
	// 이미지 하나만 가져오기 - 프로젝트 넘버
	ProjectImg findByImgNo(int imgNo);
}
