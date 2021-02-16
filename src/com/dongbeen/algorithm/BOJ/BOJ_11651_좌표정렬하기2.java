package com.dongbeen.algorithm.BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11651_좌표정렬하기2 {
	int N;
	int[][] arr;

	public static void main(String[] args) {
		BOJ_11651_좌표정렬하기2 m = new BOJ_11651_좌표정렬하기2();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][2];

		for (int n = 0; n < N; n++) {
			arr[n][1] = sc.nextInt();
			arr[n][0] = sc.nextInt();
		}

		Arrays.sort(arr, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			} else {
				return o1[0] - o2[0];
			}
		});

		for (int n = 0; n < N; n++) {
			System.out.println(arr[n][1] + " " + arr[n][0]);
		}

		sc.close();
	}
}
