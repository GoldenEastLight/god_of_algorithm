package com.dongbeen.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2628_종이자르기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int height = sc.nextInt();
		int width = sc.nextInt();
		int N = sc.nextInt();
		int[] rowCut = new int[N + 1];
		int[] calCut = new int[N + 1];

		for (int n = 0; n < N; n++) {
			int cutType = sc.nextInt(); // 가로 : 0, 세로 : 1
			int cutNum = sc.nextInt();

			if (cutType == 0) {
				rowCut[n] = cutNum;
			} else {
				calCut[n] = cutNum;
			}
		}
		rowCut[N] = width;
		calCut[N] = height;

		Arrays.sort(rowCut);
		Arrays.sort(calCut);

		int[] row = new int[N + 1];
		int[] cal = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			row[i] = rowCut[i] - rowCut[i - 1];
			cal[i] = calCut[i] - calCut[i - 1];
		}

		Arrays.sort(row);
		Arrays.sort(cal);

		System.out.println(row[N] * cal[N]);
	}
}
