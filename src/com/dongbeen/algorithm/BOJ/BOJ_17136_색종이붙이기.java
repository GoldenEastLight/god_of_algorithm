package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {
	int[][] map = new int[10][10]; // 색종이를 붙일 10 * 10 종이
	int[] papers = { 5, 5, 5, 5, 5 }; // 1 * 1, 2 * 2, 3 * 3, 4 * 4, 5 * 5 색종이 각 5개씩
	int minPaperCnt = Integer.MAX_VALUE; // 1이 적힌 모든 칸을 붙이는 데 필요한 색종이의 최소 개수

	public static void main(String[] args) throws Exception {
		new BOJ_17136_색종이붙이기().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		paste(0, 0, 0);

		if (minPaperCnt == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(minPaperCnt);
	}

	private void paste(int y, int x, int cnt) {
		if (y == 9 && x == 10) { // 마지막 (9, 9) 넘어가면.
			minPaperCnt = Math.min(minPaperCnt, cnt);
			return;
		}
		if (cnt >= minPaperCnt)
			return;
		// 마지막 x 넘으면 다음 y로
		if (x > 9) {
			paste(y + 1, 0, cnt);
			return;
		}

		if (map[y][x] == 1) {
			for (int i = 4; i >= 0; i--) {
				if (papers[i] > 0 && possible(y, x, i + 1)) {
					// 붙
					attach(y, x, i + 1);
					papers[i]--;

					// 다음
					paste(y, x + 1, cnt + 1);
					
					// 뗌
					detach(y, x, i + 1);
					papers[i]++;
				}
			}
		} else
			paste(y, x + 1, cnt);
	}

	// 색종이 붙이기 (size == 색종이 크기)
	private void attach(int y, int x, int size) {
		for (int i = y; i < y + size; i++)
			for (int j = x; j < x + size; j++)
				map[i][j] = 0;
	}

	// 색종이 다시 뗌
	private void detach(int y, int x, int size) {
		for (int i = y; i < y + size; i++)
			for (int j = x; j < x + size; j++)
				map[i][j] = 1;
	}

	// 해당 사이즈 색종이를 붙일 수 있는지
	private boolean possible(int y, int x, int size) {
		for (int i = y; i < y + size; i++)
			for (int j = x; j < x + size; j++)
				if (i < 0 || i >= 10 || j < 0 || j >= 10 || map[i][j] == 0)
					return false;
		return true;
	}
}
