package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1018_체스판다시칠하기 {
	int N;
	int M;
	int minPainting = Integer.MAX_VALUE;
	char[][] board;

	public static void main(String[] args) throws IOException {
		BOJ_1018_체스판다시칠하기 m = new BOJ_1018_체스판다시칠하기();
		m.service();
	}

	private void service() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];

		for (int y = 0; y < N; y++) {
			String temp = br.readLine();
			for (int x = 0; x < M; x++) {
				board[y][x] = temp.charAt(x);
			}
		}

		searchBoard();

		System.out.print(minPainting);
	}

	private void searchBoard() {
		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				int painting = 0;
				for (int y = i; y < i + 8; y++) {
					for (int x = j; x < j + 8; x++) {
						if (y % 2 == 0 && x % 2 == 0 && board[y][x] == 'B') {
							painting++;
						}
						if (y % 2 == 0 && x % 2 == 1 && board[y][x] == 'W') {
							painting++;
						}
						if (y % 2 == 1 && x % 2 == 0 && board[y][x] == 'W') {
							painting++;
						}
						if (y % 2 == 1 && x % 2 == 1 && board[y][x] == 'B') {
							painting++;
						}
					}
				}
				minPainting = Math.min(painting, minPainting);
				
				painting = 0;
				for (int y = i; y < i + 8; y++) {
					for (int x = j; x < j + 8; x++) {
						if (y % 2 == 0 && x % 2 == 0 && board[y][x] == 'W') {
							painting++;
						}
						if (y % 2 == 0 && x % 2 == 1 && board[y][x] == 'B') {
							painting++;
						}
						if (y % 2 == 1 && x % 2 == 0 && board[y][x] == 'B') {
							painting++;
						}
						if (y % 2 == 1 && x % 2 == 1 && board[y][x] == 'W') {
							painting++;
						}
					}
				}
				minPainting = Math.min(painting, minPainting);
			}
		}
	}
}
