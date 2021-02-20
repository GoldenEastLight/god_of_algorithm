package com.dongbeen.algorithm.BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_3190_뱀 {
	int N;
	int K;
	int L;
	int[] time;
	String[] dir;
	ArrayList<Location> apples = new ArrayList<>();
	ArrayList<Location> snake = new ArrayList<>();
	int[][] direction = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌
	int dead = 0;

	public class Location {
		int y;
		int x;

		public Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		BOJ_3190_뱀 m = new BOJ_3190_뱀();
		m.service();
	}

	public boolean contains(ArrayList<Location> arrList, Location loc) {
		for (int i = 0; i < arrList.size(); i++) {
			if (arrList.get(i).y == loc.y && arrList.get(i).x == loc.x)
				return true;
		}
		return false;
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		for (int k = 0; k < K; k++) {
			apples.add(new Location(sc.nextInt(), sc.nextInt()));
		}

		L = sc.nextInt();
		time = new int[L];
		dir = new String[L];

		for (int l = 0; l < L; l++) {
			time[l] = sc.nextInt();
			dir[l] = sc.next();
		}

		snake.add(new Location(1, 1));

		int turn = 0;
		int move = 1;
		int t = 1;
		while (true) {
			dead = t;

			int ny = snake.get(snake.size() - 1).y + direction[move][0];
			int nx = snake.get(snake.size() - 1).x + direction[move][1];

			if (ny <= 0 || ny > N || nx <= 0 || nx > N || contains(snake, new Location(ny, nx)))
				break;

			if (contains(apples, new Location(ny, nx))) {
				snake.add(new Location(ny, nx));
				for (int i = 0; i < apples.size(); i++) {
					if (apples.get(i).y == ny && apples.get(i).x == nx)
						apples.remove(i);
				}
			} else {
				snake.add(new Location(ny, nx));
				snake.remove(0);
			}

			if (t <= time[L - 1] && t == time[turn]) {
				if (dir[turn].equals("D")) {
					move += 5;
					move %= 4;
				} else if (dir[turn].equals("L")) {
					move += 3;
					move %= 4;
				}
				turn++;
			}

			t++;
		}
		System.out.println(dead);

		sc.close();
	}
}
