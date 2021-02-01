package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1992_쿼드트리 {
	static int[][] map;

	public static void quadTree(int startY, int startX, int mapSize) {
		int color = map[startY][startX];
		boolean same = true;

		outer: for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (map[startY + i][startX + j] != color) {
					same = false;
					break outer;
				}
			}
		}

		if (same == true) {
			System.out.print(color);
			return;
		}

		System.out.print("(");
		int newSize = mapSize / 2;
		quadTree(startY, startX, newSize);
		quadTree(startY, startX + newSize, newSize);
		quadTree(startY + newSize, startX, newSize);
		quadTree(startY + newSize, startX + newSize, newSize);
		System.out.print(")");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = ch[j] - '0';
			}
		}

		quadTree(0, 0, N);
	}
}
