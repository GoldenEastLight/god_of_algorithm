package master_of_algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class SWExpert_5644_무선충전 {
	int M, BCCnt;
	int[][] BCInfo;
	int[] pathA, pathB;
	int[] locationA = new int[2];
	int[] locationB = new int[2];
	int[] dy = { 0, -1, 0, 1, 0 }; // 제자리, 상, 우, 하, 좌
	int[] dx = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) {
		SWExpert_5644_무선충전 m = new SWExpert_5644_무선충전();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			M = sc.nextInt();
			BCCnt = sc.nextInt();

			pathA = new int[M + 1]; // 0초 즉, 처음 시작 위치에서도 처리하도록 M+1
			pathB = new int[M + 1];
			BCInfo = new int[BCCnt][4];
			locationA[0] = locationA[1] = 1;
			locationB[0] = locationB[1] = 10;

			for (int i = 1; i <= M; i++) {
				pathA[i] = sc.nextInt();
			}
			for (int i = 1; i <= M; i++) {
				pathB[i] = sc.nextInt();
			}
			for (int i = 0; i < BCCnt; i++) {
				BCInfo[i][0] = sc.nextInt(); // x
				BCInfo[i][1] = sc.nextInt(); // y
				BCInfo[i][2] = sc.nextInt(); // c
				BCInfo[i][3] = sc.nextInt(); // p
			}

			System.out.println("#" + t + " " + move());
		}
		sc.close();
	}

	private int move() { // 매 시간마다 두 플레이어의 충전량 합의 최대값을 구하고 그 값을 모든 시간동안 누적
		ArrayList<Integer> BCListA, BCListB;

		int totalSum = 0;
		int time = 0;
		while (time <= M) {
			// 두 플레이어를 해당 시간의 이동정보에 맞게 이동
			locationA[0] += dx[pathA[time]];
			locationA[1] += dy[pathA[time]];
			locationB[0] += dx[pathB[time]];
			locationB[1] += dy[pathB[time]];

			// 두 플레이어의 자신의 위치 기준으로 충전가능한 충전소리스트 가져오기
			BCListA = getBC(locationA[0], locationA[1]);
			BCListB = getBC(locationB[0], locationB[1]);

			totalSum += getCharge(BCListA, BCListB);
			++time;
		}

		return totalSum;
	}

	private int getCharge(ArrayList<Integer> bcListA, ArrayList<Integer> bcListB) {
		int max = 0;
		int temp = 0;
		int aSize = bcListA.size();
		int bSize = bcListB.size();

		if (aSize == 0 && bSize == 0)
			return 0;
		else if (aSize == 0)
			return getMaxPower(bcListB); // playerB만 충전 가능한 상황
		else if (bSize == 0)
			return getMaxPower(bcListA); // playerA만 충전 가능한 상황

		// player A, B 모두 충전가능한 상황은 다 조합을 고려해본다.
		for (Integer a : bcListA) {
			for (Integer b : bcListB) {
				if (a != b)
					temp = BCInfo[a][3] + BCInfo[b][3];
				else
					temp = Math.max(BCInfo[a][3], BCInfo[b][3]);

				if (max < temp)
					max = temp;
			}
		}
		return max;
	}

	private int getMaxPower(ArrayList<Integer> bcList) {
		int max = 0;
		for (Integer a : bcList) {
			if (max < BCInfo[a][3])
				max = BCInfo[a][3];
		}
		return max;
	}

	private ArrayList<Integer> getBC(int x, int y) {
		ArrayList<Integer> BCList = new ArrayList<>();
		int d = 0;
		for (int i = 0; i < BCCnt; i++) {
			d = Math.abs(BCInfo[i][0] - x) + Math.abs(BCInfo[i][1] - y);
			if (d <= BCInfo[i][2])
				BCList.add(i);
		}

		return BCList;
	}
}
