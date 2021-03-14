package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_1001_AminusB {
	int N;
	int M;

	public static void main(String[] args) {
		BOJ_1001_AminusB m = new BOJ_1001_AminusB();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		System.out.println(N - M);

		sc.close();
	}
}
