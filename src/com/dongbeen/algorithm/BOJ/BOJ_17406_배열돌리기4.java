package com.dongbeen.algorithm.BOJ;
/*
5 6 2
1 2 3 2 5 6
3 8 7 2 1 3
8 2 3 1 4 5
3 4 5 1 1 1
9 3 2 1 4 3
3 4 2
4 2 1
*/



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17406_배열돌리기4 {
	static int[][] original;
	static int[][] copy;
	static int[][] copyBefore;
	static int N;
	static int M;
	static int K;
	static int minResult = 1000000;
	static int[] visited;
	static int[] R;
	static int[] C;
	static int[] S;
	static int[] order;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = new int[K];
		C = new int[K];
		S = new int[K];
		visited = new int[K];
		order = new int[K];

		original = new int[N + 1][M + 1];
		copy = new int[N + 1][M + 1];
		copyBefore = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				original[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = original[i][j];
				copyBefore[i][j] = original[i][j];
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			R[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			S[i] = Integer.parseInt(st.nextToken());
		}

		circle(0);

		System.out.println(minResult);
	}

	public static void circle(int cnt) {
		if (cnt == K) {
			for (int k = 0; k < K; k++) {
				int index = order[k];
				for (int s = S[index]; s >= 1; s--) {
					for (int right = C[index] - s; right < C[index] + s; right++) {
						copy[R[index] - s][right + 1] = copyBefore[R[index] - s][right];
					}
					for (int down = R[index] - s; down < R[index] + s; down++) {
						copy[down + 1][C[index] + s] = copyBefore[down][C[index] + s];
					}
					for (int left = C[index] + s; left > C[index] - s; left--) {
						copy[R[index] + s][left - 1] = copyBefore[R[index] + s][left];
					}
					for (int up = R[index] + s; up > R[index] - s; up--) {
						copy[up - 1][C[index] - s] = copyBefore[up][C[index] - s];
					}
				}
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= M; j++) {
						copyBefore[i][j] = copy[i][j];
					}
				}
			}
			
			mapResult();
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					copyBefore[i][j] = original[i][j];
					copy[i][j] = original[i][j];
				}
			}
		} else {
			for (int i = 0; i < K; i++) {
				if (visited[i] == 1)
					continue;
				order[cnt] = i;
				visited[i] = 1;
				circle(cnt + 1);
				visited[i] = 0;
			}
		}
	}

	public static void mapResult() {
		for (int i = 1; i <= N; i++) {
			int result = 0;
			for (int j = 1; j <= M; j++) {
				result += copy[i][j];
			}
			if (result < minResult)
				minResult = result;
		}
	}
}