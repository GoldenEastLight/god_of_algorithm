package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_행렬의덧셈 {
	public int solution(int n) {
		int answer = 0;
		boolean[] sosu = new boolean[n + 1];
		int root = (int) Math.sqrt(n);

		for (int i = 2; i <= n; i++)
			sosu[i] = true;
		for (int i = 2; i <= root; i++) {
			if (sosu[i]) {
				for (int j = i; j * i <= n; j++)
					sosu[j * i] = false;
			}
		}
		for (int i = 2; i <= n; i++)
			if (sosu[i])
				answer++;
		return answer;
	}
}
