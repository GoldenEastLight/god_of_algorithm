package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_정수제곱근판별 {
	class Solution {
		public long solution(long n) {
			if (Math.sqrt(n) % 1 == 0)
				return (long) Math.pow(Math.sqrt(n) + 1, 2);
			return -1;
		}
	}
}
