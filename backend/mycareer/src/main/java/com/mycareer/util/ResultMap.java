package com.mycareer.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResultMap<T> {

	int code;
	String message;
	T data;
	
}
