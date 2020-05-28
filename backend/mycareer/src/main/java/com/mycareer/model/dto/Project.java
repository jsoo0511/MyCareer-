package com.mycareer.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_no")
	int projectNo;

	@Column(name = "project_title")
	String projectTitle;

	@Column(name = "project_info")
	String projectInfo;

	int contribution;
	String url;

	@Column(name = "start_day")
	String startDay;
	@Column(name = "end_day")
	String endDay;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_no", referencedColumnName = "user_no")
	User pUser;
}
