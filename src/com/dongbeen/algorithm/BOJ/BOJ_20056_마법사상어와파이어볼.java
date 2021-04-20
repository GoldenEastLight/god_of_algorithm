package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_20056_마법사상어와파이어볼 {
	int N, M, K;
	int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 12시방향부터 시계방향
	int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	ArrayList<Fireball>[][] map;
	ArrayList<Fireball> fireList = new ArrayList<>();
	int massLeft = 0; // 이동 완료 후 남은 질량

	class Fireball {
		int y;
		int x;
		int mass; // 질량
		int speed; // 속력
		int dir; // 방향

		Fireball(int y, int x, int mass, int speed, int dir) {
			this.y = y;
			this.x = x;
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		new BOJ_20056_마법사상어와파이어볼().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = new ArrayList<Fireball>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			fireList.add(new Fireball(r, c, mass, speed, dir));
		}

		for (int i = 0; i < K; i++) {
			move();
		}

		for (int i = 0; i < fireList.size(); i++)
			massLeft += fireList.get(i).mass;

		System.out.println(massLeft);
	}

	private void move() {
		int listSize = fireList.size();
		// 파이어볼 이동
		for (int i = 0; i < listSize; i++) {
			Fireball temp = fireList.get(i);
			int ny = temp.y + dy[temp.dir] * (temp.speed % N);
			int nx = temp.x + dx[temp.dir] * (temp.speed % N);
			if (ny < 0)
				ny = N + ny;
			else
				ny = ny % N;
			if (nx < 0)
				nx = N + nx;
			else
				nx = nx % N;

			temp.y = ny;
			temp.x = nx;

			map[ny][nx].add(temp);
		}
		// 파이어볼 두개 이상 든 곳에서
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() == 1)
					map[i][j].clear();
				if (map[i][j].size() < 2)
					continue;

				int massSum = 0, sSum = 0;

				boolean even = map[i][j].get(0).dir % 2 == 0 ? true : false;
				boolean odd = map[i][j].get(0).dir % 2 == 1 ? true : false;

				for (Fireball cur : map[i][j]) {
					massSum += cur.mass;
					sSum += cur.speed;
					even = even & cur.dir % 2 == 0 ? true : false;
					odd = odd & cur.dir % 2 == 1 ? true : false;
					fireList.remove(cur);
				}

				int newMass = massSum / 5;
				int size = map[i][j].size();
				map[i][j].clear();

				if (newMass == 0)
					continue;
				int newS = sSum / size;

				if (even | odd) { // 0 2 4 6
					for (int k = 0; k < 8; k += 2) {
						fireList.add(new Fireball(i, j, newMass, newS, k));
					}
				} else {
					for (int k = 1; k < 8; k += 2) {
						fireList.add(new Fireball(i, j, newMass, newS, k));
					}
				}
			}
		}
	}
}
