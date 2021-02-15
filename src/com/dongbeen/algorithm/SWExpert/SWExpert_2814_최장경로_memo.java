package com.dongbeen.algorithm.SWExpert;
import java.util.Scanner;

public class SWExpert_2814_최장경로_memo {
	static int N; // 정점의 수
	static int M; // 간선의 수
	static int[][] adj; // 인접행렬
	static int[][] memo;// 메모하기 위함

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			adj = new int[N + 1][N + 1];
			memo = new int[N + 1][1 << (N + 1)]; // 각비트를 정점으로 활용하기 위해서

			// 무방향 그래프이므로 양쪽표시
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				adj[a][b] = 1;
				adj[b][a] = 1;
			}
			ans = 0;
			// 모든 정점 출발
			for (int i = 1; i <= N; i++) {
				// 정점과 방문상태를 정수 값으로 들고 이동
				ans = Math.max(ans, dfs(i, 1 << i));
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	static int ans;

	static int dfs(int v, int visited) {
		int ret = 1; // 기본값


		for (int i = 1; i <= N; i++) {
			//이미 방문했으면 패스/
			if ((visited & (1 << i)) != 0)
				continue;
			//경로 없으면 패스/
			if (adj[v][i] == 0)
				continue;
			// 방문하지 않았고 연결되어 있다면
			// 기본값과 재귀를 타고 내려가서 반환된 값+1 중 더큰 값을 길이로 가진다.
			ret = Math.max(ret, dfs(i, visited | (1 << i)) + 1);
		}
		return ret;
	}
}