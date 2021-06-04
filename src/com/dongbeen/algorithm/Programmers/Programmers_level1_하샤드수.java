package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_하샤드수 {
	class Solution {
		public boolean solution(int x) {
			String num = String.valueOf(x);
			char[] nums = num.toCharArray();
			int numSum = 0;

			for (int i = 0; i < nums.length; i++)
				numSum += nums[i] - '0';

			if (x % numSum == 0)
				return true;
			return false;
		}
	}
}
