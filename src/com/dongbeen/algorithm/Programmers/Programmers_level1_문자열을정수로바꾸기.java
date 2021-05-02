package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_문자열을정수로바꾸기 {
	public int solution(String s) {
		int answer = 0;

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '-')
				continue;
			answer = answer * 10 + ch - '0';
		}
		return (s.charAt(0) == '-') ? answer * -1 : answer;
	}
}
