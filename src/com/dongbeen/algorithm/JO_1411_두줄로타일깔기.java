package com.dongbeen.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JO_1411_두줄로타일깔기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int memo[] = new int[N + 1];
		memo[1] = 1;
		memo[2] = 3;
		for (int i = 3; i <= N; i++) {
			memo[i] = (memo[i - 1] + memo[i - 2] * 2) % 20100529;
		}

		System.out.println(memo[N]);
	}
}
