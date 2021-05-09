package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_약수의합 {
	public int solution(int n) {
		int answer = 0;
		for (int i = 1; i <= n / 2; i++)
			if (n % i == 0)
				answer += i;
		return answer + n;
	}
}
