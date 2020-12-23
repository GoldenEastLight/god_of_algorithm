package com.dongbeen.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWExpert_8382_방향전환 {
	int[] dr = { -1, 1, 0, 0 };
	int[] dc = { 0, 0, -1, 1 };

	class Point {
		int r, c, cnt;
		boolean dir;

		Point(int r, int c, int cnt, boolean dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
	}

	public static void main(String[] args) {
		SWExpert_8382_방향전환 m = new SWExpert_8382_방향전환();
		m.service();
	}

	public void service() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int sR = sc.nextInt() + 100;
			int sC = sc.nextInt() + 100;
			int dR = sc.nextInt() + 100;
			int dC = sc.nextInt() + 100;
			boolean[][][] visited = new boolean[201][201][2];
			Queue<Point> queue = new LinkedList<>();
			visited[sR][sC][0] = true;
			queue.add(new Point(sR, sC, 0, true));
			visited[sR][sC][1] = true;
			queue.add(new Point(sR, sC, 0, false));
			int max = 0;
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				if (p.r == dR && p.c == dC) {
					max = p.cnt;
					break;
				}
				// 방향에 따라서
				if (p.dir) {
					// 상하
					for (int d = 0; d < 2; d++) {
						int nr = p.r + dr[d];
						int nc = p.c + dc[d];
						if (nr < 0 || nc < 0 || nr > 200 || nc > 200)
							continue;
						if (visited[nr][nc][0])
							continue;
						visited[nr][nc][0] = true;
						queue.add(new Point(nr, nc, p.cnt + 1, !p.dir));
					}
				} else {
					// 좌우
					for (int d = 2; d < 4; d++) {
						int nr = p.r + dr[d];
						int nc = p.c + dc[d];
						if (nr < 0 || nc < 0 || nr > 200 || nc > 200)
							continue;
						if (visited[nr][nc][1])
							continue;
						visited[nr][nc][1] = true;
						queue.add(new Point(nr, nc, p.cnt + 1, !p.dir));
					}
				}
			}
			System.out.println("#" + tc + " " + max);
		}
	}
}
