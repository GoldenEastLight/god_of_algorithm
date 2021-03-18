package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

/**
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다. N이 주어졌을 때, 퀸을 놓는 방법의
 * 수를 구하는 프로그램을 작성하시오. input 8 output 92
 */
public class BOJ_9663_NQueen {
	int N;
	int[] board;
	int nCount = 0;

	public static void main(String[] args) {
		BOJ_9663_NQueen m = new BOJ_9663_NQueen();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N];
		sc.close();

		nQueen(0);
		System.out.println(nCount);
	}

	private void nQueen(int depth) {
		if (depth == N) {
			nCount++;
			return;
		}

		for (int i = 0; i < N; i++) {
			board[depth] = i;

			if (possible(depth)) {
				nQueen(depth + 1);
			}
		}
	}

	private boolean possible(int column) {
		for (int i = 0; i < column; i++) {
			// 같은 행에 존재하면
			if (board[column] == board[i])
				return false;

			// 열과 행의 차가 같으면 대각선에 위치한 것이다.
			else if (Math.abs(column - i) == Math.abs(board[column] - board[i]))
				return false;
		}
		return true;
	}
}
