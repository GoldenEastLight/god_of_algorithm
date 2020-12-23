package com.dongbeen.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_15685_DragonCurve {
	int N;
	Info[] info;
	boolean[][] map = new boolean[101][101]; // 0 <= y, x <= 100
	int[] dy = { 0, -1, 0, 1 }; // 방향 0 == → (x++), 1 == ↑ (y--), 2 == ← (x--), 3 == ↓ (y++)
	int[] dx = { 1, 0, -1, 0 };

	public class Info {
		int x;
		int y;
		int d;
		int g;

		public Info(int x, int y, int d, int g) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}
	}

	public static void main(String[] args) {
		BOJ_15685_DragonCurve m = new BOJ_15685_DragonCurve();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		info = new Info[N];

		for (int n = 0; n < N; n++) {
			info[n] = new Info(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());

			dragonCurve(info[n]);
		}
		System.out.println(countSquares());
	}

	private void dragonCurve(Info dragon) {
		List<Integer> dirs = new ArrayList<>();
		dirs.add(dragon.d);

		for (int g = 0; g < dragon.g; g++) {
			for (int i = dirs.size() - 1; i >= 0; i--) {
				dirs.add((dirs.get(i) + 1) % 4);
			}
		}

		map[dragon.y][dragon.x] = true;

		for (int i = 0; i < dirs.size(); i++) {
			dragon.y += dy[dirs.get(i)];
			dragon.x += dx[dirs.get(i)];

			if (dragon.y < 0 || dragon.x < 0 || dragon.y > 100 || dragon.x > 100)
				continue;

			map[dragon.y][dragon.x] = true;
		}
	}

	private int countSquares() {
		int cnt = 0;
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if (map[r][c] && map[r + 1][c] && map[r][c + 1] && map[r + 1][c + 1])
					cnt++;
			}
		}
		return cnt;
	}
}
