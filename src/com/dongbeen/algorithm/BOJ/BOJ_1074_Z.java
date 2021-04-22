/** 

입력

2 3 1

출력

11


입력

3 7 7

출력

63


*/
package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_1074_Z {
	private static int N, R, C;
	private static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = (int) Math.pow(2, sc.nextInt());
		R = sc.nextInt();
		C = sc.nextInt();

		solve(0, 0, N);
	}

	private static void solve(int y, int x, int n) {
		if (y == R && x == C) {
			System.out.println(answer);
			System.exit(0);
		}

		if (y <= R && R < (y + n) && x <= C && C < (x + n)) {
			solve(y, x, n / 2);
			solve(y, x + n / 2, n / 2); // 우측
			solve(y + n / 2, x, n / 2); // 하단
			solve(y + n / 2, x + n / 2, n / 2); // 대각선하단
		} else
			answer += n * n;
	}
}