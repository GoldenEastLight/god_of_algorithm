package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_이상한문자만들기 {
	public String solution(String s) {
		char[] chars = s.toCharArray();
		int index = 0;

		for (int i = 0; i < s.length(); i++) {
			if (chars[i] == ' ') {
				index = 0;
				continue;
			}
			chars[i] = index % 2 == 0 ? Character.toUpperCase(chars[i]) : Character.toLowerCase(chars[i]);
			index++;
		}
		return String.valueOf(chars);
	}
}
