package com.mycareer.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycareer.model.dto.Project;
import com.mycareer.model.dto.User;
import com.mycareer.model.dto.project.Api;
import com.mycareer.model.dto.project.OnlyApi;
import com.mycareer.model.dto.project.OnlyProject;
import com.mycareer.model.dto.project.OnlyRole;
import com.mycareer.model.dto.project.OnlyTech;
import com.mycareer.model.dto.project.ProjectImg;
import com.mycareer.model.dto.project.Role;
import com.mycareer.model.dto.project.RoleDevelop;
import com.mycareer.model.dto.project.Tech;
import com.mycareer.model.dto.response.ProjectResponse;
import com.mycareer.model.service.ProjectService;
import com.mycareer.model.service.UserService;
import com.mycareer.util.ResultMap;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;

@RestController
@CrossOrigin("*")
public class ProjectController {

	@Autowired
	ProjectService ps;
	@Autowired
	UserService us;

	/** Project 관련 */
	@GetMapping("project")
	@ApiOperation(value="projectNo에해당하는 모든 project정보 출력")
	public ResponseEntity<Map<String,Object>> Project(@RequestParam int projectNo){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			//Project에 관한 모든 정보들 가져옴
			List<Role> rList=ps.findAllRoleByProjectNo(projectNo);
			List<Tech> tList = ps.findBytProjectProjectNo(projectNo);
			List<Api> aList = ps.findByaProjectProjectNo(projectNo);
			List<ProjectImg> piList = ps.findAllByProjectNo(projectNo);
			Project p=ps.findByProjectNo(projectNo);
			
			//불필요한 정보들 제거후 필요한 정보들만 다시 추출
			OnlyProject project=new OnlyProject(p.getProjectNo()
					,p.getProjectTitle(),p.getProjectInfo(),p.getContribution(),
					p.getUrl(),p.getStartDay(),p.getEndDay());
			List<OnlyApi> oaList=new LinkedList<OnlyApi>();
			List<OnlyRole> orList=new LinkedList<OnlyRole>();
			List<OnlyTech> otList=new LinkedList<OnlyTech>();
			for(Api a:aList)
				oaList.add(new OnlyApi(a.getApiNo(),a.getApiName(),a.getApiInfo()));
			for(Role r:rList)
				orList.add(new OnlyRole(r.getRoleNo(),r.getRoleName()));
			for(Tech t:tList)
				otList.add(new OnlyTech(t.getTechNo(),t.getTechName()));
			resultMap.put("Project", project);
			resultMap.put("Role", orList);
			resultMap.put("Api",oaList);
			resultMap.put("Tech", otList);
			resultMap.put("ProjectImg", piList);
			return new ResponseEntity<>(resultMap,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("project/{userNo}")
	@ApiOperation(value = "userNo로 해당 유저의 프로젝트들을 불러온다.")
	public ResponseEntity<Object> findAllByProject(@PathVariable int userNo) {
//		List<Project> pList = ps.findAllByUserNo(userNo);
		ResultMap<List<ProjectResponse>> pList = ps.findAllProjectUserNo(userNo);
		if (Objects.isNull(pList)) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Object>(pList.getData(), HttpStatus.OK);
		}
	}

	@GetMapping("project/info/{projectNo}")
	@ApiOperation(value = "projectNo로  간단한 프로젝트들을 불러온다.")
	public ResponseEntity<Object> findByProjectNo(@PathVariable int projectNo) {
		Project p = ps.findByProjectNo(projectNo);
		if (Objects.isNull(p)) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Object>(p, HttpStatus.OK);
		}
	}
	
	@PostMapping("project/{userNo}")
	@ApiOperation(value = "해당 유저에서 프로젝트 등록")
	public ResponseEntity<Object> saveProject(
			@RequestParam String projectTitle,
			@RequestParam String startDate, 
			@RequestParam String endDate, @RequestParam String projectInfo, @RequestParam int members,
			@RequestParam String url,
			@RequestParam String role,
			@RequestParam List<String> techs, @RequestParam int templateNo,
			@RequestParam MultipartFile[] files, @PathVariable int userNo) {
		
		if (!Objects.isNull(us.findByUserNo(userNo))) {
			Project newProject = new Project(projectTitle, projectInfo, members, url, startDate, endDate, templateNo);
			return new ResponseEntity<Object>(ps.saveProejctOne(newProject, techs, role, userNo, files), HttpStatus.OK);
		}else
			return new ResponseEntity<Object>(null, HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping("project/{userNo}")
	@ApiOperation(value = "해당 유저에 대한 프로젝트 수정")
	public ResponseEntity<Object> updateProject(@RequestParam String projectTitle,
			@RequestParam String startDate, 
			@RequestParam String endDate, @RequestParam String projectInfo, @RequestParam(name = "인원 members") int members,
			@RequestParam String url,
			@RequestParam(name = "맡은 역할 role") String role,
			@RequestParam(name = "사용한 기술 techs") List<String> techs, @RequestParam int templateNo,
			@RequestParam MultipartFile[] files, 
			@PathVariable int userNo, @RequestParam int projectNo) {
		Project newProject = ps.findByProjectNo(projectNo);
		newProject.setProjectTitle(projectTitle);
		newProject.setProjectInfo(projectInfo);
		newProject.setContribution(members);
		newProject.setEndDay(endDate);
		newProject.setStartDay(startDate);
		newProject.setTemplateNo(templateNo);
		newProject.setUrl(url);
		
		return new ResponseEntity<Object>(ps.updateProjectOne(newProject, techs, role, userNo, files), HttpStatus.OK);
	}

	/** Role 관련 */
	@GetMapping("project/role/{projectNo}")
	@ApiOperation(value = "프로젝트에 해당하는 role을 가져오기")
	public ResponseEntity<Object> findAllByRole(@PathVariable int projectNo) {
		if (Objects.isNull(ps.findAllRoleByProjectNo(projectNo))) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Object>(ps.findAllRoleByProjectNo(projectNo), HttpStatus.OK);
		}
	}

	@PostMapping("project/role/{projectNo}")
	@ApiOperation(value = "project에 해당되는 role 추가")
	public ResponseEntity<Object> saveRole(@PathVariable int projectNo, @RequestBody Role role) {
		Role sRole = ps.saveRole(role, projectNo);
		if (Objects.isNull(sRole)) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Object>(sRole, HttpStatus.OK);
		}
	}

	@PutMapping("project/role/{projectNo}")
	@ApiOperation(value = "project에 해당되는 role 중 하나를 수정 ")
	public ResponseEntity<Object> updateRole(@PathVariable int projectNo, @RequestBody Role role) {
		int result = ps.updateRole(role);
		return result == 1 ?
				new ResponseEntity<Object>("수정 완료", HttpStatus.OK) : new ResponseEntity<Object>("수정 실패", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("project/role/{projectNo}")
	@ApiOperation(value = "project에 해당 되는 role 중의 하나 삭제")
	public ResponseEntity<Object> deleteRole(@PathVariable int projectNo, int roleNo) {
		int result = ps.deleteRole(roleNo);
		return result == 1 ?
				new ResponseEntity<Object>("삭제 완료", HttpStatus.OK) : new ResponseEntity<Object>("삭제 실패", HttpStatus.BAD_REQUEST);
	}

	/** Role Develop 관련 */

	/** ProjectImg 관련 */

	/** Api 관련 컨트롤러 */
	@GetMapping("project/apiNo/{apiNo}")
	@ApiOperation("apiNo로 해당 api에 대한 정보만 출력")
	public ResponseEntity<Object> findByApiNo(@PathVariable int apiNo) {
		Api api = ps.findByApiNo(apiNo);
		if (Objects.isNull(api)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(api, HttpStatus.OK);
		}
	}

	@GetMapping("project/aProjectNo/{projectNo}")
	@ApiOperation("projectNo 해당 project에 대한 모든 api출력")
	public ResponseEntity<Object> findByaProjectProjectNo(@PathVariable int projectNo) {
		List<Api> api = ps.findByaProjectProjectNo(projectNo);
		if (Objects.isNull(api)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(api, HttpStatus.OK);
		}
	}

	@PostMapping("project/api")
	public ResponseEntity<Object> saveApi(@RequestBody Api api) {
		try {
			Api result = ps.saveApi(api);
			if (Objects.isNull(result))
				return new ResponseEntity<Object>("Api 정보 등록 실패", HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
		}
	}

	@PutMapping("project/api")
	public ResponseEntity<Object> updateApi(@RequestBody Api api) {
		try {
			Api result = ps.saveApi(api);
			if (Objects.isNull(result))
				return new ResponseEntity<Object>("Api 정보 수정 실패", HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("project/api")
	public ResponseEntity<Object> deleteApi(@RequestParam int apiNo) {
		int result = ps.deleteByApiNo(apiNo);
		if (result == 1)
			return new ResponseEntity<Object>("Delete success", HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Delete Fail", HttpStatus.CONFLICT);
	}

	/** Tech관련 컨트롤러 */
	@GetMapping("project/techNo/{techNo}")
	@ApiOperation("apiNo로 해당 api에 대한 정보만 출력")
	public ResponseEntity<Object> findByTechNo(@PathVariable int techNo) {
		Tech tech = ps.findByTechNo(techNo);
		if (Objects.isNull(tech)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(tech, HttpStatus.OK);
		}
	}

	@GetMapping("project/tProjectNo/{projectNo}")
	@ApiOperation("projectNo 해당 project에 대한 모든 tech출력")
	public ResponseEntity<Object> findBytProjectProjectNo(@PathVariable int projectNo) {
		List<Tech> api = ps.findBytProjectProjectNo(projectNo);
		if (Objects.isNull(api)) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(api, HttpStatus.OK);
		}
	}

	@PostMapping("project/tech")
	public ResponseEntity<Object> saveTech(@RequestBody Tech tech) {
		try {
			Tech result = ps.saveTech(tech);
			if (Objects.isNull(result))
				return new ResponseEntity<Object>("Tech 정보 등록 실패", HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
		}
	}

	@PutMapping("project/tech")
	public ResponseEntity<Object> updateTech(@RequestBody Tech tech) {
		try {
			Tech result = ps.saveTech(tech);
			if (Objects.isNull(result))
				return new ResponseEntity<Object>("Tech 정보 수정 실패", HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("project/tech")
	public ResponseEntity<Object> deleteTech(@RequestParam int techNo) {
		int result = ps.deleteByTechNo(techNo);
		if (result == 1)
			return new ResponseEntity<Object>("Delete success", HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Delete Fail", HttpStatus.CONFLICT);
	}

}
