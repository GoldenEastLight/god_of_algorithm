package com.dongbeen.algorithm.SWExpert;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// 인접 행렬을 이용한 구현
public class SWExpert_1238_Contact {
	public static int MAXN = 100;
	public static int[][] contact;
	public static int[] visit;
	public static int max;

	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("src/com/ssafy/day07/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = 10;// sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int L = sc.nextInt();
			int S = sc.nextInt();
			contact = new int[MAXN + 1][MAXN + 1];
			visit = new int[MAXN + 1];

			for (int i = 0; i < L / 2; i++) {
				int f = sc.nextInt();
				int t = sc.nextInt();
				contact[f][t] = 1;
			}
			
			Queue<Integer> queue = new LinkedList<>();
			visit[S] = 1;
			queue.offer(S);
			while (!queue.isEmpty()) {
				max = 0;
				int size = queue.size();
				for (int k = 0; k < size; k++) {
					int i = queue.poll();
					if (max < i)
						max = i;

					for (int j = 1; j <= MAXN; j++) {
						if (contact[i][j] == 1 && visit[j] == 0) {
							visit[j] = 1;
							queue.offer(j);
						}
					}
				}
			}

			System.out.println("#" + tc + " " + max);
		}
		sc.close();
	}
}
