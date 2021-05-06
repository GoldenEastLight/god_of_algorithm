package com.dongbeen.algorithm.SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * T = 20
 * 충전하려는 player :: 2aud rhwjd!
 * 충전가능한 BC :: 1 ~ 8
 * 
 * 각 궤도의 위치마다 두 플레이어의 충전가능한 AP list 구하기
 * player A의 APList(?, ?)
 * player B의 APList(?, ?)
 * -> 조합 -> 두 플레이어의 충전량의 합이 최대가 되도록 처리
 * 
 * 위를 매 시간마다 반복해서 매 시간마다의 최대 충전량 누적합
 */

public class SWExpert_5644_무선충전_조합 {
	int M, aCnt; // 시간, 충전소 개수
	int[] pathA, pathB, playerA, playerB;
	int[][] ap;
	int[] dx = { 0, 0, 1, 0, -1 }; // 이동하지않음, 상, 우, 하, 좌
	int[] dy = { 0, -1, 0, 1, 0 }; // 이동하지않음, 상, 우, 하, 좌

	public static void main(String[] args) throws Exception {
		new SWExpert_5644_무선충전_조합().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		playerA = new int[2];
		playerB = new int[2];

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			aCnt = Integer.parseInt(st.nextToken());

			// 두 플레이어의 초기위치
			playerA[0] = playerA[1] = 1;
			playerB[0] = playerB[1] = 10;

			pathA = new int[M + 1]; // 0초, 즉 처음위치에서도 처리 하도록 M + 1;
			pathB = new int[M + 1];
			ap = new int[aCnt][4];

			String charsA = br.readLine();
			String charsB = br.readLine();

			for (int c = 0; c < M; c++) {
				pathA[c + 1] = charsA.charAt(c * 2) - '0';
				pathB[c + 1] = charsB.charAt(c * 2) - '0';
			}

			for (int a = 0; a < aCnt; a++) {
				st = new StringTokenizer(br.readLine(), " ");
				ap[a][0] = Integer.parseInt(st.nextToken()); // x
				ap[a][1] = Integer.parseInt(st.nextToken()); // y
				ap[a][2] = Integer.parseInt(st.nextToken()); // c (충전 범위)
				ap[a][3] = Integer.parseInt(st.nextToken()); // p (성능)
			}

			System.out.println("#" + t + " " + move());
		}
	}

	private int move() { // 매 시간 마다 두 플레이어의 충전량의 합의 최대값을 구하고 그 값을 모든 시간동안 누적
		ArrayList<Integer> apListA, apListB;
		int totalSum = 0;
		int time = 0;
		while (time <= M) {
			// 두 플레이어를 해당 시간의 이동 정보에 맞게 이동
			playerA[0] += dx[pathA[time]];
			playerA[1] += dy[pathA[time]];
			playerB[0] += dx[pathB[time]];
			playerB[1] += dy[pathB[time]];

			// 두 플레이어의 각자 위치 기준으로 충전 가능한 충전소 리스트 가져오기
			apListA = getAp(playerA[0], playerA[1]);
			apListB = getAp(playerB[0], playerB[1]);

			totalSum += getCharge(apListA, apListB);
			++time;
		}
		return totalSum;
	}

	private ArrayList<Integer> getAp(int x, int y) {
		ArrayList<Integer> apList = new ArrayList<Integer>();
		int distance = 0;
		for (int a = 0; a < aCnt; a++) {
			distance = Math.abs(ap[a][0] - x) + Math.abs(ap[a][1] - y);
			if (distance <= ap[a][2])
				apList.add(a);
		}
		return apList;
	}

	private int getCharge(ArrayList<Integer> apListA, ArrayList<Integer> apListB) {
		int max = 0, temp = 0;
		int aSize = apListA.size(), bSize = apListB.size();

		if (aSize == 0 && bSize == 0)
			return 0;
		else if (aSize == 0) // 플레이어 B만 충전 가능한 상황
			return getMaxPower(apListB);
		else if (bSize == 0) // 플레이어 A만 충전 가능한 상황
			return getMaxPower(apListA);

		// 플레이어 A, B 모두 충전 가능한 상황은 다 조합을 고려해본다.
		for (Integer a : apListA) {
			for (Integer b : apListB) {
				if (a != b)
					temp = ap[a][3] + ap[b][3];
				else
					temp = Math.max(ap[a][3], ap[b][3]);

				if (max < temp)
					max = temp;
			}
		}

		return max;
	}

	private int getMaxPower(ArrayList<Integer> apList) {
		int max = 0;
		for (Integer a : apList) {
			if (max < ap[a][3])
				max = ap[a][3];
		}
		return max;
	}
}
