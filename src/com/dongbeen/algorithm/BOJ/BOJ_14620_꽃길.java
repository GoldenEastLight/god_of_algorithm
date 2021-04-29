package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_14620_꽃길 {
	int N;
	int[][] map;
	boolean[][] visited;
	int minPrice;
	int dy[] = { -1, 1, 0, 0 };
	int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		new BOJ_14620_꽃길().service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();

		plant(0, 0);
	}

	private void plant(int seed, int sum) {
		if (seed == 3) { // 씨앗 3개가 다 심기면
			minPrice = Math.min(minPrice, sum);
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for(int k = 0; k < 4; k++) {
					int ny = i + dy[k];
				}
			}
		}
	}
}
