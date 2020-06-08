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
@Table(name="tech")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tech {
	
	@Id
	@Column(name="tech_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int techNo;
	
	@Column(name="tech_name")
	String techName;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="project_no", referencedColumnName = "project_no")
	Project tProject;

	public Tech(String techName, Project tProject) {
		super();
		this.techName = techName;
		this.tProject = tProject;
	}
}
