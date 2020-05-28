package com.mycareer.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.project.Api;
import com.mycareer.model.dto.project.Tech;
import com.mycareer.model.service.ProjectService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
public class ProjectController {

	@Autowired
	ProjectService ps;

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
