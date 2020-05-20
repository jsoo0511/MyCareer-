package com.mycareer.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mycareer.model.dto.user.Award;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	Integer userId;
	
	
	String email;
	String password;
	String name;
	
	String birth;
	String gender;
	String job;
	String phone;
	String provider;
	
//	String created;
//	String deleted;
	
//	@OneToMany(mappedBy = "aUser",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
//	Award uAward;
}
