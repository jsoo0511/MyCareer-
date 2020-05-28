package com.mycareer.model.service;

import java.util.List;

import com.mycareer.model.dto.project.Api;
import com.mycareer.model.dto.project.Tech;

public interface ProjectService {

	/**Api관련 메서드*/

	//apiNo를 통해 api하나에 대한 정보를 가져오기
	Api findByApiNo(int apiNo);
	
	//projectNo를 통해 project에 있는 모든 api정보 가져오기
	List<Api> findByaProjectProjectNo(int projectNo);
	
	//api추가, 변경
	public Api saveApi(Api api);
	
	//api삭제
	int deleteByApiNo(int apiNo);
	
	/**Tech관련 메서드*/
	//techNo를 통해 tech에 관한 정보를 가져옴
	Tech findByTechNo(int techNo);
	
	//projectNo를 통해 project에 있는 모든 tech정보 가져오기
	List<Tech> findBytProjectProjectNo(int projectNo);
	
	//tech 추가, 번경
	public Tech saveTech(Tech tech);
	
	//tech삭제
	int deleteByTechNo(int techNo);
}
