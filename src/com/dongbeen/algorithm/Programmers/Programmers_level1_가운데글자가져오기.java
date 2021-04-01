package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_가운데글자가져오기 {
	public String solution(String s) {
		String answer = "";

		char[] sToChar = s.toCharArray();
		int middle = sToChar.length / 2;
		if (sToChar.length % 2 == 1) {
			return answer = Character.toString(sToChar[middle]);
		} else {
			answer += Character.toString(sToChar[middle - 1]);
			return answer += Character.toString(sToChar[middle]);
		}
	}
}
