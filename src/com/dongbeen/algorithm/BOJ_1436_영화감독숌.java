package com.dongbeen.algorithm;

import java.util.Scanner;

public class BOJ_1436_영화감독숌 {
	int N;

	public static void main(String[] args) {
		BOJ_1436_영화감독숌 m = new BOJ_1436_영화감독숌();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		counting();
	}

	private void counting() {
		int count = 0;
		int n = 666;

		while (true) {
			if (String.valueOf(n).contains("666")) {
				count++;
			}
			if (count == N) {
				System.out.println(n);
				return;
			}
			n++;
		}
	}
}
