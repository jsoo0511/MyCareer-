package com.mycareer.model.dto.response;

import java.io.Serializable;
import java.util.List;

import com.mycareer.model.dto.project.ProjectImg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProjectResponse implements Serializable{

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
