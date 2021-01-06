package com.dongbeen.algorithm;

import java.util.Scanner;

public class BOJ_1546_평균 {
	int N;
	double M;
	double[] scores;
	double sum;

	public static void main(String[] args) {
		BOJ_1546_평균 m = new BOJ_1546_평균();
		m.service();
	}

	public void service() {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		scores = new double[N];
		sum = 0;

		for (int n = 0; n < N; n++) {
			int score = sc.nextInt();
			if (n == 0)
				M = score;
			else
				M = Math.max(score, M);
			scores[n] = score;
		}

		for (int n = 0; n < N; n++) {
			sum += scores[n] / M * 100;
		}

		System.out.println(sum / N);
	}
}
