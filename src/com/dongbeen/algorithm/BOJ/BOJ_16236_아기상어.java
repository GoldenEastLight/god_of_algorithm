package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 같은 거리라면 y가 더 작은 물고기(가장 위) -> x가 더 작은 물고기(가장 왼쪽) 순으로 먹어야함
 */
public class BOJ_16236_아기상어 {
	static int N;
	static int[][] map;
	static int[][] visited;
	static int[] dy = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	static int[] dx = { 0, 0, -1, 1 };
	static int babySize = 2;
	static int eatCnt = 0;
	static Location baby;
	static int finalSec = 0;

	public static class Location implements Comparable<Location> {
		int y;
		int x;

		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(Location o) {
			if (this.y == o.y)
				return this.x - o.x;
			else
				return this.y - o.y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					map[r][c] = 0;
					baby = new Location(r, c);
				}
			}
		}

		while (true) {
			if (!move())
				break;
		}

		System.out.println(finalSec);
	}

	public static boolean move() {
		Queue<Location> queue = new LinkedList<>();
		PriorityQueue<Location> eatQueue = new PriorityQueue<>();
		visited = new int[N][N];
		visited[baby.y][baby.x] = 1;
		queue.offer(baby);
		int dist = Integer.MAX_VALUE;

		while (!queue.isEmpty()) {
			Location temp = queue.poll();

			if (visited[temp.y][temp.x] >= dist) // 거리가 더 멀면 진행할 필요가 없다.
				continue;

			for (int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] > 0 || babySize < map[ny][nx])
					continue;

				if (map[ny][nx] > 0 && map[ny][nx] < babySize) {
					eatQueue.offer(new Location(ny, nx));
					dist = visited[temp.y][temp.x] + 1;
				}
				visited[ny][nx] = visited[temp.y][temp.x] + 1;
				queue.offer(new Location(ny, nx));
			}
		}

		if (!eatQueue.isEmpty()) {
			Location temp = eatQueue.poll();
			finalSec += visited[temp.y][temp.x] - 1;

			if (++eatCnt == babySize) {
				++babySize;
				eatCnt = 0;
			}

			map[temp.y][temp.x] = 0;

			baby.y = temp.y;
			baby.x = temp.x;
			
			return true;
		}
		return false;
	}
}
