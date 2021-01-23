package com.dongbeen.algorithm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Hanoi(n) = 2 * Hanoi(n - 1) + 1
 */
public class BOJ_11729_하노이탑이동순서 {
	int N;
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BOJ_11729_하노이탑이동순서 m = new BOJ_11729_하노이탑이동순서();
		m.service();
	}

	public void service() throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		bw.write((int) (Math.pow(2, N) - 1) + "\n");

		hanoi(N, 1, 2, 3);
		bw.close();
	}

	public void hanoi(int n, int A, int B, int C) throws IOException {
		if (n == 1) {
			bw.write(A + " " + C + "\n");
			return;
		}

		// n - 1개를 A에서 B로 이동
		hanoi(n - 1, A, C, B);

		// 1개를 A에서 C로 이동
		bw.write(A + " " + C + "\n");

		// n - 1개를 B에서 C로 이동
		hanoi(n - 1, B, A, C);
	}
}
