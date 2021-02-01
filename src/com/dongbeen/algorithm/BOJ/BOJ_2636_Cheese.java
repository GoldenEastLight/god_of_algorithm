package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2636_Cheese {
	int N;
	int M;
	int[][] map;
	boolean[][] visited;
	int[] dy = { -1, 1, 0, 0 };
	int[] dx = { 0, 0, -1, 1 };
	int cheeseCount;
	int time = 0;

	public static void main(String[] args) throws IOException {
		BOJ_2636_Cheese m = new BOJ_2636_Cheese();
		m.service();
	}

	private void service() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M];
		aircheck(0, 0);

		melting();

		System.out.println(time - 1);
		System.out.println(cheeseCount);

	}

	private void melting() {
		while (true) {
			boolean finish = true;
			int[][] copyMap = new int[N][M];
			int count = 0;

			time++;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					copyMap[r][c] = map[r][c];
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 1) {
						finish = false;
						for (int i = 0; i < 4; i++) {
							int ny = r + dy[i];
							int nx = c + dx[i];

							if (map[ny][nx] == 2) {
								copyMap[r][c] = 2;
							}
						}
					}
				}
			}

			if (finish)
				break;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 1)
						count++;
					map[r][c] = copyMap[r][c];
				}
			}

			cheeseCount = count;
			visited = new boolean[N][M];
			aircheck(0, 0);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						System.out.print("8" + " ");
					} else
						System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	private void aircheck(int y, int x) {
		map[y][x] = 2;
		visited[y][x] = true;

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < 0 || ny >= N || nx < 0 || nx >= M)
				continue;
			if (map[ny][nx] == 1 || visited[ny][nx])
				continue;

			aircheck(ny, nx);
		}
	}
}
