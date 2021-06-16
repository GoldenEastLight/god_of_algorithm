package com.dongbeen.algorithm.SWExpert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 방사선치료 {
	private int N;
	private int M;
	private List<Cancer> list;

	public static void main(String[] args) {
		new 방사선치료().service();
	}

	private void service() {
		Scanner scn = new Scanner(System.in);

		int T = scn.nextInt();
		for (int t = 1; t <= T; t++) {
			N = scn.nextInt();
			M = scn.nextInt();
			list = new ArrayList<Cancer>();
			for (int i = 0; i < N; i++) {
				int x1 = scn.nextInt();
				int y1 = scn.nextInt();
				int x2 = scn.nextInt();
				int y2 = scn.nextInt();
				list.add(new Cancer(x1, y1, x2, y2));
			}

			int start = 0;
			int end = 300;
			while (true) {
				if (end - start <= 1)
					break;
				int mid = (start + end) / 2;
				if (check(mid)) {
					end = mid;
				} else {
					start = mid;
				}
			}
			System.out.println("#" + t + " " + end);
		}

		scn.close();
	}

	private boolean check(int size) {
		for (int i = 0; i < 300 - size + 1; i++) {
			for (int j = 0; j < 300 - size + 1; j++) {
				if (isOk(i, j, i + size, j + size))
					return true;
			}
		}
		return false;
	}

	private boolean isOk(int cx1, int cy1, int cx2, int cy2) {
		int count = N;
		for (int i = 0; i < list.size(); i++) {
			int x1 = list.get(i).x1;
			int y1 = list.get(i).y1;
			int x2 = list.get(i).x2;
			int y2 = list.get(i).y2;
			if (cx1 <= x1 && cx1 <= x2 && cx2 >= x1 && cx2 >= x2) {
				if (cy1 <= y1 && cy1 <= y2 && cy2 >= y1 && cy2 >= y2) {
					count--;
				}
			}
		}
		if (count > M)
			return false;
		return true;
	}

}

class Cancer {
	public int x1, y1, x2, y2;

	public Cancer() {
	};

	public Cancer(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}