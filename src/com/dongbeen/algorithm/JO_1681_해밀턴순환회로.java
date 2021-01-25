/**
6
0	93	23	32	39	46 
0	0	7	58	59	13 
40	98	0	14	33	98 
3	39	0	0	13	16 
51	25	19	88	0	47 
65	81	63	0	6	0 
 */

package com.dongbeen.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1681_해밀턴순환회로 {
	static int N;
	static int[][] map;
	static int[] visited;
	static int minResult = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new int[N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		visited[0] = 1;
		com(1, 0, 0);
		
		System.out.println(minResult);
	}

	private static void com(int cnt, int start, int sum) {
		if (cnt == N) {
			if (map[start][0] == 0) // 마지막 위치에서 회사로 못돌아가면 리턴
				return;
			sum += map[start][0];
			minResult = Math.min(minResult, sum);
			return;
		}
		
		

		for (int i = 1; i < N; i++) {
			if (visited[i] == 0 && map[start][i] > 0) { // 방문하지 않았고 갈 수 있는 길이면 진행
				visited[i] = 1;
				com(cnt + 1, i, sum + map[start][i]);
				visited[i] = 0;
			}
		}
	}
}
