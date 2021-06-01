package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_짝수와홀수 {
	class Solution {
		public String solution(int num) {
			if (num % 2 == 1 || num % 2 == -1)
				return "Odd";
			return "Even";
		}
	}
}
