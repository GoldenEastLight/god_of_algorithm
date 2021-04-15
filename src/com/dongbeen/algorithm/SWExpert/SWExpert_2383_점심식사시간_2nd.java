package com.dongbeen.algorithm.SWExpert;

import java.util.PriorityQueue;
import java.util.Scanner;

public class SWExpert_2383_점심식사시간_2nd {
	static int N;
	static Stair[] stairs;
	static Person[] people;
	static int[][] map;
	static int ans;

	static class Person implements Comparable<Person> {
		int r, c;
		int[] dist;
		int selectStair;// 배정된 계단의 번호를 저장할 변수.

		Person(int r, int c) {
			this.r = r;
			this.c = c;
			dist = new int[2];// 계단이 두개. 항상 두개.
		}

		@Override
		public int compareTo(Person o) {
			// TODO Auto-generated method stub
			return dist[selectStair] - o.dist[o.selectStair];
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

	public static void main(String[] args) {
		// 입력받기
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			stairs = new Stair[2];
			people = new Person[10];
			int pCnt = 0; // 지금까지 총 몇명을 입력 받았는지.
			int sCnt = 0; // 계단
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
			// 각 사람별로, 각 계단까지의 거리를 구하자.
			for (int i = 0; i < pCnt; i++) {
				for (int j = 0; j < sCnt; j++) {
					// i번 사람과 j번 계단 사이의 거리를 구해서
					// i번사람의 dist j에다가 적어주자.
					people[i].dist[j] = Math.abs(people[i].r - stairs[j].r) + Math.abs(people[i].c - stairs[j].c) + 1;
				}
			}
			ans = Integer.MAX_VALUE;
			powerSet(0, pCnt);
			System.out.println("#" + tc + " " + ans);
		}
	}

	// idx번째 사람에 대해서, 계단을 배정
	static void powerSet(int idx, int pCnt) {
		if (idx == pCnt) {
			int[][] stairTimeTable = new int[2][200];
			PriorityQueue<Person>[] stairQueue = new PriorityQueue[2];
			stairQueue[0] = new PriorityQueue<>();
			stairQueue[1] = new PriorityQueue<>();
			for (int i = 0; i < pCnt; i++) {
				stairQueue[people[i].selectStair].add(people[i]);
			}
			int max = 0;
			// 모든 계단에 대해서. (2개)
			for (int i = 0; i < stairs.length; i++) {
				// 빨리 도착하는 사람부터 꺼내서
				int to = 0;
				while (!stairQueue[i].isEmpty()) {
					Person temp = stairQueue[i].poll();
					int from = temp.dist[temp.selectStair]; // 선택된 계단까지 거리부터 계단을 내려가기 시작.
					to = from + stairs[temp.selectStair].height; // 도착부터, 내가 선택된 계단의 높이만큼 계단에 머물음.
		   			for (int j = from; j < to; j++) { // 계단까지 거리부터 시작해서 계단의 높이만큼 계단을 차지하고 있음
						if (stairTimeTable[temp.selectStair][j] == 3) { // 한 계단에는 최대 3명까지만 동시에 내려갈 수 있다.
							to++; // 모든 사람이 계단을 내려오는데 걸리는 시간이 1분 더 늘어난다.
							continue;
						}
						stairTimeTable[temp.selectStair][j]++;
					}
				}
				if (to > max)
					max = to;
			}
			if (ans > max)
				ans = max;
			return;
		}
		people[idx].selectStair = 0;
		powerSet(idx + 1, pCnt);
		people[idx].selectStair = 1;
		powerSet(idx + 1, pCnt);
	}
}