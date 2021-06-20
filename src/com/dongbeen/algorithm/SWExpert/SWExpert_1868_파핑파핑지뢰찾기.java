package com.dongbeen.algorithm.SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWExpert_1868_파핑파핑지뢰찾기 {
	int[][] land;
	int N, clickCnt;
	boolean[][] visited;
	int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 }; // 좌상, 상, 우상, 우, 우하, 하, 좌하, 좌
	int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 }; // 좌상, 상, 우상, 우, 우하, 하, 좌하, 좌

	public static void main(String[] args) throws Exception {
		SWExpert_1868_파핑파핑지뢰찾기 m = new SWExpert_1868_파핑파핑지뢰찾기();
		m.service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			land = new int[N][N];
			visited = new boolean[N][N];
			clickCnt = 0;

			for (int r = 0; r < N; r++) {
				char[] line = br.readLine().toCharArray();
				for (int c = 0; c < N; c++) {
					if (line[c] == '*')
						land[r][c] = 13;
					else
						land[r][c] = 0;
				}
			}
			checkLand();

			System.out.println("#" + t + " " + clickCnt);
		}
	}

	private void checkLand() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (land[r][c] == 13)
					for (int i = 0; i < 8; i++) {
						int ny = r + dy[i];
						int nx = c + dx[i];

						if (ny < 0 || nx < 0 || ny >= N || nx >= N)
							continue;
						if (land[ny][nx] == 13)
							continue;
						land[ny][nx] += 1;
					}
			}
		}
		clickZero();
	}

	private void clickZero() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (land[r][c] == 0) {
					searchLand(r, c);
					clickCnt++;
				}
			}
		}
		leftDangerLandSearch();
	}

	private void leftDangerLandSearch() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (land[r][c] > 0 && land[r][c] < 13) {
					clickCnt++;
				}
			}
		}
	}

	private void searchLand(int y, int x) {
		visited[y][x] = true;
		for (int i = 0; i < 8; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N)
				continue;
			if (land[ny][nx] == 13 || visited[ny][nx])
				continue;
			if (land[ny][nx] == 0)
				searchLand(ny, nx);
			land[ny][nx] = -1;
			visited[ny][nx] = true;
		}
	}
}
