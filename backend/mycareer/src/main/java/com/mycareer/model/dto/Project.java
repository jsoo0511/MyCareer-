package com.mycareer.model.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "projectNo")
public class Project implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_no")
	int projectNo;

	public Project(String projectTitle, String projectInfo, int contribution, String url, String startDay,
			String endDay) {
		super();
		this.projectTitle = projectTitle;
		this.projectInfo = projectInfo;
		this.contribution = contribution;
		this.url = url;
		this.startDay = startDay;
		this.endDay = endDay;
	}

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
	@ToString.Exclude
	User pUser;
}
