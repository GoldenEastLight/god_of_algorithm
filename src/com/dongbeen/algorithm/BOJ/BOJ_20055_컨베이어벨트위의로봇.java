package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_20055_컨베이어벨트위의로봇 {
	int N;
	int K;
	int process = 0;
	int[] belt;
	boolean[] robot;

	public static void main(String[] args) {
		new BOJ_20055_컨베이어벨트위의로봇().service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		belt = new int[2 * N];
		robot = new boolean[N];

		for (int i = 0; i < N * 2; i++) {
			belt[i] = sc.nextInt();
		}
		sc.close();

		while (durability()) {
			process++;
			// belt가 한칸 회전한다.
			int temp = belt[2 * N - 1];
			for (int i = 2 * N - 1; i > 0; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = temp;
			
			// 로봇도 벨트 위에서 회전
			for (int i = N - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
			}
			robot[0] = false;
			robot[N - 1] = false;

			// 로봇 이동 가능하면 이동
			for (int i = N - 1; i > 0; i--) {
				if (!robot[i] && robot[i - 1] && belt[i] > 0) {
					robot[i] = true;
					robot[i - 1] = false;
					belt[i]--;
				}
			}

			// 맨 앞에 로봇 올린다.
			if (belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
			}
		}

		System.out.println(process);
	}

	private boolean durability() {
		int zeroCnt = 0;
		for (int i = 0; i < N * 2; i++) {
			if (belt[i] <= 0)
				zeroCnt++;
			if (zeroCnt >= K)
				return false;
		}
		return true;
	}
	/**
	 * 1. 벨트가 한 칸 회전한다. 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약
	 * 이동할 수 없다면 가만히 있는다. (로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야
	 * 한다.) 3. 올라가는 위치에 로봇이 없다면 로봇을 하나 올린다. (내구도가 0인 칸에는 로봇이 올라갈 수 없다.) 4. 내구도가 0인
	 * 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
	 */
}
