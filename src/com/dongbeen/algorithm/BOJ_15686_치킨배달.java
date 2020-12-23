package com.dongbeen.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달 {
	static int N = 0;
	static int M = 0;
	static List<House> houses = new ArrayList<>();
	static List<House> chicken = new ArrayList<>();
//	static int[][] city;
//	static int[][] chicken;
	static int seleted = 0;
	static int[] visited;
	static int minDistance = 1000000;

	static class House {
		int r;
		int c;

		House(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());

//		city = new int[N][N];
//		chicken = new int[N][N];

		for (int r = 1; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c < N; c++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1)
//					city[r][c] = temp;
					houses.add(new House(r, c));
				if (temp == 2)
//					chicken[r][c] = temp;
					chicken.add(new House(r, c));
			}
		}
		visited = new int[chicken.size()];
		
		DFS(0, 0);
		
		System.out.println(minDistance);
	}

	public static int distance(House a, House b) {
		return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
	}

	public static void DFS(int index, int selected) {
		if (selected == M) {
			int result = 0;
			for (int i = 0; i < houses.size(); i++) {
				int dist = 0;
				int minDist = 100000;
				for (int j = 0; j < chicken.size(); j++) {
					if (visited[j] == 1) {
						dist = distance(houses.get(i), chicken.get(j));
						if (minDist > dist)
							minDist = dist;
					}
				}
				result += minDist;
			}
			if (minDistance > result)
				minDistance = result;
		}
		
		if (index == chicken.size())
			return;
		
		visited[index] = 1;
		DFS(index + 1, selected + 1);
		visited[index] = 0;
		DFS(index + 1, selected);
	}
}
