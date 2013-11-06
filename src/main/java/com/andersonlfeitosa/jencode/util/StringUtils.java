package com.andersonlfeitosa.jencode.util;

public final class StringUtils {
	
	public static boolean isBlank(String text) {
		return text == null || "".equals(text) || "".equals(text.trim());
	}
	
	public static String toLowerCaseWithTrim(String text) {
		return isBlank(text) ? text : text.toLowerCase().trim();
	}

}
