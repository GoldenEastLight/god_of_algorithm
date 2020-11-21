/**
 * 프림 : 임의의 정점을 시작지점으로 하여 시작
 * 신장트리에 포함된 정점과 자신을 연결하는 간선 비용 최솟값
 */
package master_of_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWexpert_1251_하나로_프림 {
	private static int N;
	private static long[][] adjMatrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			adjMatrix = new long[N][N];

			int[] x = new int[N];
			int[] y = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			} // N개 섬의 x좌표
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			} // N개 섬의 y좌표

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					adjMatrix[r][c] = adjMatrix[c][r] = getDistance(x[r], x[c], y[r], y[c]);
				}
			} // 인접행렬 완성

			double E = Double.parseDouble(br.readLine());
			System.out.println("#" + t + " " + Math.round(E * makeMST()));
		}
	}

	private static double makeMST() {
		long[] minEdge = new long[N];
		boolean[] visited = new boolean[N];

		long result = 0; // 최소신장트리비용
		int cnt = 0; // 처리한 정점 수

		Arrays.fill(minEdge, Long.MAX_VALUE);
		minEdge[0] = 0; // 0섬을 시작섬으로.

		while (true) {
			// 1단계 : 신장트리에 포함되지 않은 정점중 최소간선비용의 정점 선택
			long min = Long.MAX_VALUE;
			int minNo = 0; // 최소간선비용의 정점

			for (int i = 0; i < N; i++) {
				if (!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minNo = i;
				}
			}

			visited[minNo] = true; // 정점 방문 처리(신장트리에 포함시킴)
			result += min; // 간선비용 누적
			if (++cnt == N)
				break;

			// 2단계 : 선택된 정점에서 신장트리에 포함되지 않은 다른 정점들로의 간선의 비용을 고려하여 minEdge 업데이트
			for (int i = 0; i < N; i++) {
				if (!visited[i] && adjMatrix[minNo][i] > 0 && minEdge[i] > adjMatrix[minNo][i]) {
					minEdge[i] = adjMatrix[minNo][i];
				}
			}
		}

		return result;
	}

	private static long getDistance(int x1, int x2, int y1, int y2) {
		return (long) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}
