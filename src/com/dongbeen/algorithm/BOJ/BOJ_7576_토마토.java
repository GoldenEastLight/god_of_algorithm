package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7576_토마토 {
	int M;
	int N;
	int[][] box;

	public static void main(String[] args) throws IOException {
		BOJ_7576_토마토 m = new BOJ_7576_토마토();
		m.service();
	}

	public void service() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[M][N];
		
		
	}
}