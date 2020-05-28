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

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Role {

	@Id
	@Column(name = "role_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int roleNo;
	
	@Column(name = "role_name")
	String roleName;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="project_no", referencedColumnName = "project_no")
	Project iProject;
}
