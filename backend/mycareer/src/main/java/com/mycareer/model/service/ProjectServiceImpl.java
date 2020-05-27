package com.mycareer.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycareer.model.dto.project.Api;
import com.mycareer.model.dto.project.Tech;
import com.mycareer.model.repo.ApiRepository;
import com.mycareer.model.repo.TechRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ApiRepository ar;
	
	@Autowired
	TechRepository tr;
	
	/**Api관련 메서드*/
	//apiNo를 통해 api하나에 대한 정보를 가져오기
	@Override
	public Api findByApiNo(int apiNo) {
		try {
			Api result=ar.findByApiNo(apiNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//projectNo를 통해 project에 있는 모든 api정보 가져오기
	@Override
	public List<Api> findByaProjectProjectNo(int projectNo) {
		try {
			List<Api> result=ar.findByaProjectProjectNo(projectNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Api saveApi(Api api) {
		try {
			Api result=ar.save(api);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//api삭제
	@Override
	public int deleteByApiNo(int apiNo) {
		try {
			int result=ar.deleteByApiNo(apiNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public Tech findByTechNo(int techNo) {
		try {
			Tech result=tr.findByTechNo(techNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Tech> findBytProjectProjectNo(int projectNo) {
		try {
			List<Tech> result=tr.findBytProjectProjectNo(projectNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Tech saveTech(Tech tech) {
		try {
			Tech result=tr.save(tech);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int deleteByTechNo(int techNo) {
		try {
			int result=tr.deleteByTechNo(techNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


}
