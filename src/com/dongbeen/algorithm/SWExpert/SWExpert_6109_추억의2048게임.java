package com.dongbeen.algorithm.SWExpert;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWExpert_6109_추억의2048게임 {
	int N;
	String S;
	int[][] map;

	public static void main(String[] args) {
		SWExpert_6109_추억의2048게임 m = new SWExpert_6109_추억의2048게임();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			S = sc.nextLine().replace(" ", "");

			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			move(S);

			System.out.println("#" + t);
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					System.out.print(map[r][c] + " ");
				}
				System.out.println();
			}
		}
	}

	private void move(String s) {
		Queue<Integer> queue = new LinkedList<>();

		switch (s) {
		case "left":
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != 0) {
						queue.offer(map[r][c]);
						map[r][c] = 0;
					}
				}
				
				while (!queue.isEmpty()) {
					int num = queue.poll();

					if (queue.size() > 0 && num == queue.peek()) {
						queue.poll();
						num *= 2;
					}
					for (int i = 0; i < N; i++)
						if (map[r][i] == 0) {
							map[r][i] = num;
							break;
						}
				}
			}
			break;

		case "right":
			for (int r = 0; r < N; r++) {
				for (int c = N - 1; c >= 0; c--) {
					if (map[r][c] != 0) {
						queue.offer(map[r][c]);
						map[r][c] = 0;
					}
				}

				while (!queue.isEmpty()) {
					int num = queue.poll();

					if (queue.size() > 0 && num == queue.peek()) {
						queue.poll();
						num *= 2;
					}
					for (int i = N - 1; i > 0; i--)
						if (map[r][i] == 0) {
							map[r][i] = num;
							break;
						}
				}
			}
			break;

		case "up":
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					if (map[r][c] != 0) {
						queue.offer(map[r][c]);
						map[r][c] = 0;
					}
				}

				while (!queue.isEmpty()) {
					int num = queue.poll();

					if (queue.size() > 0 && num == queue.peek()) {
						queue.poll();
						num *= 2;
					}
					for (int i = 0; i < N; i++)
						if (map[i][c] == 0) {
							map[i][c] = num;
							break;
						}
				}
			}
			break;

		case "down":
			for (int c = 0; c < N; c++) {
				for (int r = N - 1; r >= 0; r--) {
					if (map[r][c] != 0) {
						queue.offer(map[r][c]);
						map[r][c] = 0;
					}
				}

				while (!queue.isEmpty()) {
					int num = queue.poll();

					if (queue.size() > 0 && num == queue.peek()) {
						queue.poll();
						
						num *= 2;
					}
					for (int i = N - 1; i > 0; i--)
						if (map[i][c] == 0) {
							map[i][c] = num;
							break;
						}
				}
			}
			break;
		}
	}
}
