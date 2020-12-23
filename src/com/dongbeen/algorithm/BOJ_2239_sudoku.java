package com.dongbeen.algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BOJ_2239_sudoku {
	int[][] sudoku = new int[9][9];
	ArrayList<int[]> zeroLocation = new ArrayList<>();
	int finish;
	boolean gguet = false;

	public static void main(String[] args) throws IOException {
		BOJ_2239_sudoku m = new BOJ_2239_sudoku();
		m.service();
	}

	private void service() throws IOException {
		Scanner sc = new Scanner(System.in);

		for (int r = 0; r < 9; r++) {
			char[] line = sc.nextLine().toCharArray();
			for (int c = 0; c < 9; c++) {
				sudoku[r][c] = line[c] - '0';
				if (sudoku[r][c] == 0)
					zeroLocation.add(new int[] { r, c });
			}
		}
		finish = zeroLocation.size();

		putNumber(0);
		printSudoku();
	}

	private void putNumber(int index) {
		if (index >= finish) {
			gguet = true;
			return;
		}

		int[] temp = zeroLocation.get(index);
		int y = temp[0];
		int x = temp[1];
		List<Integer> canNum = new LinkedList<>();

		for (int i = 1; i <= 9; i++) {
			if (!checkY(y, x, i))
				continue;
			if (!checkX(y, x, i))
				continue;
			if (!checkBox(y, x, i))
				continue;
			canNum.add(i);
		}
		int size = canNum.size();

		for (int i = 0; i < size; i++) {
			sudoku[y][x] = canNum.get(i);
			putNumber(index + 1);
			if (gguet == true)
				return;
			sudoku[y][x] = 0;
		}
	}

	private boolean checkBox(int y, int x, int i) {
		boolean b = true;

		int R = y / 3 * 3, C = x / 3 * 3;

		for (int r = R; r <= R + 2; r++) {
			for (int c = C; c <= C + 2; c++) {
				if (r == y && c == x)
					continue;
				if (sudoku[r][c] == i)
					return false;
			}
		}
		return b;
	}

	private boolean checkX(int y, int x, int i) {
		for (int c = 0; c < 9; c++) {
			if (c == x)
				continue;
			if (sudoku[y][c] == i)
				return false;
		}
		return true;
	}

	private boolean checkY(int y, int x, int i) {
		for (int r = 0; r < 9; r++) {
			if (r == y)
				continue;
			if (sudoku[r][x] == i)
				return false;
		}
		return true;
	}

	private void printSudoku() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				System.out.print(sudoku[r][c]);
			}
			System.out.println();
		}
	}
}
