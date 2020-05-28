package com.mycareer.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.project.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	// 프로젝트에 해당하는 역할 리스트 가져오기
	List<Role> findAllByrProjectProjectNo(int projectNo);

	// 프로젝트에 해당하는 역할 한 개일 경우
//	Role findByrProjectProjectNo(int projectNo);
	// 한 개의 Role 검색
	Role findByRoleNo(int roleNo);

}
