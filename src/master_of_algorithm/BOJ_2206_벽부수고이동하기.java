/**

입력


6 4
0010
1110
1000
0000
0111
0000


6 4
0100
1110
1000
0000
0111
0000

출력
15

입력
4 4
0111
1111
1111
1110

출력
-1
 */

package master_of_algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2206_벽부수고이동하기 {
	static boolean[][][] v; // 벽이 부서진 상태일때, 아닐 때 따로 확인
	static int[][] arr;
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 가로
		int m = sc.nextInt(); // 세로
		arr = new int[n][m];
		v = new boolean[n][m][2];

		for (int i = 0; i < n; i++) {
			char[] temp = sc.next().toCharArray();
			for (int j = 0; j < temp.length; j++) {
				arr[i][j] = temp[j] - '0';
			}
		}
		bfs(n, m);
	}

	public static void bfs(int n, int m) {
		Queue<Data> q = new LinkedList<>();

		q.offer(new Data(0, 0, 1, 0));
		v[0][0][0] = true;
		v[0][0][1] = true;

		while (!q.isEmpty()) {
			Data p = q.poll();
			if (p.x == n - 1 && p.y == m - 1) {
				System.out.println(p.cnt);
				return;
			}

			// 상하좌우 확인
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				// 범위 내에 있을때
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					// 벽일 경우
					if (arr[nx][ny] == 1) {
						// 벽을 부수지 않은 상태일 경우 부순 후 방문
						if (p.wall == 0 && !v[nx][ny][1]) {
							q.offer(new Data(nx, ny, p.cnt + 1, 1));
							v[nx][ny][1] = true;
						}
					} else {
						if (!v[nx][ny][p.wall]) {
							q.offer(new Data(nx, ny, p.cnt + 1, p.wall));
							v[nx][ny][p.wall] = true;
						}
					}
				}
			}
		}
		System.out.println(-1);
	}

	public static class Data {
		int x; // 위치
		int y;
		int cnt; // 이동 횟수
		int wall; // 벽 부순 여부

		Data(int x, int y, int count, int wall) {
			this.x = x;
			this.y = y;
			this.cnt = count;
			this.wall = wall;
		}
	}
}