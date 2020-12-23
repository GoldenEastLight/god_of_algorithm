package com.dongbeen.algorithm;

import java.util.Scanner;

public class SWExpert_2112_보호필름 {
	int D, W, K; // 깊이, 너비, 필요K(통과기준)
	int[][] film; // 보호필름의 상태를 저장할 배열.
	int ans;

	public static void main(String[] args) {
		SWExpert_2112_보호필름 m = new SWExpert_2112_보호필름();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++)
					film[i][j] = sc.nextInt();
			}
			ans = 987654321;
			solve(0, 0);
			System.out.println("#" + tc + " " + ans);
		}
	}

	// 상태공간트리의 상태는, 몇번째 행을 주입여부 결정 중이며, 지금까지 주입한 횟수는 몇회인지.
	void solve(int row, int cnt) {
		// 현재 필름이 ok라면 현재까지 cnt를 체크하자.
		if (isOk()) {
			ans = Math.min(ans, cnt);
			return;
		}
		// 벌써 발견된 최소횟수를 넘어갔으면 더 볼것도 없다.
		if (ans < cnt)
			return;
		// ㅣ마지막 행까지 검사 완료
		if (row == D)
			return;

		// 그냥가기.
		solve(row + 1, cnt);
		// 원래 필름의 상태 백업해두자.
		int[] tmp = new int[W];
		for (int i = 0; i < W; i++)
			tmp[i] = film[row][i];
		// A주입하고 가기.
		for (int i = 0; i < W; i++)
			film[row][i] = 0;
		solve(row + 1, cnt + 1);
		// B주입하고 가기.
		for (int i = 0; i < W; i++)
			film[row][i] = 1;
		solve(row + 1, cnt + 1);
		// 원상복구.
		for (int i = 0; i < W; i++)
			film[row][i] = tmp[i];
	}

	boolean isOk() {
		// 모든 열을 검사
		for (int j = 0; j < W; j++) {
			// cnt는 1부터 시작. (연속한 셀의 갯수를 셀 변수)
			boolean colOk = false;
			int cnt = 1;

			// 1번 행부터. 이전행과 현재행이 같은지 검사해가자.
			for (int i = 1; i < D; i++) {
				// 현재행이 이전행과 다르다면. 카운트 다시 세기 시작.
				if (film[i][j] != film[i - 1][j])
					cnt = 1;
				else
					cnt++;
				// 기준을 만족하면 현재 열이 ok인걸로 체크.
				if (cnt == K) {
					colOk = true;
					break;
				}
			}
			// 검사를 마친 열이 기준을 만족하지 못했다면, 전체 셀은 불합격.
			if (!colOk)
				return false;
		}
		// 모든 열이 한번도 불합격을 만나지 않았으므로 합격.
		return true;
	}
}
