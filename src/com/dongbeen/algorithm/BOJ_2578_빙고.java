package com.dongbeen.algorithm;
import java.util.Scanner;

public class BOJ_2578_빙고 {
	static int[][] checked;
	static int[] dy = { 1, 1, 1, 0, };
	static int[] dx = { 0, 0, -1, 1 };
	static int callCnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[][] bingo = new int[5][5];
		checked = new int[5][5];

		for (int i = 0; i < bingo.length; i++) {
			for (int j = 0; j < bingo.length; j++) {
				bingo[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < bingo.length; i++) {
			for (int j = 0; j < bingo.length; j++) {
				int call = sc.nextInt();
				++callCnt;

				for (int k = 0; k < bingo.length; k++) {
					for (int q = 0; q < bingo.length; q++) {
						if (bingo[k][q] == call) {
							checked[k][q] = 1;
						}
					}
				}

				if (callCnt > 10) {
					check();
				}
			}
		}
	}

	public static void check() {
		int bingoCnt = 0;

		for (int i = 0; i < 5; i++) { // 세로
			if (checked[0][i] == 1) {
				boolean bing = true;
				for (int j = 0; j < 5; j++) {
					if (checked[j][i] != 1)
						bing = false;
				}
				if (bing)
					bingoCnt++;
			}
		}

		for (int i = 0; i < 5; i++) { // 가로
			if (checked[i][0] == 1) {
				boolean bing = true;
				for (int j = 0; j < 5; j++) {
					if (checked[i][j] != 1)
						bing = false;
				}
				if (bing)
					bingoCnt++;
			}
		}

		if (checked[0][0] == 1) {
			boolean bing = true;
			for (int j = 0; j < 5; j++) {
				if (checked[j][j] != 1)
					bing = false;
			}
			if (bing)
				bingoCnt++;
		}

		if (checked[0][4] == 1) {
			boolean bing = true;
			for (int j = 4; j >= 0; j--) {
				if (checked[4 - j][j] != 1)
					bing = false;
			}
			if (bing)
				bingoCnt++;
		}

		if (bingoCnt >= 3) {
			System.out.println(callCnt);
			System.exit(0);
		}
	}
}
