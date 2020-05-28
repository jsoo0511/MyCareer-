package com.mycareer.model.dto.project;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mycareer.model.dto.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="api")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Api {
	
	@Id
	@Column(name="api_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int apiNo;
	
	@Column(name="api_name")
	String apiName;
	
	@Column(name="api_info")
	String apiInfo;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="project_no", referencedColumnName = "project_no")
	Project aProject;

}
