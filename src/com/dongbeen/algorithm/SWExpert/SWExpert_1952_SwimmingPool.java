package com.dongbeen.algorithm.SWExpert;

import java.io.IOException;
import java.util.Scanner;

public class SWExpert_1952_SwimmingPool {
	int[] pay;
	int[] month;
	int minCost;

	public static void main(String args[]) throws IOException {
		SWExpert_1952_SwimmingPool m = new SWExpert_1952_SwimmingPool();
		m.service();
	}

	public void service() throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			pay = new int[4];
			month = new int[12];

			for (int i = 0; i < 4; i++)
				pay[i] = sc.nextInt();

			for (int i = 0; i < 12; i++)
				month[i] = sc.nextInt();

			minCost = pay[3];
			dfs(0, 0);
			System.out.println("#" + t + " " + minCost);
		}

	}

	public void dfs(int monthCnt, int sum) throws IOException {
		if (monthCnt >= 12) {
			minCost = Math.min(minCost, sum);
			return;
		}
		dfs(monthCnt + 1, sum + month[monthCnt] * pay[0]);
		dfs(monthCnt + 1, sum + pay[1]);
		dfs(monthCnt + 3, sum + pay[2]);
		dfs(monthCnt + 12, sum + pay[3]);
	}
}