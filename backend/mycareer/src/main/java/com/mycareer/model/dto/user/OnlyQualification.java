package com.mycareer.model.dto.user;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnlyQualification {
	int qualificationNo;

	String qualificationTitle;

	String gainDay;

	String grade;
	Integer score;
}
