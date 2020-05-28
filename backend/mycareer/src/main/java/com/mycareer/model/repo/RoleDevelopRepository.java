package com.mycareer.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.project.RoleDevelop;

@Repository
public interface RoleDevelopRepository extends JpaRepository<RoleDevelop, Integer> {

	// 역할 리스트 가져오기 - 역할과 연동
	List<RoleDevelop> findAllBydRoleRoleNo(int roleNo);

}
