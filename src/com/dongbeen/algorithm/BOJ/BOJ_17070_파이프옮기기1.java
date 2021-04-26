package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	int N;
	int[][] map;
	int pipeCnt = 0;

	public static void main(String[] args) throws Exception {
		new BOJ_17070_파이프옮기기1().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		// 파이프의 앞부분이 움직이면 윗부분은 앞부분의 위치로 이동하기 때문에 앞부분만 체크하면 된다.
		move(0, 1, 0);
		System.out.println(pipeCnt);
	}

	// dir :: 0. 가로, 1. 세로, 2. 대각선
	private void move(int y, int x, int dir) {
		if (y >= N - 1 && x >= N - 1) {
			pipeCnt++;
			return;
		}

		switch (dir) {
		case 0: // 파이프가 가로일 경우
			if (possible(y, x + 1))
				move(y, x + 1, 0);
			if (possible(y, x + 1) && possible(y + 1, x + 1) && possible(y + 1, x))
				move(y + 1, x + 1, 2);
			break;

		case 1: // 파이프가 세로일 경우
			if (possible(y + 1, x))
				move(y + 1, x, 1);
			if (possible(y, x + 1) && possible(y + 1, x + 1) && possible(y + 1, x))
				move(y + 1, x + 1, 2);
			break;

		case 2: // 파이프가 대각선인 경우
			if (possible(y, x + 1))
				move(y, x + 1, 0);
			if (possible(y + 1, x))
				move(y + 1, x, 1);
			if (possible(y, x + 1) && possible(y + 1, x + 1) && possible(y + 1, x))
				move(y + 1, x + 1, 2);
			break;
		}
	}

	private boolean possible(int y, int x) {
		return y >= 0 && y < N && x >= 0 && x < N && map[y][x] == 0;
	}
}
