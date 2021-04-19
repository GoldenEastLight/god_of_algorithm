package com.dongbeen.algorithm.BOJ;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_18513_샘터 {

	static class house { // 현재 위치와 샘터로부터 거리 저장할 class
		int loc;
		int dist;

		public house(int loc, int dist) {
			this.loc = loc;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		Queue<house> que = new LinkedList<house>();
		boolean Visit[] = new boolean[150000000]; // 0 ~ 100,100,000 까지 사용
		boolean mVisit[] = new boolean[150000000];// -100,100,000 ~ -1 까지 사용

		for (int i = 0; i < N; i++) {
			int loc = sc.nextInt(); // 샘터 위치
			que.offer(new house(loc, 0)); // 샘터 다 큐에 넣기
			if (loc < 0) // 위치가 음수라면
				mVisit[-loc] = true; // 음수 visit에 처리
			else if (loc >= 0)// 위치가 양수라면
				Visit[loc] = true; // 양수 visit에 처리

		}

		int dl[] = { -1, 1 }; // 현재 집으로 부터 지어질 양쪽집 방향
		int cnt = 0; // 세워진 집의 갯수
		long result = 0; // 결과값

		outer: while (!que.isEmpty()) { // bfs돌기
			house now = que.poll();
			for (int i = 0; i < 2; i++) { // 두 방향에 대해
				int nextL = now.loc + dl[i]; // 다음 방향 가보기
				int nextD = now.dist + 1; // 샘터하고 1만큼 더 멀어지기
				if (nextL >= 0 && Visit[nextL]) // 다음 위치가 양수면 양수 visit검사
					continue;
				if (nextL < 0 && mVisit[-nextL]) // 다음 위치가 음수면 음수 visit검사
					continue;
				result += (long) nextD; // 아직 비어있는 공간이면 결과값 거리만큼 +
				cnt++; // 집도 지어졌으니 cnt ++
				if (cnt == K) // 모든 집 다 지었으면 종료
					break outer;
				if (nextL >= 0) // 다음 위치가 양수면
					Visit[nextL] = true; // 양수 visit 처리
				if (nextL < 0) // 음수면
					mVisit[-nextL] = true; // 음수 visit처리
				que.offer(new house(nextL, nextD)); // 다음집 집에 넣기
			}
		}
		System.out.println(result); // 결과 출력
		sc.close();
	}
}