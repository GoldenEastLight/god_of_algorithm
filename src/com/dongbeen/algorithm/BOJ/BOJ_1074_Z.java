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
	private static char map[][];

	private static int N, R, C;
	private static int answer;
	private static boolean find;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = (int) Math.pow(2, sc.nextInt());

		R = sc.nextInt();
		C = sc.nextInt();

		solve(0, 0, N);
		System.out.println(answer);
	}

	private static void isFind(int x, int y) {
		for (int i = x; i < x + 2; i++) {
			for (int j = y; j < y + 2; j++) {
				if (i == R && j == C) {
					find = true;
					return;
				}
				answer++;
			}
		}
	}

	private static void solve(int x, int y, int n) {
		if (n == 2) {
			isFind(x, y);
			return;
		}

		int max = n / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (find)
					return;
//            	System.out.println("호출하려는 좌표 (" + (x + max * i) + "," + (y + max * j) + ")" + max);
				solve(x + max * i, y + max * j, max);
			}
		}
	}
}