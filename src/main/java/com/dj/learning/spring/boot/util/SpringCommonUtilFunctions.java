package com.dj.learning.spring.boot.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component
public class SpringCommonUtilFunctions {

	public String springAssertFunctionality(String checkString) {

		// 1. Assert Not null > isNull(), isNotNull(), isTrue(), hasText() and many
		// more; Throws IllegalArgumentException
		String str = null;
		Assert.isNull(str, "str must be null");
		Assert.isNull(str, () -> "str must be null");
		Assert.notNull(checkString, "str cannot be null");

		// 2. Asserting Collections are empty or not > isEmpty() > its empty even if you
		// initialize with new ArrayList. null also gives empty
		List<String> nullList = null;
		List<String> list = new ArrayList<String>();
		list.add("D");
		Map<String, String> map = new HashMap<String, String>();
		map.put("A", "Apple");
		Assert.notEmpty(list, "list cannot be empty");
		Assert.notEmpty(list, () -> "list cannot be empty");
		Assert.notEmpty(map, "map cannot be empty");

		return "Good, string {} is not null" + checkString;
	}

	public String springStringUtils(String word) {
		// 1. hasLength()
		if (!StringUtils.hasLength("")) { // checks for empty or blank. isEmpty() is deprecated
			System.out.println("String length verified");
		}

		// 2. trimAllWhitespace > prints: codewithOliverFostertest
		String trim = StringUtils.trimAllWhitespace(" code with Oliver Foster test ");
		System.out.println(trim);

		// 3. startsWithIgnoreCase, endsWithIgnoreCase
		System.out.println(StringUtils.startsWithIgnoreCase("code with Oliver Foster", " Oliver Foster"));
		System.out.println(StringUtils.endsWithIgnoreCase("code with Oliver Foster", "Oliver Foster"));

		// 4. Concatenating collections > collectionToCommaDelimitedString
		   List<String> list = new ArrayList<>();
		   list.add("a");
		   list.add("b");
		   list.add("c");
		   System.out.println(StringUtils.collectionToCommaDelimitedString(list));
		// 5. countOccurrencesOf(), capitalize(), many more
		return "String is verified";
	}

}
