package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_콜라츠추측 {
	class Solution {
		public int solution(int num) {
			if (num == 1)
				return 0;

			int repeatCnt = 0;
			long no = (long) num;

			while (repeatCnt++ < 500) {
				if (no % 2 == 0)
					no /= 2;
				else
					no = no * 3 + 1;

				if (no == 1)
					return repeatCnt;
			}
			return -1;
		}
	}
}
