package com.dj.learning.spring.boot.util;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class SpringCommonUtilFunctions {

	public String assertNull(String checkString) {
		String str = null;
		Assert.isNull(str, "str must be null");
		Assert.isNull(str, () -> "str must be null");
		Assert.notNull(checkString, "str cannot be null");
		return "Good, string {} is not null"+ checkString;
	}

}
