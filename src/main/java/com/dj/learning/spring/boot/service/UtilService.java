package com.dj.learning.spring.boot.service;

import org.springframework.stereotype.Service;

import com.dj.learning.spring.boot.util.SpringCommonUtilFunctions;

@Service
public class UtilService {

	private SpringCommonUtilFunctions util;

	public UtilService(SpringCommonUtilFunctions util) {

	}

	public void callAssertCheck(String check) {
		util.assertNull(check);
	}
}
