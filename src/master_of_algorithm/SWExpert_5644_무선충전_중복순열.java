package master_of_algorithm;

import java.util.Scanner;

public class SWExpert_5644_무선충전_중복순열 {
	int M, BCCnt;
	int[][] BCInfo;
	int[] pathA, pathB;
	int[] locationA = new int[2];
	int[] locationB = new int[2];
	int[] dx = { 0, 0, 1, 0, -1 }; // 제자리, 상, 우, 하, 좌
	int[] dy = { 0, -1, 0, 1, 0 };

	public static void main(String[] args) {
		SWExpert_5644_무선충전_중복순열 m = new SWExpert_5644_무선충전_중복순열();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			M = sc.nextInt();
			BCCnt = sc.nextInt();

			// 두 플레이어의 초기위치
			locationA[0] = locationA[1] = 1;
			locationB[0] = locationB[1] = 10;

			pathA = new int[M + 1]; // 0초 즉, 처음 시작 위치에서도 처리하도록 M+1
			pathB = new int[M + 1];
			BCInfo = new int[BCCnt][4];

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
		int totalSum = 0;
		int time = 0;
		while (time <= M) {
			// 두 플레이어를 해당 시간의 이동정보에 맞게 이동
			locationA[0] += dx[pathA[time]];
			locationA[1] += dy[pathA[time]];
			locationB[0] += dx[pathB[time]];
			locationB[1] += dy[pathB[time]];

			totalSum += getCharge();
			++time;
		}

		return totalSum;
	}

	// 중복순열
	private int getCharge() {
		int max = 0;

		for (int a = 0; a < BCCnt; a++) { // player A의 충전소
			for (int b = 0; b < BCCnt; b++) { // player B의 충전소
				int sum = 0;
				int amountA = check(a, locationA[0], locationA[1]);
				int amountB = check(b, locationB[0], locationB[1]);
				if (a != b)
					sum = amountA + amountB;
				else
					sum = Math.max(amountA, amountB);

				if (max < sum)
					max = sum;
			}
		}

		return max;
	}

	private int check(int a, int x, int y) {
		return Math.abs(BCInfo[a][0] - x) + Math.abs(BCInfo[a][1] - y) <= BCInfo[a][2] ? BCInfo[a][3] : 0;
	}
}
