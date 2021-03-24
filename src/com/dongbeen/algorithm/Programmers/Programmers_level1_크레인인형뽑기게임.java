package com.dongbeen.algorithm.Programmers;

import java.util.Stack;

public class Programmers_level1_크레인인형뽑기게임 {
	public int solution(int[][] board, int[] moves) {
		int answer = 0;
		Stack<Integer> stack = new Stack<>();

		for (Integer move : moves) {
			for (int i = 0; i < board.length; i++) {
				int doll = board[i][move - 1];
				if (doll == 0)
					continue;

				if (!stack.isEmpty() && stack.peek() == doll) {
					stack.pop();
					answer += 2;
				} else {
					stack.push(doll);
				}
				board[i][move - 1] = 0;
				break;
			}
		}
		return answer;
	}
}