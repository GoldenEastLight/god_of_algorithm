package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_체육복 {
	int[] visited;

	public int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;
		visited = new int[n];

		for (int i = 0; i < n; i++) {
			visited[i]++;
		}
		for (int i = 0; i < reserve.length; i++) {
			visited[reserve[i] - 1]++;
		}
		for (int i = 0; i < lost.length; i++) {
			visited[lost[i] - 1]--;
		}
		for (int i = n - 1; i >= 0; i--) {
			if (visited[i] == 2)
				if (i + 1 < visited.length && visited[i + 1] == 0) {
					visited[i]--;
					visited[i + 1] = 1;
				} else if (i - 1 >= 0 && visited[i - 1] == 0) {
					visited[i]--;
					visited[i - 1] = 1;
				}
		}
		for (int i = 0; i < n; i++) {
			if (visited[i] > 0)
				answer++;
		}
		return answer;
	}
}
