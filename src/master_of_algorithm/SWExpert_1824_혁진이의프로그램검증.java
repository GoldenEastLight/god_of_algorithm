package master_of_algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWExpert_1824_혁진이의프로그램검증 {
	int[] dr = { -1, 1, 0, 0 };
	int[] dc = { 0, 0, -1, 1 };

	class Status {
		int r, c, mem, dir;

		Status(int r, int c, int mem, int dir) {
			this.r = r;
			this.c = c;
			this.mem = mem;
			this.dir = dir;
		}
	}

	public static void main(String[] args) {
		SWExpert_1824_혁진이의프로그램검증 m = new SWExpert_1824_혁진이의프로그램검증();
		m.service();
	}

	public void service() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int R = sc.nextInt(); // 2 ≤ R, C ≤ 20
			int C = sc.nextInt(); // 2 ≤ R, C ≤ 20
			char[][] map = new char[R][C];
			for (int i = 0; i < R; i++) {
				String line = sc.next();
				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			boolean[][][][] visited = new boolean[R][C][16][4];
			Queue<Status> queue = new LinkedList<>();
			queue.add(new Status(0, 0, 0, 3));
			boolean ans = false;
			loop: while (!queue.isEmpty()) {
				Status s = queue.poll();
				if (visited[s.r][s.c][s.mem][s.dir])
					continue;
				visited[s.r][s.c][s.mem][s.dir] = true;
				switch (map[s.r][s.c]) {
				case '@':
					ans = true;
					break loop;
				case '^':
					s.dir = 0;
					break;
				case 'v':
					s.dir = 1;
					break;
				case '<':
					s.dir = 2;
					break;
				case '>':
					s.dir = 3;
					break;
				case '_':
					s.dir = (s.mem == 0 ? 3 : 2);
					break;
				case '|':
					s.dir = (s.mem == 0 ? 1 : 0);
					break;
				case '.':
					break;
				case '+':
					s.mem = (s.mem == 15 ? 0 : s.mem + 1);
					break;
				case '-':
					s.mem = (s.mem == 0 ? 15 : s.mem - 1);
					break;
				case '?':
					for (int d = 0; d < 4; d++) {
						int nr = s.r + dr[d];
						int nc = s.c + dc[d];
						nr = (nr == R ? 0 : nr);
						nr = (nr == -1 ? R - 1 : nr);
						nc = (nc == C ? 0 : nc);
						nc = (nc == -1 ? C - 1 : nc);
						queue.add(new Status(nr, nc, s.mem, d));
					}
					break;
				default:
					s.mem = map[s.r][s.c] - '0';
					break;
				}
				int nr = s.r + dr[s.dir];
				int nc = s.c + dc[s.dir];
				nr = (nr == R ? 0 : nr);
				nr = (nr == -1 ? R - 1 : nr);
				nc = (nc == C ? 0 : nc);
				nc = (nc == -1 ? C - 1 : nc);
				queue.add(new Status(nr, nc, s.mem, s.dir));
			}
			System.out.println("#" + tc + " " + (ans ? "YES" : "NO"));
		}
	}
}
