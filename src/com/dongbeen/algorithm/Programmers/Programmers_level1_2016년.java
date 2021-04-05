package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_2016년 {
	public String solution(int a, int b) {
		String answer = "";
		String[] day = { "THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED" };
		int[] month = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int afterDays = b;

		for (int i = 0; i < a - 1; i++) {
			afterDays += month[i];
		}
		answer = day[afterDays % 7];

		return answer;
	}
}
