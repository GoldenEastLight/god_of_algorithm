package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058_마법사상어와파이어스톰 {
	int N, Q, mapSize, sum;
	int map[][];
	int[] dy = { -1, 0, 0, 1 }; // 상 우 좌 하
	int[] dx = { 0, 1, -1, 0 };
	int iceLeft;

	public static void main(String[] args) throws Exception {
		new BOJ_20058_마법사상어와파이어스톰().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		mapSize = (int) Math.pow(2, N);
		map = new int[mapSize][mapSize];
		sum = 0;

		for (int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int l = Integer.parseInt(st.nextToken());
			spin(l);
			melt();
		}

		boolean visited[][] = new boolean[mapSize][mapSize];
		int max = 0;
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (!visited[i][j] && map[i][j] > 0) {
					visited[i][j] = true;
					max = Math.max(max, answer(i, j, visited));
				}
			}
		}
		System.out.println(sum + "\n" + max);
	}

	private void spin(int l) {
		int loop = mapSize / (int) Math.pow(2, l);
		int next[][] = new int[mapSize][mapSize];

		int x = 0;
		// 세로 덩어리
		for (int i = 0; i < loop; i++) {
			int y = 0;
			if (i != 0)
				x += (int) Math.pow(2, l);
			// 작은 덩어리
			for (int j = 0; j < loop; j++) {
				if (j != 0)
					y += (int) Math.pow(2, l);
				for (int a = 0; a < (int) Math.pow(2, l); a++) {
					for (int b = 0; b < (int) Math.pow(2, l); b++) {
						next[x + b][y - a + (int) Math.pow(2, l) - 1] = map[x + a][y + b];
					}
				}
			}
		}
		map = next;
	}

	private void melt() {
		Queue<Integer> qy = new LinkedList<>();
		Queue<Integer> qx = new LinkedList<>();
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int ny = i + dy[k];
					int nx = j + dx[k];

					if (check(ny, nx))
						if (map[ny][nx] >= 1)
							cnt++;
				}
				if (cnt < 3) {
					qy.offer(i);
					qx.offer(j);
				}
			}
		}

		while (!qx.isEmpty()) {
			int y = qy.poll();
			int x = qx.poll();
			map[y][x]--;
		}
	}

	private boolean check(int y, int x) {
		return x >= 0 && y >= 0 && x < mapSize && y < mapSize;
	}

	private int answer(int y, int x, boolean visited[][]) {
		int cnt = 1;
		sum += map[y][x];

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (check(ny, nx) && map[ny][nx] > 0 && !visited[ny][nx]) {
				visited[ny][nx] = true;
				cnt += answer(ny, nx, visited);
			}
		}

		return cnt;
	}
}
