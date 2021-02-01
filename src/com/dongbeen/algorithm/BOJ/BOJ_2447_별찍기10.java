/**
 * 재귀적인 패턴을 재귀함수로 찍는 별찍기 문제
 */

package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_2447_별찍기10 {
	int N;
	char[][] board;

	public static void main(String[] args) {
		BOJ_2447_별찍기10 m = new BOJ_2447_별찍기10();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new char[N][N];

		star(0, 0, N, false);

		for (int i = 0; i < N; i++) {
			System.out.println(board[i]);
		}
	}

	private void star(int y, int x, int n2, boolean blank) {
		if (blank) {
			for (int i = y; i < y + n2; i++) {
				for (int j = x; j < x + n2; j++) {
					board[i][j] = ' ';
				}
			}
			return;
		}

		if (n2 == 1) {
			board[y][x] = '*';
			return;
		}

		int size = n2 / 3;
		int count = 0;
		for (int i = y; i < y + n2; i += size) {
			for (int j = x; j < x + n2; j += size) {
				count++;
				if (count == 5) {
					star(i, j, size, true);
				} else {
					star(i, j, size, false);
				}
			}
		}
	}
}
