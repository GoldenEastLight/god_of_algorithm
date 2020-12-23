package com.dongbeen.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10163_색종이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] DoHwaJi = new int[101][101];
		int[] paper = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int yStart = Integer.parseInt(st.nextToken());
			int xStart = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());

			for (int r = yStart; r < yStart + height; r++) {
				for (int c = xStart; c < xStart + width; c++) {
					DoHwaJi[r][c] = i;
				}
			}
		}

		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				if (DoHwaJi[i][j] >= 1)
					++paper[DoHwaJi[i][j]];
			}
		}

		for (int i = 1; i <= N; i++) {
			System.out.println(paper[i]);
		}
	}
}
