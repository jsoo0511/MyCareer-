
package com.mycareer.model.dto.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mycareer.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "qualification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Qualification {

	
	@Id
	@Column(name = "qualification_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int qualificationNo;
	
	@Column(name = "q_title")
	String qualificationTitle;
	
	@Column(name = "gain_day")
	String gainDay;
	
	String grade;
	Integer score;	
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="user_no", referencedColumnName = "user_no")
	User qUser;
}

