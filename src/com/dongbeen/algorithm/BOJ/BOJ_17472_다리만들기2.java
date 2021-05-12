package com.dongbeen.algorithm.BOJ;

/*
 * 문제 풀이 순서
 * 1. 입력 받은 map의 섬의 개수를 파악하며 섬에 번호를 붙여준다 (DFS)
 * 2. 각 섬에서 출발하여 도착 가능한 섬에 건설할수 있는 최소비용을 찾는다. (BFS)
 * 3. 모든 섬을 연결하는 다리 길이의 최솟값을 구한다(MST:최소신장트리)
 * 4. 이 문제는 완전탐색, 너비우선탐색, 깊이우선탐색, 그래프이론, 최소신장트리를 이용하여 구현하는 문제로 문제의 난이도가 높다.
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17472_다리만들기2 {
	int N, M;
	int[][] map;
	int[] dy = { -1, 1, 0, 0 }; // 상 하 좌 우
	int[] dx = { 0, 0, -1, 1 };
	int[] parent;

	public static void main(String[] args) {
		new BOJ_17472_다리만들기2().service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}

		int island = 1;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] == -1)
					DFS(i, j, island++);

		// 간선의 비용 구하기
		PriorityQueue<int[]> pQueue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		for (int i = 1; i < island; i++) {
			for (int j = i + 1; j < island; j++) {
				int cost = getBridge(i, j);
				if (cost != 0)
					pQueue.add(new int[] { i, j, cost });
			}
		}

		// makeSet
		parent = new int[island];
		for (int i = 1; i < island; i++) {
			parent[i] = i;
		}

		int ans = 0;
		// 다리선택 MST
		while (!pQueue.isEmpty()) {
			int[] now = pQueue.poll();
			int x = find(now[0]);
			int y = find(now[1]);
			if (x != y) {
				union(now[0], now[1]);
				ans += now[2];
			}
		}

		// 결과
		boolean flag = true;
		for (int i = 1; i < island; i++) {
			if (find(i) != 1) {
				flag = false;
				break;
			}
		}
		System.out.println(flag ? ans : -1);
	}

	private void DFS(int i, int j, int cnt) {
		map[i][j] = cnt;
		for (int k = 0; k < 4; k++) {
			int ny = i + dy[k];
			int nx = j + dx[k];
			if (ny >= 0 && ny < N && nx >= 0 && nx < M && map[ny][nx] == -1)
				DFS(ny, nx, cnt);
		}
	}

	private int getBridge(int start, int end) {
		boolean[][][] visited = new boolean[N][M][4];
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != start)
					continue;
				for (int k = 0; k < dy.length; k++) {
					int r = i + dy[k];
					int c = j + dx[k];
					if (r < 0 || r >= N || c < 0 || c >= M || map[r][c] != 0)
						continue;
					q.add(new int[] { r, c, 0, k });
					visited[r][c][k] = true;
				}
			}
		}

		int ret = 0;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (map[now[0]][now[1]] == end) {
				if (now[2] == 1)
					continue;
				ret = now[2];
				break;
			}
			int ny = now[0] + dy[now[3]];
			int nx = now[1] + dx[now[3]];

			if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx][now[3]]
					&& (map[ny][nx] == 0 || map[ny][nx] == end)) {
				visited[ny][nx][now[3]] = true;
				q.add(new int[] { ny, nx, now[2] + 1, now[3] });
			}
		}

		return ret;
	}

	private void union(int i, int j) {
		int x = find(i);
		int y = find(j);

		if (x > y)
			parent[x] = y;
		else
			parent[y] = x;
	}

	private int find(int i) {
		if (i == parent[i])
			return i;
		return parent[i] = find(parent[i]);
	}
}
