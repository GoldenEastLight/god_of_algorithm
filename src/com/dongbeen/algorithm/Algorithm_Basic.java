package com.dongbeen.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Algorithm_Basic {
	int N = 5;
	// 메모이제이션 할 임의 배열
	int[] memo = new int[5];
	// 순열, 조합 할 visited[], sum, max, arr[], r(몇개 선택할지)
	boolean[] visited = new boolean[5];
	int max = 0;
	int sum = 0;
	int[] arr = new int[5];
	int[] num = new int[5];
	int r = 5;

	public static void main(String[] args) throws Exception {
		Algorithm_Basic study = new Algorithm_Basic();
		study.io();
		study.factorial(5);
	}

	private void io() throws Exception {
		// 일반적
		Scanner sc = new Scanner(System.in);

		// 입력 多 경우에 효율적
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		char[] arr = br.readLine().toCharArray();

		// " " 공백 기준으로 출력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		st.nextToken();

		// 문자열을 조작 해주기에, 출력 多 경우 효율적
		StringBuilder sb = new StringBuilder();
		sb.append("Hello");
		sb.append(" World");
		sb.append("!");
		System.out.println(sb.toString());
	}

	// 재귀 1. n!
	private int factorial(int n) {
		if (n <= 1)
			return 1;
		else
			return n * factorial(n - 1);
	}

	// 재귀 2. 피보나치
	private int fibo(int n) {
		if (n <= 1)
			return n;
		else
			return n * fibo(n - 1) * fibo(n - 2);
	}

	// 피보나치 메모이제이션(중복 호출 방지!)
	private int fiboMemo(int n) {
		if (n <= 1)
			return n;
		if (memo[n] != 0)
			return memo[n];
		return memo[n] = fiboMemo(n - 1) + fiboMemo(n - 2);
	}

	// 순열
	private void permutation(int cnt) {
		if (cnt == r) {
			sum = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i])
					sum += arr[i];
			}
			if (max < sum)
				max = sum;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			permutation(cnt + 1);
			visited[i] = false;
		}
	}

	// 조합
	private void combination(int start, int count) {
		if (count == r)
			return;

		for (int i = start; i < N; i++) {
			num[count] = arr[i];
			combination(start + 1, count + 1);
		}
	}

	// 부분 집합
	private void subset(int count) {
		if (count == N)
			return;

		visited[count] = true;
		subset(count + 1);
		visited[count] = false;
		subset(count + 1);
	}

	// preorder, postorder

	// 서로소집합
}
