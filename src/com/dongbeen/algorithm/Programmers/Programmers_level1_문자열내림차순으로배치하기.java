package com.dongbeen.algorithm.Programmers;

import java.util.Arrays;
import java.util.Collections;

public class Programmers_level1_문자열내림차순으로배치하기 {
	public String solution(String s) {
		Character[] chars = new Character[s.length()];

		for (int i = 0; i < s.length(); i++) {
			chars[i] = s.charAt(i);
		}

		Arrays.sort(chars, Collections.reverseOrder());
		String answer = "";
		for (int i = 0; i < chars.length; i++) {
			answer += chars[i];
		}
		return answer;
	}
}
