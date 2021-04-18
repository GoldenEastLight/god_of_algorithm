package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890_경사로 {
	int N;
	int L;
	int[][] map;
	int roadCnt = 0;

	public static void main(String[] args) throws IOException {
		new BOJ_14890_경사로().service();
	}

	private void service() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			makeRoadRow(i);
			makeRoadCol(i);
		}
		System.out.println(roadCnt);
	}

	private void makeRoadRow(int r) {
		boolean[] road = new boolean[N];

		for (int i = 0; i < N - 1; i++) {
			if (map[r][i] == map[r][i + 1])
				continue;
			if (Math.abs(map[r][i] - map[r][i + 1]) > 1)
				return;
			if (map[r][i] - 1 == map[r][i + 1]) { // 내리막일 경우
				for (int j = i + 1; j <= i + L; j++) {
					if (j >= N || road[j] || map[r][j] != map[r][i + 1])
						return;
					road[j] = true;
				}
			} else if (map[r][i] + 1 == map[r][i + 1]) { // 오르막일 경우
				for (int j = i; j > i - L; j--) {
					if (j < 0 || road[j] || map[r][j] != map[r][i])
						return;
					road[j] = true;
				}
			}
		}
		roadCnt++;
	}

	private void makeRoadCol(int c) {
		boolean[] road = new boolean[N];

		for (int i = 0; i < N - 1; i++) {
			if (map[i][c] == map[i + 1][c])
				continue;
			if (Math.abs(map[i][c] - map[i + 1][c]) > 1)
				return;
			if (map[i][c] - 1 == map[i + 1][c]) { // 내리막일 경우
				for (int j = i + 1; j <= i + L; j++) {
					if (j >= N || road[j] || map[j][c] != map[i + 1][c])
						return;
					road[j] = true;
				}
			} else if (map[i][c] + 1 == map[i + 1][c]) { // 오르막일 경우
				for (int j = i; j > i - L; j--) {
					if (j < 0 || road[j] || map[j][c] != map[i][c])
						return;
					road[j] = true;
				}
			}
		}
		roadCnt++;
	}
}
