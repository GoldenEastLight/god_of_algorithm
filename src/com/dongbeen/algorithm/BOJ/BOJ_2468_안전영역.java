package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2468_안전영역 {
	static int[][] info;
	static int[][] safeZone;
	static boolean[][] visited;
	static int N;
	static int maxRain = 0;
	static int maxSafeZone = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		info = new int[N][N];
		safeZone = new int[N][N];
		visited = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				info[r][c] = Integer.parseInt(st.nextToken());
				if (info[r][c] > maxRain)
					maxRain = info[r][c];
			}
		}

		for (int rain = 0; rain < maxRain; rain++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					safeZone[r][c] = info[r][c] - rain;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = false;
				}
			}
			bfs();
		}

		System.out.println(maxSafeZone);
	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };
		int cnt = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (safeZone[r][c] > 0 && visited[r][c] == false) {
					visited[r][c] = true;
					queue.offer(new int[] { r, c });

					while (!queue.isEmpty()) {
						int[] temp = queue.poll();

						for (int i = 0; i < 4; i++) {
							int ny = temp[0] + dy[i];
							int nx = temp[1] + dx[i];

							if (ny < 0 || nx < 0 || ny >= N || nx >= N)
								continue;
							if (safeZone[ny][nx] > 0 && visited[ny][nx] == false) {
								visited[ny][nx] = true;
								queue.offer(new int[] { ny, nx });
							}
						}
					}
					cnt++;
				}
			}
		}
		if (cnt > maxSafeZone)
			maxSafeZone = cnt;
	}
}
