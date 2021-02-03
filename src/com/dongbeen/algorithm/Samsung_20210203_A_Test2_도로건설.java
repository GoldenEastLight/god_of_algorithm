package com.dongbeen.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_20210203_A_Test2_도로건설 {
	int T;
	int W;
	int H;
	int[][] map;
	int[][] roadMap;
	boolean[][] visited;
	int[] dy = { -1, 1, 0, 0 };
	int[] dx = { 0, 0, -1, 1 };
	int maxDist;
	int minMaxDist;
	int houseCnt;
	List<House> houses;

	class House {
		int y;
		int x;
		int dist;

		public House(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		Samsung_20210203_A_Test2_도로건설 m = new Samsung_20210203_A_Test2_도로건설();
		m.service();
	}

	private void service() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			roadMap = new int[H][W];
			minMaxDist = Integer.MAX_VALUE;
			maxDist = Integer.MIN_VALUE;
			houseCnt = 0;
			houses = new ArrayList<>();

			for (int y = 0; y < H; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < W; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					if (map[y][x] == 1) {
						houseCnt++;
					}
				}
			}

			for (int y = 0; y < H; y++) {
				for (int x = 0; x < W; x++) {
					if (map[y][x] == 1) {
						houses.add(new House(y, x, 1));
					}
				}
			}

			buildRoad();

			if (minMaxDist == Integer.MAX_VALUE)
				System.out.println("#" + t + " " + -1);
			else
				System.out.println("#" + t + " " + minMaxDist);
		}

	}

	private void buildRoad() {
		maxDist = Integer.MIN_VALUE;
		for (int y = 0; y < H; y++) { // 가로로 도로건설
			for (int x = 0; x < W; x++) {
				if (map[y][x] == 1) {
					roadMapClear();
					break;
				}
				if (map[y][x] == 0) {
					roadMap[y][x] = 2;
					if (x == W - 1) {
//						printMap();
						countDistance();
						minMaxDist = Math.min(minMaxDist, maxDist);
						roadMapClear();
						break;
					}
				}
			}
		}
		maxDist = Integer.MIN_VALUE;
		for (int x = 0; x < W; x++) { // 세로로 도로건설
			for (int y = 0; y < H; y++) {
				if (map[y][x] == 1) {
					roadMapClear();
					break;
				}
				if (map[y][x] == 0) {
					roadMap[y][x] = 2;
					if (y == H - 1) {
//						printMap();
						countDistance();
						minMaxDist = Math.min(minMaxDist, maxDist);
						roadMapClear();
						break;
					}
				}
			}
		}
		maxDist = Integer.MIN_VALUE;
		for (int x = 0; x < W - 1; x++) { // 우측아래 대각선 도로건설
			int nx = x;
			for (int y = 0; y < H; y++, nx++) {
				if (map[y][nx] == 1) {
					roadMapClear();
					break;
				}
				if (map[y][nx] == 0) {
					roadMap[y][nx] = 2;
					if (nx == W - 1 || y == H - 1) {
//						printMap();
						countDistance();
						minMaxDist = Math.min(minMaxDist, maxDist);
						roadMapClear();
						break;
					}
				}
			}
		}
		maxDist = Integer.MIN_VALUE;
		for (int y = 0; y < H - 1; y++) { // 우측아래 대각선 도로건설
			int nx = 0;
			for (int x = 0; x < H; y++, nx++) {
				if (map[y][nx] == 1) {
					roadMapClear();
					break;
				}
				if (map[y][nx] == 0) {
					roadMap[y][nx] = 2;
					if (nx == W - 1 || y == H - 1) {
//						printMap();
						countDistance();
						minMaxDist = Math.min(minMaxDist, maxDist);
						roadMapClear();
						break;
					}
				}
			}
		}
		maxDist = Integer.MIN_VALUE;
		for (int x = 1; x < W; x++) { // 좌측아래 대각선 도로건설
			int nx = x;
			for (int y = 0; y < H; y++, nx--) {
				if (map[y][nx] == 1) {
					roadMapClear();
					break;
				}
				if (map[y][nx] == 0) {
					roadMap[y][nx] = 2;
					if (nx == 0 || y == H - 1) {
//						printMap();
						countDistance();
						minMaxDist = Math.min(minMaxDist, maxDist);
						roadMapClear();
						break;
					}
				}
			}
		}
		maxDist = Integer.MIN_VALUE;
		for (int y = 1; y < H - 1; y++) { // 좌측아래 대각선 도로건설
			int nx = W - 1;
			for (int x = 0; x < H; y++, nx--) {
				if (map[y][nx] == 1) {
					roadMapClear();
					break;
				}
				if (map[y][nx] == 0) {
					roadMap[y][nx] = 2;
					if (nx == 0 || y == H - 1) {
//						printMap();
						countDistance();
						minMaxDist = Math.min(minMaxDist, maxDist);
						roadMapClear();
						break;
					}
				}
			}
		}
	}

	private void roadMapClear() {
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				roadMap[y][x] = 0;
			}
		}
	}

	private void countDistance() {
		int size = houses.size();
		for (int n = 0; n < size; n++) {
			visited = new boolean[H][W];
			Queue<House> queue = new LinkedList<>();
			queue.add(houses.get(n));
			visited[houses.get(n).y][houses.get(n).x] = true;
			int tempDist = Integer.MAX_VALUE;

			while (!queue.isEmpty()) {
				House temp = queue.poll();

				for (int i = 0; i < 4; i++) {
					int ny = temp.y + dy[i];
					int nx = temp.x + dx[i];

					if (ny >= H || nx >= W || ny < 0 || nx < 0 || visited[ny][nx])
						continue;
					if (roadMap[ny][nx] == 2) {
						tempDist = Math.min(tempDist, temp.dist);
						break;
					}
					queue.add(new House(ny, nx, temp.dist + 1));
					visited[ny][nx] = true;
				}
			}
			maxDist = Math.max(tempDist, maxDist);
		}
	}

	private void printMap() {
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				System.out.print(roadMap[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
