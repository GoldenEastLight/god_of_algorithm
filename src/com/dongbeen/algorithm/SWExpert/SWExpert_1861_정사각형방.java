package com.dongbeen.algorithm.SWExpert;

import java.util.Scanner;

public class SWExpert_1861_정사각형방 {

	private static int[][] square;
	private static int[][] sCnt;
	private static int cnt = 0;
	private static int[] dY = { -1, 1, 0, 0 };
	private static int[] dX = { 0, 0, -1, 1 };
	private static int maxRoom = 0;
	private static int max = 0;

	public static void searchRoom(int[][] s, int y, int x) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dY[i];
			int nx = x + dX[i];
			if (ny < 0 || ny >= s.length || nx < 0 || nx >= s.length) {
				continue;
			}
			if (s[y][x] + 1 == s[ny][nx]) {
				cnt++;
				searchRoom(s, ny, nx);
			}
		}
	}

	public static int min(int room, int square) {
		if (room > square)
			return square;
		return room;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			square = new int[N][N];
			sCnt = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					square[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					searchRoom(square, i, j);
					sCnt[i][j] = cnt;
					cnt = 0;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max < sCnt[i][j]) {
						max = sCnt[i][j];
						maxRoom = square[i][j];
					}
					if (max == sCnt[i][j]) {
						maxRoom = min(maxRoom, square[i][j]);
					}
				}
			}
			System.out.println("#" + t + " " + maxRoom + " " + (max + 1));
			maxRoom = 0;
			max = 0;
		}
	}
}
