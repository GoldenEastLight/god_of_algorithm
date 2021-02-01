package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_11047_동전0 {
	int N;
	int K;
	int[] coins;
	int coinCnt = 0;

	public static void main(String[] args) {
		BOJ_11047_동전0 m = new BOJ_11047_동전0();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		coins = new int[N];

		for (int i = 0; i < N; i++) {
			coins[i] = sc.nextInt();
		}

		for (int i = N - 1; i >= 0; i--) {
			if (K >= coins[i]) {
				coinCnt += K / coins[i];
				K %= coins[i];
			}
		}

		System.out.print(coinCnt);

		sc.close();
	}
}
