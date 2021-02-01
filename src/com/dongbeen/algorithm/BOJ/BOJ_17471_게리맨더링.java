package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링 {
	static int N;
	static int[] node;
	static int[][] link;
	static int[] selected;
	static int guNum;
	static int minIngu = Integer.MAX_VALUE;
	static int difference = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		node = new int[N + 1];
		link = new int[N + 1][N + 1];
		selected = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			node[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int J = Integer.parseInt(st.nextToken());
			for (int j = 0; j < J; j++) {
				link[i][Integer.parseInt(st.nextToken())] = 1;
			}
		}

		for (int i = 1; i < N; i++) {
			guNum = i;
			divide(1);
		}

		if (minIngu == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(minIngu);
	}

	private static void divide(int cnt) {
		if (cnt == N + 1) {
			int sum1 = 0;
			int sum2 = 0;

			for (int i = 1; i <= N; i++) {
				if (selected[i] == 1)
					sum1 += node[i];
				else
					sum2 += node[i];
			}
			difference = Math.abs(sum1 - sum2);

			if (linkCheck(0) && linkCheck(1))
				minIngu = Math.min(minIngu, difference);
			return;
		}

		selected[cnt] = 1;
		divide(cnt + 1);
		selected[cnt] = 0;
		divide(cnt + 1);
	}

	private static boolean linkCheck(int type) { // 선택된 1구역인지 선택안한 0구역인지 나눠서 체크
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<Integer>();

		for (int i = 1; i <= N; i++) {
			if (selected[i] == type) {
				queue.offer(i);
				visited[i] = true;
				break;
			}
		}

		if (queue.isEmpty()) { // 2구역으로 안나눠져 있으면 1구역이든 0구역이든 한 구역이 empty일것임
			return false;
		}

		while (!queue.isEmpty()) {
			int temp = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (visited[i])
					continue;
				if (selected[i] != type)
					continue;
				if (link[temp][i] != 1)
					continue;

				queue.offer(i);
				visited[i] = true;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (selected[i] != type) // 구역이 다르면 넘김
				continue;
			if (!visited[i]) // 같은 구역인데 방문한적이 없으면 연결이 안되어있다는 것
				return false;
		}
		return true;
	}
}