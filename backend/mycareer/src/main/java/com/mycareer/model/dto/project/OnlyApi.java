package com.mycareer.model.dto.project;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnlyApi {
	int apiNo;

	String apiName;

	String apiInfo;

}
