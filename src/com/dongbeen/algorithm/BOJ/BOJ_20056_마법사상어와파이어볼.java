package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20056_마법사상어와파이어볼 {
	int N, M, K;
	int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 12시방향부터 시계방향
	int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	Fireball[][] map;
	int massLeft = 0; // 이동 완료 후 남은 질량

	class Fireball {
		int mass; // 질량
		int speed; // 속력
		int dir; // 방향
		boolean big; // 합쳐진건지
		boolean hol; // 합쳐지는 파이어볼의 방향이 모두 홀
		boolean jjack; // 합쳐지는 파이어볼의 방향이 모두 짝
		int cnt; // 합쳐진 파이어볼의 개수

		Fireball(int mass, int speed, int dir) {
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
			this.cnt = 1;
		}
	}

	public static void main(String[] args) throws Exception {
		new BOJ_20056_마법사상어와파이어볼().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Fireball[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			map[r][c] = new Fireball(mass, speed, dir);
		}

		for (int i = 0; i < K; i++) {
			move();
		}
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if (map[i][j] != null && map[i][j].big)
					massLeft += map[i][j].mass / 5 * 4;
				else if (map[i][j] != null)
					massLeft += map[i][j].mass;

		System.out.println(massLeft);
	}

	private void move() {
		Fireball[][] afterMap = new Fireball[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null && !map[i][j].big) {
					int ny = i + dy[map[i][j].dir] * map[i][j].speed;
					int nx = j + dx[map[i][j].dir] * map[i][j].speed;
					if (ny < 0)
						ny = N + ny;
					else
						ny = ny % N;
					if (nx < 0)
						nx = N + nx;
					else
						nx = nx % N;
					
					if (afterMap[ny][nx] != null) {
						afterMap[ny][nx].big = true;
						afterMap[ny][nx].mass += map[i][j].mass;
						afterMap[ny][nx].speed += map[i][j].speed;
						afterMap[ny][nx].cnt++;
						if (afterMap[ny][nx].dir == 0 || afterMap[ny][nx].dir == 2 || afterMap[ny][nx].dir == 4 || afterMap[ny][nx].dir == 6)
							if (map[i][j].dir == 0 || map[i][j].dir == 2 || map[i][j].dir == 4 || map[i][j].dir == 6)
								afterMap[ny][nx].jjack = true;
							else
								afterMap[ny][nx].jjack = false;
						else if (afterMap[ny][nx].dir == 1 || afterMap[ny][nx].dir == 3 || afterMap[ny][nx].dir == 5 || afterMap[ny][nx].dir == 7)
							if (map[i][j].dir == 1 || map[i][j].dir == 3 || map[i][j].dir == 5 || map[i][j].dir == 7)
								afterMap[ny][nx].hol = true;
							else
								afterMap[ny][nx].hol = false;
					} else {
						afterMap[ny][nx] = new Fireball(map[i][j].mass, map[i][j].speed, map[i][j].dir);
					}
				} else if (map[i][j] != null && map[i][j].big) { // 합쳐진 파이어볼이라면
					int sMass = map[i][j].mass/5; // 퍼지는 질량
					if (sMass <= 0)
						break;
					int sSpeed = map[i][j].speed/map[i][j].cnt; // 퍼지는 속도
					if (map[i][j].hol || map[i][j].jjack) {
						for (int k = 0; k <= 6; k += 2) {
							int ny = i + dy[k] * sSpeed;
							int nx = j + dx[k] * sSpeed;
							if (ny < 0)
								ny = N + ny;
							else
								ny = ny % N;
							if (nx < 0)
								nx = N + nx;
							else
								nx = nx % N;
							
							if (afterMap[ny][nx] != null) {
								afterMap[ny][nx].big = true;
								afterMap[ny][nx].mass += sMass;
								afterMap[ny][nx].speed += sSpeed;
								afterMap[ny][nx].cnt++;
								if (afterMap[ny][nx].dir == 0 || afterMap[ny][nx].dir == 2 || afterMap[ny][nx].dir == 4 || afterMap[ny][nx].dir == 6)
									afterMap[ny][nx].jjack = true;
								else {
									afterMap[ny][nx].jjack = false;
									afterMap[ny][nx].hol = false;
								}
							} else {
								afterMap[ny][nx] = new Fireball(sMass, sSpeed, k);
							}
						}
					} else {
						for (int k = 1; k <= 7; k += 2) {
							int ny = i + dy[k] * sSpeed;
							int nx = j + dx[k] * sSpeed;
							if (ny < 0)
								ny = N + ny;
							else
								ny = ny % N;
							if (nx < 0)
								nx = N + nx;
							else
								nx = nx % N;
							
							if (afterMap[ny][nx] != null) {
								afterMap[ny][nx].big = true;
								afterMap[ny][nx].mass += sMass;
								afterMap[ny][nx].speed += sSpeed;
								afterMap[ny][nx].cnt++;
								if (afterMap[ny][nx].dir == 1 || afterMap[ny][nx].dir == 3 || afterMap[ny][nx].dir == 5 || afterMap[ny][nx].dir == 7)
									afterMap[ny][nx].hol = true;
								else {
									afterMap[ny][nx].jjack = false;
									afterMap[ny][nx].hol = false;
								}
							} else {
								afterMap[ny][nx] = new Fireball(sMass, sSpeed, k);
							}
						}
					}
				}
			}
		}
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++) {
				map[i][j] = null;
				map[i][j] = afterMap[i][j];
			}
	}
}
