package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_10950_AplusB_3 {
	int T;
	int A;
	int B;

	public static void main(String[] args) {
		BOJ_10950_AplusB_3 m = new BOJ_10950_AplusB_3();

		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			A = sc.nextInt();
			B = sc.nextInt();
			System.out.println(A + B);
		}
	}
}
