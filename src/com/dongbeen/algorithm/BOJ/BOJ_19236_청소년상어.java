package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_19236_청소년상어 {
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	// 1, 2, 3, 4, 5, 6, 7, 8
	int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	int maxEatSum = 0;

	class Shark {
		int x, y, dir, maxEat;

		Shark(int x, int y, int dir, int maxEat) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.maxEat = maxEat;
		}
	}

	class Fish {
		int x, y, num, dir;
		boolean dead;

		Fish(int x, int y, int num, int dir, boolean dead) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.dead = dead;
		}
	}

	public static void main(String[] args) throws Exception {
		new BOJ_19236_청소년상어().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[4][4];
		ArrayList<Fish> fishes = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				fishes.add(new Fish(i, j, num, dir, false));
				map[i][j] = num;
			}
		}

		// 번호가 낮은 순으로 정렬
		Collections.sort(fishes, new Comparator<Fish>() {
			@Override
			public int compare(Fish o1, Fish o2) {
				return o1.num - o2.num;
			}
		});

		// 초기 상어 위치, 방향, 섭취한 물고기번호 합 초기화
		Fish init = fishes.get(map[0][0] - 1);
		Shark youthShark = new Shark(0, 0, init.dir, init.num);
		init.dead = true;
		map[0][0] = -1; // 상어의 위치를 -1로 지정

		eat(map, youthShark, fishes);
		System.out.println(maxEatSum);
	}

	private void eat(int[][] map, Shark youthShark, ArrayList<Fish> fishes) {
		maxEatSum = Math.max(maxEatSum, youthShark.maxEat);

		for (int i = 0; i < fishes.size(); i++)
			moveFish(fishes.get(i), map, fishes);

		// 같은 방향의 1칸부터 최대 3칸까지 떨어져있는 물고기를 먹어본다.
		for (int dist = 1; dist < 4; dist++) {
			int nx = youthShark.x + dx[youthShark.dir] * dist;
			int ny = youthShark.y + dy[youthShark.dir] * dist;

			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] <= 0)
				continue;

			int[][] cMap = copyMap(map);
			ArrayList<Fish> cFishes = copyFishes(fishes);

			cMap[youthShark.x][youthShark.y] = 0;
			Fish temp = cFishes.get(map[nx][ny] - 1);
			Shark eatShark = new Shark(temp.x, temp.y, temp.dir, youthShark.maxEat + temp.num);
			temp.dead = true;
			cMap[temp.x][temp.y] = -1;

			eat(cMap, eatShark, cFishes);
		}
	}

	// 물고기는 빈칸 or 다른 물고기와 위치를 바꿔서 이동할 수 있다.
	private void moveFish(Fish fish, int[][] map, ArrayList<Fish> fishes) {
		if (fish.dead)
			return;

		for (int i = 0; i < 8; i++) {
			int ndir = (fish.dir + i) % 8;
			int nx = fish.x + dx[ndir];
			int ny = fish.y + dy[ndir];

			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] < 0)
				continue;

			map[fish.x][fish.y] = 0;

			if (map[nx][ny] == 0) { // 빈칸이면 그냥 이동
				fish.x = nx;
				fish.y = ny;
			} else { // 물고기 위치 바꿈
				Fish temp = fishes.get(map[nx][ny] - 1);
				temp.x = fish.x;
				temp.y = fish.y;
				map[fish.x][fish.y] = temp.num;
				fish.x = nx;
				fish.y = ny;
			}
			map[nx][ny] = fish.num;
			fish.dir = ndir;
			return;
		}
	}

	private int[][] copyMap(int[][] map) {
		int[][] cMap = new int[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cMap[i][j] = map[i][j];
			}
		}
		return cMap;
	}

	private ArrayList<Fish> copyFishes(ArrayList<Fish> fishes) {
		ArrayList<Fish> cFishes = new ArrayList<>();
		for (int i = 0; i < fishes.size(); i++)
			cFishes.add(new Fish(fishes.get(i).x, fishes.get(i).y, fishes.get(i).num, fishes.get(i).dir,
					fishes.get(i).dead));
		return cFishes;
	}
}
