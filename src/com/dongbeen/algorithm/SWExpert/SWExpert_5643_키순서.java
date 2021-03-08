package com.dongbeen.algorithm.SWExpert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SWExpert_5643_키순서 {
	int N;
	int knowSequenceCnt;
	boolean[] visited;

	public static void main(String[] args) {
		SWExpert_5643_키순서 m = new SWExpert_5643_키순서();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			int M = sc.nextInt();

			List<Integer>[] up = new ArrayList[N + 1];
			List<Integer>[] down = new ArrayList[N + 1];
			knowSequenceCnt = 0;

			for (int n = 1; n <= N; n++) {
				up[n] = new ArrayList<Integer>();
				down[n] = new ArrayList<Integer>();
			}

			for (int m = 0; m < M; m++) {
				int studentA = sc.nextInt();
				int studentB = sc.nextInt();

				up[studentA].add(studentB);
				down[studentB].add(studentA);
			}

			for (int n = 1; n <= N; n++) {
				int upCnt = 0, downCnt = 0;
				visited = new boolean[N + 1];
				Queue<Integer> queue = new LinkedList<>();
				queue.offer(n);

				while (!queue.isEmpty()) {
					int temp = queue.poll();

					int length = up[temp].size();
					for (int i = 0; i < length; i++) {
						if (!visited[up[temp].get(i)]) {
							visited[up[temp].get(i)] = true;
							queue.offer(up[temp].get(i));
							upCnt++;
						}
					}
				}
				queue.offer(n);
				while (!queue.isEmpty()) {
					int temp = queue.poll();

					int length = down[temp].size();
					for (int i = 0; i < length; i++) {
						if (!visited[down[temp].get(i)]) {
							visited[down[temp].get(i)] = true;
							queue.offer(down[temp].get(i));
							upCnt++;
						}
					}
				}
				if (upCnt + downCnt >= N - 1)
					knowSequenceCnt++;
			}

			System.out.println("#" + t + " " + knowSequenceCnt);
		}
		sc.close();
	}
}
