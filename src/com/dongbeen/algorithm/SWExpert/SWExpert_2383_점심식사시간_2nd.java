package com.dongbeen.algorithm.SWExpert;

import java.util.PriorityQueue;
import java.util.Scanner;

public class SWExpert_2383_점심식사시간_2nd {
	static int N;
	static Stair[] stairs;
	static Person[] people;
	static int[][] map;

	public static void main(String[] args) {
		// 입력받기
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			stairs = new Stair[2];
			people = new Person[10];
			int pCnt = 0; // 지금까지 입력받은 사람이 몇인지.
			int sCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					// 0은 빈칸이니까 넘어가고.
					if (map[i][j] == 0)
						continue;
					// 1이면 사람 입력.
					else if (map[i][j] == 1) {
						people[pCnt++] = new Person(i, j);
					}
					// 아니면. 계단.
					else {
						stairs[sCnt++] = new Stair(i, j, map[i][j]);
					}
				}
			}
			// 각 사람별로, 각 계단까지의 거리를 구해자.
			for (int i = 0; i < pCnt; i++) {
				for (int j = 0; j < sCnt; j++) {
//					i번 사람과 j번 계단 사이의 거리를 구해서
//					i번사람의 dist j에다가 적어주자.
					people[i].dist[j] = Math.abs(people[i].r - stairs[j].r) + Math.abs(people[i].c - stairs[j].c) + 1;
				}
			}
			ans = 987654321;
			powerSet(0, pCnt);
			System.out.println("#" + tc + " " + ans);
		}
	}

	static class Person implements Comparable<Person> {
		int r, c;
		int[] dist;

		Person(int r, int c) {
			this.r = r;
			this.c = c;
			dist = new int[2];// 계단이 두개. 항상 두개.
		}

		int sel;// 배정된 계단의 번호를 저장할 변수.

		@Override
		public int compareTo(Person o) {
			// TODO Auto-generated method stub
			return dist[sel] - o.dist[o.sel];
		}
	}

	static class Stair {
		int r, c, height;

		Stair(int r, int c, int height) {
			this.r = r;
			this.c = c;
			this.height = height;
		}
	}

	static int ans;

	// idx번째 사람에 대해서, 계단을 배정
	static void powerSet(int idx, int pCnt) {

		if (idx == pCnt) {
			int[][] stairTimeTable = new int[2][200];
			PriorityQueue<Person>[] stairQueue = new PriorityQueue[2];
			stairQueue[0] = new PriorityQueue<>();
			stairQueue[1] = new PriorityQueue<>();
			for (int i = 0; i < pCnt; i++) {
				stairQueue[people[i].sel].add(people[i]);
			}
			int max = 0;
			// 모든 계단에 대해서.
			for (int i = 0; i < stairs.length; i++) {
				// 빨리 도착하는 사람부터 꺼내서
				int to = 0;
				while (!stairQueue[i].isEmpty()) {
					Person p = stairQueue[i].poll();
					int from = p.dist[p.sel];// 선택된 계단까지 거리부터 계단을 내려가기 시작.
					to = from + stairs[p.sel].height;// 도착부터, 내가 선택된 계단의 높이만큼 계단에 머물음.
					for (int j = from; j < to; j++) {
						if (stairTimeTable[p.sel][j] == 3) {
							to++;
							continue;
						}
						stairTimeTable[p.sel][j]++;
					}
				}
				if (to > max)
					max = to;
			}
			if (ans > max)
				ans = max;
			return;
		}
		people[idx].sel = 0;
		powerSet(idx + 1, pCnt);
		people[idx].sel = 1;
		powerSet(idx + 1, pCnt);
	}
}