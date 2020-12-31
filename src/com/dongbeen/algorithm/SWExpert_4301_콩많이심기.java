package com.dongbeen.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_4301_콩많이심기 {
//	static int[][] bat;
	static boolean[][] forbidden;
	static int N;
	static int M;
	static int maxBean = 0;
	static int[] dy = { -2, 2, 0, 0 };
	static int[] dx = { 0, 0, -2, 2 };

	public static void greedy(int y, int x) {
		if (forbidden[y][x] == true)
			return;
//		bat[y][x] = 1;
		maxBean++;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M)
				continue;
			forbidden[ny][nx] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
//			bat = new int[N][M];
			forbidden = new boolean[N][M];
			maxBean = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					greedy(i, j);
				}
			}
			System.out.println("#" + t + " " + maxBean);
		}
	}
}
