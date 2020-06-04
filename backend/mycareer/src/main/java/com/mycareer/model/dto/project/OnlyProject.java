package com.mycareer.model.dto.project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OnlyProject {
	int projectNo;

	String projectTitle;

	String projectInfo;

	int contribution;
	String url;

	String startDay;
	String endDay;
}
