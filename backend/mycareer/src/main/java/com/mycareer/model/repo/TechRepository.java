package com.mycareer.model.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.project.Tech;

@Repository
public interface TechRepository extends JpaRepository<Tech, Integer>{
	Tech findByTechNo(int techNo);
	List<Tech> findBytProjectProjectNo(int projectNo);
	@Transactional
	int deleteByTechNo(int techNo);
}
