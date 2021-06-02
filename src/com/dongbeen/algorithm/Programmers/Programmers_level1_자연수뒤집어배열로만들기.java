package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_자연수뒤집어배열로만들기 {
	class Solution {
		public int[] solution(long n) {
			String s = String.valueOf(n);
			char[] chars = s.toCharArray();
			int[] answer = new int[chars.length];

			for (int i = s.length() - 1, j = 0; i >= 0; i--, j++)
				answer[j] = chars[i] - '0';
			return answer;
		}
	}
}
