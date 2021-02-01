package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697_숨바꼭질 {
	static int[] street = new int[100001];
	static int[] visited = new int[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Second = 0;

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if (N == K) {
			System.out.println(0);
			return;
		}

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(N);
		visited[N] = 1;

		while (!queue.isEmpty()) {
			int n = queue.poll();

			if (n == K)
				break;
			int walkPlus = n + 1;
			if (walkPlus <= 100000 && visited[walkPlus] < 1) {
				queue.offer(walkPlus);
				visited[walkPlus] = visited[n] + 1;
			}
			int walkMinus = n - 1;
			if (walkMinus >= 0 && visited[walkMinus] < 1) {
				queue.offer(walkMinus);
				visited[walkMinus] = visited[n] + 1;
			}
			int teleport = 2 * n;
			if (teleport <= 100000 && visited[teleport] < 1) {
				queue.offer(teleport);
				visited[teleport] = visited[n] + 1;
			}
		}
		System.out.println(visited[K] - 1);
	}
}