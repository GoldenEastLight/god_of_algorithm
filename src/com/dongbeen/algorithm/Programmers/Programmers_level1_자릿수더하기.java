package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_자릿수더하기 {
	public int solution(int n) {
		int answer = 0;
		String nString = String.valueOf(n);
		char[] chars = nString.toCharArray();

		for (int i = 0; i < chars.length; i++)
			answer += chars[i] - '0';

		return answer;
	}
}
