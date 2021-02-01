/*
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 1 2 0 0 2 2 2 1 0 0 0 0 0 0 0 0 0 0
0 0 1 2 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
0 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 2 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 1 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 2 1 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 */

package com.dongbeen.algorithm.JO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1733_오목 {
	static int size = 19;
	static int[][] pan = new int[size][size];
	static int[] dy = { 0, 1, 1, -1 }; // 우, 우하, 하, 우상
	static int[] dx = { 1, 1, 0, 1 };
	static int[][][] visited = new int[4][size][size];
	static AL start;

	public static class AL {
		int y;
		int x;

		AL(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int r = 0; r < size; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < size; c++) {
				pan[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (pan[r][c] == 1) {
					start = new AL(r + 1, c + 1);
					DFS(1, 4, r, c, 1);
				}
				if (pan[r][c] == 2) {
					start = new AL(r + 1, c + 1);
					DFS(2, 4, r, c, 1);
				}
			}
		}
		System.out.println(0);
	}

	public static void DFS(int color, int type, int y, int x, int cnt) {
		if (cnt == 5) {
			int ny = y + dy[type];
			int nx = x + dx[type];

			if (ny >= size || nx >= size || ny < 0 || color != pan[ny][nx]) {
				if (visited[type][start.y - 1][start.x - 1] == 1)
					return;
				System.out.println(color);
				System.out.println((start.y) + " " + (start.x));
				System.exit(0);
			}

			if (color == pan[ny][nx]) {
				return;
			}
		}

		if (type == 4) {
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny >= size || nx >= size || ny < 0)
					continue;

				if (color == pan[ny][nx] && visited[i][ny][nx] != 1) {
					visited[i][ny][nx] = 1;
					DFS(color, i, ny, nx, cnt + 1);
				}
			}
		} else {
			int ny = y + dy[type];
			int nx = x + dx[type];

			if (ny >= size || nx >= size || ny < 0)
				return;

			if (color == pan[ny][nx]) {
				DFS(color, type, ny, nx, cnt + 1);
			}
		}
	}
}