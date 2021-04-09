package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_문자열다루기기본 {
	public boolean solution(String s) {
		char[] chars = s.toCharArray();
		if (s.length() == 4 || s.length() == 6) {
			for (int i = 0; i < s.length(); i++) {
				if (chars[i] > '9' || '0' > chars[i])
					return false;
			}
			return true;
		} else
			return false;
	}
}
