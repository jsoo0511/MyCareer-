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
public class OnlyAward {
	int awardNo;

	String aTitle;

	String aInfo;

	String gainDay;

	String grade;
}
