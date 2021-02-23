package com.dongbeen.algorithm.SWExpert;

import java.util.Scanner;

public class SWExpert_4008_숫자만들기 {
	static int N; // 수의 갯수
	static int[] opCnt; // 연산자 카운트
	static int[] nums; // 수
	static int max, min; // 최대 , 최소값

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			opCnt = new int[4]; // 연산자는 4개
			nums = new int[N];

			// 입력
			for (int i = 0; i < 4; i++)
				opCnt[i] = sc.nextInt();

			for (int i = 0; i < N; i++)
				nums[i] = sc.nextInt();

			max = -987564321; // 임의의 작은값으로 초기화
			min = 987654321; // 임의의 큰값으로 초기화
			permutation(1, nums[0]);
			System.out.println("#" + tc + " " + (max - min));
		}
	}

	static void permutation(int idx, int value) {
		if (idx == N) {
			// 최대 최소갱신
			max = Math.max(max, value);
			min = Math.min(min, value);
			return;
		}
		for (int i = 0; i < 4; i++) {
			// 중복연산방지
			// 값이 있을때만 연산
			if (opCnt[i] > 0) {
				opCnt[i]--;// 해당 연산횟수 깎기
				// i의 값에따라 + - * / 순으로 재귀호출
				switch (i) {
				case 0:
					permutation(idx + 1, value + nums[idx]);
					break;
				case 1:
					permutation(idx + 1, value - nums[idx]);
					break;
				case 2:
					permutation(idx + 1, value * nums[idx]);
					break;
				case 3:
					permutation(idx + 1, value / nums[idx]);
					break;
				}
				// 원상복귀
				opCnt[i]++;
			}
		}
	}
}
