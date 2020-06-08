package com.mycareer.model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mycareer.model.dto.Project;
import com.mycareer.model.dto.User;
import com.mycareer.model.dto.project.Api;
import com.mycareer.model.dto.project.ProjectImg;
import com.mycareer.model.dto.project.Role;
import com.mycareer.model.dto.project.RoleDevelop;
import com.mycareer.model.dto.project.Tech;
import com.mycareer.model.dto.response.ProjectResponse;
import com.mycareer.model.repo.ApiRepository;
import com.mycareer.model.repo.ProjectImgRepository;
import com.mycareer.model.repo.ProjectRepository;
import com.mycareer.model.repo.RoleDevelopRepository;
import com.mycareer.model.repo.RoleRepository;
import com.mycareer.model.repo.TechRepository;
import com.mycareer.util.ResultMap;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository pr;
	@Autowired
	ProjectImgRepository pir;
	@Autowired
	UserService us;

	@Autowired
	RoleRepository rr;
	@Autowired
	RoleDevelopRepository dr;
	@Autowired
	ProjectImgRepository ir;
	@Autowired
	ImageServiceImpl is;

	@Autowired
	ApiRepository ar;

	@Autowired
	TechRepository tr;

	/** Project 관련 메서드 */

	@Override
	public List<ProjectImg> findAllByProjectNo(int projectNo) {
		try {
			List<ProjectImg> piList = pir.findAllByiProjectProjectNo(projectNo);
			return piList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// User에 해당되는 프로젝트들을 다 가져온다.
	// 대표 이미지가 표시되야 한다.
	@Override
	public List<Project> findAllByUserNo(int userNo) {
		try {
			List<Project> pList = pr.findAllBypUserUserNo(userNo);
			if (Objects.isNull(pList)) {
				return null;
			} else {
				return pList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultMap<List<ProjectResponse>> findAllProjectUserNo(int userNo) {
		try {
			List<Project> pList = pr.findAllBypUserUserNo(userNo);
			List<ProjectResponse> resList = new ArrayList<ProjectResponse>();
			for (Project project : pList) {
				resList.add(new ProjectResponse(project.getProjectNo(), project.getProjectTitle(),
						project.getProjectInfo(), project.getContribution(), project.getUrl(), project.getStartDay(),
						project.getEndDay(), project.getPUser().getUserNo(),
						pir.findAllByiProjectProjectNo(project.getProjectNo())));
			}
			return new ResultMap<List<ProjectResponse>>(200, "목록 가져오기 성공", resList);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Project findByProjectNo(int projectNo) {
		Project p = pr.findByProjectNo(projectNo);
		try {
			if (Objects.isNull(p))
				return null;
			else
				return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 프로젝트에 대한 저장 기능
	// 하나의 프로젝트만 저장하기
	@Override
	public Project saveProejctOne(Project project, List<String> techs, String role, int userNo, MultipartFile[] files) {
		try {
			User findUser = us.findByUserNo(userNo);
			project.setPUser(findUser);

			pr.save(project);
			if (!Objects.isNull(files)) {
				String imgUploadPath = File.separator + "home" + File.separator + "ubuntu";
				ResultMap<List<ProjectImg>> prImg = is.uploadFiles(files, imgUploadPath, "ProjectImg");
				for (ProjectImg pimg : prImg.getData()) {
					ProjectImg tmp = new ProjectImg(pimg.getSrc());
					tmp.setIProject(project);
					ir.save(tmp);
				}
			}
			Role tRole = new Role(role, project);
			rr.save(tRole);

			for (String tech : techs) {
				Tech tTech = new Tech(tech, project);
				tr.save(tTech);
			}
			return project;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Project updateProjectOne(Project project, List<String> techs, String role, int userNo,
			MultipartFile[] files) {
		try {

			pr.save(project);

			String imgUploadPath = File.separator + "home" + File.separator + "ubuntu";
			System.out.println("어디가 문제인가");
			if (!Objects.isNull(is.uploadFiles(files, imgUploadPath, "ProjectImg").getData())) {
				List<ProjectImg> prImg = is.uploadFiles(files, imgUploadPath, "ProjectImg").getData();
				List<ProjectImg> tradeOrigin = pir.findAllByiProjectProjectNo(project.getProjectNo());
				int oldlen = tradeOrigin.size();
				System.out.println("올드 : " + oldlen);
				int newlen = files.length;
				System.out.println("뉴 : " + newlen);
				if (oldlen < newlen) {
					for (int i = oldlen; i < newlen; i++) {
						tradeOrigin.add(new ProjectImg(prImg.get(i).getSrc(), project));
					}
				} else if (oldlen > newlen) {
					for (int i = 0; i < oldlen; i++) {
						if (i >= newlen)
							pir.delete(tradeOrigin.get(i));
					}
					tradeOrigin = tradeOrigin.subList(0, newlen);
				}
				for (int i = 0; i < tradeOrigin.size(); i++) {
					if (i < oldlen) {
						System.out.println("뭔데 : " + prImg.get(i).getSrc());
						tradeOrigin.get(i).setSrc(prImg.get(i).getSrc());
					}
				}
				pir.saveAll(tradeOrigin);
			}

			Role tRole = new Role(role, project);
			rr.save(tRole);

			for (String tech : techs) {
				Tech tTech = new Tech(tech, project);
				tr.save(tTech);
			}
			return project;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int deleteProject(int projectNo) {
		try {
			Project dProject = pr.findByProjectNo(projectNo);
			if (Objects.isNull(dProject)) {
				return 0;
			} else {
				pr.deleteById(projectNo);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/** Role 관련 메서드 */

	// 프로젝트에 해당되는 모든 역할을 가져옴
	@Override
	public List<Role> findAllRoleByProjectNo(int projectNo) {
		try {
			List<Role> pList = rr.findAllByrProjectProjectNo(projectNo);
			if (Objects.isNull(pList)) {
				return null;
			} else {
				return pList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// role 등록
	@Override
	public Role saveRole(Role role, int projectNo) {
		try {
			Role sRole = rr.findByRoleNo(role.getRoleNo());
			if (Objects.isNull(sRole)) {
				role.setRProject(pr.findByProjectNo(projectNo));
				rr.save(role);
				return role;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// role 수정
	@Override
	public int updateRole(Role role) {
		try {
			Role uRole = rr.findByRoleNo(role.getRoleNo());
			if (Objects.isNull(uRole)) {
				return 0;
			} else {
				rr.save(role);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// role 삭제
	@Override
	public int deleteRole(int roleNo) {
		try {
			Role dRole = rr.findByRoleNo(roleNo);
			if (Objects.isNull(dRole))
				return 0;
			else {
				rr.deleteById(roleNo);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/** Role Develop 관련 메서드 */

	// 해당 role에 해당되는 모든 develop 가져옴
	@Override
	public List<RoleDevelop> findAllDevelopByProjectNo(int roleNo) {
		try {
			List<RoleDevelop> dList = dr.findAllBydRoleRoleNo(roleNo);
			if (Objects.isNull(dList)) {
				return null;
			} else {
				return dList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// develop들 일괄 처리
	@Override
	public int saveRoleDevelop(List<RoleDevelop> dList, int roleNo) {
		try {
			List<RoleDevelop> all = dr.findAllBydRoleRoleNo(roleNo);
			int count = 0;
			for (int i = 0; i < all.size(); i++) {
				if (dList.get(i).getDevelopNo() == all.get(i).getDevelopNo()) {
					dr.save(dList.get(i));
					count++;
				} else {
					dr.deleteById(dList.get(i).getDevelopNo());
				}
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/** Api관련 메서드 */
	// apiNo를 통해 api하나에 대한 정보를 가져오기
	@Override
	public Api findByApiNo(int apiNo) {
		try {
			Api result = ar.findByApiNo(apiNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// projectNo를 통해 project에 있는 모든 api정보 가져오기
	@Override
	public List<Api> findByaProjectProjectNo(int projectNo) {
		try {
			List<Api> result = ar.findByaProjectProjectNo(projectNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Api saveApi(Api api) {
		try {
			Api result = ar.save(api);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// api삭제
	@Override
	public int deleteByApiNo(int apiNo) {
		try {
			int result = ar.deleteByApiNo(apiNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/** Tech관련 메서드 */

	@Override
	public Tech findByTechNo(int techNo) {
		try {
			Tech result = tr.findByTechNo(techNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Tech> findBytProjectProjectNo(int projectNo) {
		try {
			List<Tech> result = tr.findBytProjectProjectNo(projectNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tech saveTech(Tech tech) {
		try {
			Tech result = tr.save(tech);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteByTechNo(int techNo) {
		try {
			int result = tr.deleteByTechNo(techNo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
