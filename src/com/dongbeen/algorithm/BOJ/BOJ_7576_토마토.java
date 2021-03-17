package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_토마토 {
	int M;
	int N;
	int[][] box;
	int[] dy = { -1, 1, 0, 0 };
	int[] dx = { 0, 0, -1, 1 };
	int maxDays = 0;

	private class Tomato {
		int y;
		int x;

		Tomato(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BOJ_7576_토마토 m = new BOJ_7576_토마토();
		m.service();
	}

	private void service() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ripe();
	}

	private void ripe() {
		Queue<Tomato> queue = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 1) {
					queue.offer(new Tomato(i, j));
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Tomato temp = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
					continue;
				}
				
				if (box[ny][nx] != 0) {
					continue;
				}
				
				box[ny][nx] = box[temp.y][temp.x] + 1; // 최대 일수를 구하기 위해 현재 일수 +1
				queue.offer(new Tomato(ny, nx));
			}
		}
		
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(box[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				maxDays = Math.max(box[i][j], maxDays);
			}
		}
		System.out.println(maxDays - 1); // 첫날은 0일인데 첫 토마토가 1이니까 -1해서 출력
	}
}