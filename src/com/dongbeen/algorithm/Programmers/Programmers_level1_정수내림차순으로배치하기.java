package com.dongbeen.algorithm.Programmers;

import java.util.Arrays;

public class Programmers_level1_정수내림차순으로배치하기 {
	public long solution(long n) {
		long answer = 0;
		String num = String.valueOf(n);
		String desc = "";
		char[] arr = new char[num.length()];

		for (int i = 0; i < num.length(); i++)
			arr[i] += num.charAt(i);

		Arrays.sort(arr);

		for (int i = arr.length - 1; i >= 0; i--)
			desc += arr[i];

		answer = Long.parseLong(desc);

		return answer;
	}
}
