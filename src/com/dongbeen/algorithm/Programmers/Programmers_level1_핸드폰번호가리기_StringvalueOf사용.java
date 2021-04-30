package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_핸드폰번호가리기_StringvalueOf사용 {
	public String solution(String phone_number) {
		char[] chars = phone_number.toCharArray();
		for (int i = 0; i < phone_number.length() - 4; i++)
			chars[i] = '*';
		return String.valueOf(chars);
	}
}
