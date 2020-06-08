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
import com.mycareer.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_img")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter	
public class ProjectImg {
	
	@Id
	@Column(name = "img_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int imgNo;
	
	String src;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="project_no", referencedColumnName = "project_no")
	Project iProject;

	public ProjectImg(String src) {
		super();
		this.src = src;
	}

	public ProjectImg(String src, Project iProject) {
		super();
		this.src = src;
		this.iProject = iProject;
	}

	
}
