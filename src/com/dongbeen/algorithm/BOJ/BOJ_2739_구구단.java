package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_2739_구구단 {
	int N;

	public static void main(String[] args) {
		BOJ_2739_구구단 m = new BOJ_2739_구구단();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		for (int i = 1; i <= 9; i++) {
			System.out.println(N + " * " + i + " = " + N * i);
		}
	}
}
