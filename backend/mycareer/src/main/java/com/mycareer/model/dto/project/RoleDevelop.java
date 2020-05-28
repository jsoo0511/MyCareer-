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
@Table(name = "role_develop")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoleDevelop {

	
	@Id
	@Column(name = "develop_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int developNo;
	
	@Column(name = "develop_name")
	String developName;
	
	@Column(name = "develop_info")
	String developInfo;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="role_no", referencedColumnName = "role_no")
	Role dRole;
}
