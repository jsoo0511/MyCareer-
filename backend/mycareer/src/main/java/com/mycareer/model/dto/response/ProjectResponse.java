package com.mycareer.model.dto.response;

import java.util.List;

import com.mycareer.model.dto.project.ProjectImg;

public class ProjectResponse {

	int projectNo;
	String projectTitle;
	String projectInfo;

	int contribution;
	String url;

	String startDay;
	String endDay;

	int userNo;

	List<ProjectImg> pimgList;

}
