/*
7 8 1
0 0 0 0 0 0 0 9
0 0 0 0 3 0 0 8
-1 0 5 0 0 0 22 0
-1 8 0 0 0 0 0 0
0 0 0 0 0 10 43 0
0 0 5 0 15 0 0 0
0 0 40 0 0 0 20 0

188
 */
package master_of_algorithm;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17144_미세먼지안녕_2 {

	// 위, 아래, 오른쪽, 왼쪽
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[] ccw = { 2, 0, 3, 1 };
	static int[] cw = { 2, 1, 3, 0 };
	static int[][] map, copyMap;
	static int R, C, T, sum;

	static ArrayList<Point> cleaner = new ArrayList<Point>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		map = new int[R][C];
		copyMap = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1) {
					cleaner.add(new Point(i, j));
				}
			}
		}

		for (int i = 0; i < T; i++) {
			spread();
			for (int ii = 0; ii < R; ii++) {
				for (int j = 0; j < C; j++) {
					copyMap[ii][j] = map[ii][j];
				}
			}
			solve(cleaner.get(0).x, cleaner.get(0).y, ccw);
			solve(cleaner.get(1).x, cleaner.get(1).y, cw);
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					sum += map[i][j];
				}
			}
		}
		System.out.println(sum);
	}

	static void spread() {
		Queue<Dust> q = new LinkedList<Dust>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 4) {
					q.add(new Dust(i, j, map[i][j]));
				}
			}
		}

		while (!q.isEmpty()) {
			Dust dust = q.poll();
			int give = dust.n / 5;
			int cnt = 0;

			for (int k = 0; k < 4; k++) {
				int nx = dust.c + dx[k];
				int ny = dust.r + dy[k];

				if (0 <= nx && nx < C && 0 <= ny && ny < R) {
					if (map[ny][nx] != -1) {// 현재 위치에 공기청정기가 없을 경우
						cnt++;
						map[ny][nx] += give;
					}
				}
			}
			map[dust.r][dust.c] -= give * cnt;
		}
	};

	static void solve(int cX, int cY, int[] dir) {
		int x = cX;
		int y = cY + 1;
		// 로봇 청소기 바람 첫번째 칸은 미세먼지 0
		map[x][y] = 0;
		for (int k = 0; k < 4; k++) {
			while (true) {
				int nx = x + dx[dir[k]];
				int ny = y + dy[dir[k]];

				if (!(0 <= nx && nx < R && 0 <= ny && ny < C)) {
					break;
				}

				if (cY == ny && cX == nx) {
					break;
				}

				map[nx][ny] = copyMap[x][y];
				x = nx;
				y = ny;
			}
		}
	};

	static class Dust {
		int r;
		int c;
		int n;

		Dust(int r, int c, int n) {
			this.r = r;
			this.c = c;
			this.n = n;
		}
	}

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
