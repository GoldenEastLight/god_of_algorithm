package com.dongbeen.algorithm.SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 최대값을 구하는 문제에선 백트래킹 사실상 X
 * 그래서 최대값 구하기는 보통 완탐이다.
 */

public class SWExpert_2117_홈방범서비스_2nd {
	int N, M;
	int[][] city; // 도시 정보
	int[] cost; // 운영 비용

	public static void main(String[] args) throws Exception {
		new SWExpert_2117_홈방범서비스_2nd().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		cost = new int[21];
		for (int k = 0; k < 21; k++) { // k : 기준점에서 떨어진 정도
			cost[k] = (k + 1) * (k + 1) + k * k;
		} // 가능한 모든 k의 운영비용 계산

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			city = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					city[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end input

			System.out.println("#" + t + " " + process());
		} // end TC
	} // end main

	private int process() {
		int max = 0;

		// 기준점에서 얼마나 퍼져 있는지.
		for (int k = 0; k <= N; k++) {
			// 완전탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int houseCnt = 0; // 영역 안에 들어온 집 개수
					// 마름모 영역 계산
					// 기준행
					int startJ = j - k, endJ = j + k;
					for (int b = startJ; b <= endJ; b++) {
						if (b >= 0 && b < N && city[i][b] == 1)
							houseCnt++;
					}

					// 기준행의 위/아래를 처리
					for (int c = 1; c <= k; c++) {
						startJ = j - (k - c);
						endJ = j + (k - c);
						for (int b = startJ; b <= endJ; b++) {
							if (b >= 0 && b < N && i - c >= 0 && city[i - c][b] == 1) // i행 기준으로 c만큼 윗행
								houseCnt++;
							if (b >= 0 && b < N && i + c < N && city[i + c][b] == 1) // i행 기준으로 c만큼 아래행
								houseCnt++;
						}
					}

					// 구한 집의 개수를 이용하여 지불비용을 계산하여 운영비용과 수지타산
					if (M * houseCnt >= cost[k]) {
						if (max < houseCnt)
							max = houseCnt;
					}
				}
			}
		}
		return max;
	}
}
