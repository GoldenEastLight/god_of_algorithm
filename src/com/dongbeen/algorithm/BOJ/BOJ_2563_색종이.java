package com.dongbeen.algorithm.BOJ;
import java.util.Scanner;

public class BOJ_2563_색종이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[][] DoHwaJi = new int[100][100];
		int N = sc.nextInt();
		int black = 0;

		for (int i = 0; i < N; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();

			for (int r = y; r < y + 10; r++) {
				for (int c = x; c < x + 10; c++) {
					DoHwaJi[r][c] = 1;
				}
			}
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (DoHwaJi[i][j] == 1)
					++black;
			}
		}

		System.out.println(black);
	}
}
