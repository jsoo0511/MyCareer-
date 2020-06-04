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
public class OnlyCareer {
	int careerNo;

	String careerTitle;

	String start;
	String end;

	String detail;
}
