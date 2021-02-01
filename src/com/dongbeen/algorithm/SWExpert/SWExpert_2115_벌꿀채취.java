package com.dongbeen.algorithm.SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_2115_벌꿀채취 {
	int N, M, C;
	int[][] map, maxMap;

	public static void main(String[] args) throws IOException {
		SWExpert_2115_벌꿀채취 m = new SWExpert_2115_벌꿀채취();
		m.service();
	}

	private void service() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxMap = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#" + t + " " + getMaxBenefit());
		}
	}

	private int getMaxBenefit() {
		makeMaxMap();
		return processCombination();
	}

	private void makeMaxMap() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c <= N - M; c++) {
				makeMaxSubset(r, c, 0, 0, 0);
			}
		}
	}

	private void makeMaxSubset(int r, int c, int cnt, int sum, int powSum) {
		if (sum > C)
			return;
		if (cnt == M) {
			if (maxMap[r][c - M] < powSum)
				maxMap[r][c - M] = powSum;
			return;
		}
		// 선택
		makeMaxSubset(r, c + 1, cnt + 1, sum + map[r][c], powSum + map[r][c] * map[r][c]);
		// 비선택
		makeMaxSubset(r, c + 1, cnt + 1, sum, powSum);
	}

	private int processCombination() {
		int max = 0, aBenefit = 0, bBenefit = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c <= N - M; c++) {
				aBenefit = maxMap[r][c];
				// 일꾼 B 선택
				// 같은 행
				bBenefit = 0;
				for (int c2 = c + M; c2 <= N - M; c2++) {
					if (bBenefit < maxMap[r][c2])
						bBenefit = maxMap[r][c2];
				}
				// 다른 행
				for (int r2 = r + 1; r2 < N; r2++) {
					for (int c2 = 0; c2 <= N - M; c2++) {
						if (bBenefit < maxMap[r2][c2])
							bBenefit = maxMap[r2][c2];
					}
				}
				if (max < aBenefit + bBenefit)
					max = aBenefit + bBenefit;
			}
		}
		return max;
	}
}
