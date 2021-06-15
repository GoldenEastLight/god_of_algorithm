package com.dongbeen.algorithm.Programmers;

public class Programmers_test_웅덩이만들기 {
	public static void main(String[] args) {
		int[] bricks = { 1, 2, 5, 3, 1, 0, 2, 3 };
		int n = 6;
		int k = 3;
		// result == 5;

		Programmers_test_웅덩이만들기 m = new Programmers_test_웅덩이만들기();
		System.out.println(m.solution(bricks, n, k));
	}

	public int solution(int[] bricks, int n, int k) {
		int answer = 0;

		for (int i = 0; i < k - 1; i++) {
			answer = buildBricks(bricks, n, answer);
		}

		return answer;
	}

	public int buildBricks(int[] bricks, int n, int answer) {
		int blanc = 0;
		int blancIdx = 0;

		for (int i = 1; i < bricks.length - 1; i++) {
			if (bricks[i] != n && bricks[i - 1] < n && bricks[i + 1] < n) {
				if (blanc < bricks[i]) {
					blanc = bricks[i];
					blancIdx = i;
				}
			}
		}
		answer += n - blanc;
		bricks[blancIdx] += n - blanc;

		return answer;
	}
}
