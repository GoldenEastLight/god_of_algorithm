package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {
	static int R;
	static int C;
	static int T;
	static int[][] room;
	static int[][] roomAfter;
	static int[] dy = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	static int[] dx = { 0, 0, -1, 1 };
	static int upAirCleaner;
	static int downAirCleaner;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		roomAfter = new int[R][C];

		int cleanerCnt = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
				if (cleanerCnt == 0 && room[r][c] == -1) {
					upAirCleaner = r;
					cleanerCnt++;
				}
				if (cleanerCnt == 1 && room[r][c] == -1)
					downAirCleaner = r;
			}
		}

		for (int t = 0; t < T; t++) {
			diffusion();
			circulation();
		}

		int dust = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (room[r][c] != -1)
					dust += room[r][c];
			}
		}
		System.out.println(dust);
	}

	private static void diffusion() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (room[r][c] >= 5) {
					int circulationDust = room[r][c] / 5;
					for (int i = 0; i < 4; i++) {
						int ny = dy[i] + r;
						int nx = dx[i] + c;

						if (ny >= 0 && nx >= 0 && ny < R && nx < C && room[ny][nx] != -1) {
							roomAfter[ny][nx] += circulationDust;
							roomAfter[r][c] -= circulationDust;
						}
					}
				}
			}
		}
		after();
	}

	private static void circulation() {
		// 위쪽방향 공기청정기 순환
		for (int right = 1; right < C - 1; right++) {
			roomAfter[upAirCleaner][right + 1] = room[upAirCleaner][right];
			room[upAirCleaner][right] = roomAfter[upAirCleaner][right];
		}
		for (int up = upAirCleaner; up > 0; up--) {
			roomAfter[up - 1][C - 1] = room[up][C - 1];
			room[up][C - 1] = roomAfter[up][C - 1];
		}
		for (int left = C - 1; left > 0; left--) {
			roomAfter[0][left - 1] = room[0][left];
			room[0][left] = roomAfter[0][left];
		}
		for (int down = 0; down < upAirCleaner; down++) {
			roomAfter[down + 1][0] = room[down][0];
			room[down][0] = roomAfter[down][0];
		}

		// 아랫쪽방향 공기청정기 순환
		for (int right = 1; right < C - 1; right++) {
			roomAfter[downAirCleaner][right + 1] = room[downAirCleaner][right];
			room[downAirCleaner][right] = roomAfter[downAirCleaner][right];
		}
		for (int down = downAirCleaner; down < R - 1; down++) {
			roomAfter[down + 1][C - 1] = room[down][C - 1];
			room[down][C - 1] = roomAfter[down][C - 1];
		}
		for (int left = C - 1; left > 0; left--) {
			roomAfter[R - 1][left - 1] = room[R - 1][left];
			room[R - 1][left] = roomAfter[R - 1][left];
		}
		for (int up = R - 1; up > downAirCleaner; up--) {
			roomAfter[up - 1][0] = room[up][0];
			room[up][0] = roomAfter[up][0];
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				roomAfter[r][c] = 0;
			}
		}
	}

	private static void after() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				room[r][c] += roomAfter[r][c];
				roomAfter[r][c] = 0;
			}
		}
	}
}
