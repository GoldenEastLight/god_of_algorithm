package com.dongbeen.algorithm.BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_17135_캐슬디펜스 {
	int N, M, D;
	int[][] map;
	int[][] copyMap;
	int[] arr;
	int[] set;
	int maxKill;
	ArrayList<Enemy> enemy;
	ArrayList<Enemy> archer;

	class Enemy {
		int x, y;

		Enemy(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		new BOJ_17135_캐슬디펜스().play();
	}

	private void play() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();

		// 맨 위에는 궁수가 배치된다.
		map = new int[N + 1][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();

		arr = new int[M];

		for (int i = 0; i < M; i++)
			arr[i] = i;

		set = new int[3];
		maxKill = 0;

		combination(0, 0);

		System.out.println(maxKill);
		sc.close();
	}

	private void combination(int length, int k) {
		if (length == 3) {
			enemy = new ArrayList<>();
			copyMap = new int[N + 1][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copyMap[i][j] = map[i][j];
					if (map[i][j] == 1)
						enemy.add(new Enemy(i, j));
				}
			}
			archer = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				copyMap[N][arr[set[i]]] = 2;
				archer.add(new Enemy(N, set[i]));
			}
			game(copyMap);

			return;
		}
		if (k == arr.length)
			return;

		set[length] = arr[k];
		combination(length + 1, k + 1);
		combination(length, k + 1);
	}

	private void game(int[][] field) {
		int killCnt = 0;

		// 적이 모두 사라질 때 까지
		while (enemy.size() != 0) {
			ArrayList<Enemy> dead = new ArrayList<>();
			for (int i = 0; i < archer.size(); i++) {
				int[] dist = new int[enemy.size()];
				int minDist = Integer.MAX_VALUE;

				for (int j = 0; j < enemy.size(); j++) {
					dist[j] = distance(archer.get(i), enemy.get(j));
					minDist = Math.min(minDist, dist[j]);
				}

				ArrayList<Enemy> shoot = new ArrayList<>();

				// 최소 거리이고 사정거리 안에 들어오면 사격이 가능하다.
				for (int j = 0; j < enemy.size(); j++)
					if (dist[j] == minDist)
						if (dist[j] <= D)
							shoot.add(enemy.get(j));

				// shoot.size가 0이면 쏠 수 있는 적이 없는 상황, 1이면 바로 죽이고, 1초과하면 x가 가장 작은 적을 죽인다.
				if (shoot.size() == 0)
					continue;
				else if (shoot.size() == 1)
					dead.add(shoot.get(0));
				else if (shoot.size() > 1) {
					int minX = Integer.MAX_VALUE;

					for (int j = 0; j < shoot.size(); j++)
						minX = Math.min(minX, shoot.get(j).x);
					for (int j = 0; j < shoot.size(); j++)
						if (shoot.get(j).x == minX)
							dead.add(shoot.get(j));
				}
			}

			// dead 적들 없앤다.
			for (int i = 0; i < dead.size(); i++)
				for (int j = 0; j < enemy.size(); j++)
					if (dead.get(i).y == enemy.get(j).y && dead.get(i).x == enemy.get(j).x) {
						enemy.remove(j);
						killCnt++;
						break;
					}

			// 적 한칸씩 성으로 다가옴
			int c = enemy.size();
			while (c > 0) {
				if (enemy.get(0).y + 1 != N)
					enemy.add(new Enemy(enemy.get(0).y + 1, enemy.get(0).x));
				enemy.remove(0);
				c--;
			}
		}
		maxKill = Math.max(maxKill, killCnt);
	}

	private int distance(Enemy arc, Enemy ene) {
		return Math.abs(arc.y - ene.y) + Math.abs(arc.x - ene.x);
	}
}
