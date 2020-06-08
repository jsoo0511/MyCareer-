package com.mycareer.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mycareer.model.dto.Project;
import com.mycareer.model.dto.project.Api;
import com.mycareer.model.dto.project.Role;
import com.mycareer.model.dto.project.RoleDevelop;
import com.mycareer.model.dto.project.Tech;

public interface ProjectService {

	/** Project 관련 메서드 **/
	List<Project> findAllByUserNo(int userNo);

	// 프로젝트에 대한 저장 기능.
	// 하나의 프로젝트에 대해 저장하기. 수정
	Project saveProejctOne(Project project, int userNo, MultipartFile[] files);

	// 프로젝트 삭제 기능
	int deleteProject(int projectNo);

	Project findByProjectNo(int projectNo);
	/** Role 관련 메서드 */

	// role 역할을 다 가져오는 메서드
	List<Role> findAllRoleByProjectNo(int projectNo);

	// role 저장 = 등록, 수정
//	int saveRole(List<Role> rList, int projectNo);
	Role saveRole(Role role, int projectNo);
	int updateRole(Role role);
	int deleteRole(int roleNo);

	/** Role Develop 관련 메서드 */

	// role에서 개발한 내용을 가져오는 메서드
	List<RoleDevelop> findAllDevelopByProjectNo(int roleNo);

	// role develop 저장 = 등록, 수정
	int saveRoleDevelop(List<RoleDevelop> dList, int roleNo);

	/** ProjectImg 관련 메서드 */

	/** Api관련 메서드 */

	// apiNo를 통해 api하나에 대한 정보를 가져오기
	Api findByApiNo(int apiNo);

	// projectNo를 통해 project에 있는 모든 api정보 가져오기
	List<Api> findByaProjectProjectNo(int projectNo);

	// api추가, 변경
	public Api saveApi(Api api);

	// api삭제
	int deleteByApiNo(int apiNo);

	/** Tech관련 메서드 */
	// techNo를 통해 tech에 관한 정보를 가져옴
	Tech findByTechNo(int techNo);

	// projectNo를 통해 project에 있는 모든 tech정보 가져오기
	List<Tech> findBytProjectProjectNo(int projectNo);

	// tech 추가, 번경
	public Tech saveTech(Tech tech);

	// tech삭제
	int deleteByTechNo(int techNo);
}
