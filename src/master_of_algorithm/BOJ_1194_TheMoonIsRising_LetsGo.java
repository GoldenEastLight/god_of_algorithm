package master_of_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_TheMoonIsRising_LetsGo {
	int N, M, startY, startX;
	int[][] map;
	int[][][] visited;
	int minCnt = Integer.MAX_VALUE;
	int[] dy = { -1, 1, 0, 0 };
	int[] dx = { 0, 0, -1, 1 };

	public class Info {
		int y;
		int x;
		int searchCnt;
		int key;

		public Info(int y, int x, int searchCnt, int key) {
			this.y = y;
			this.x = x;
			this.searchCnt = searchCnt;
			this.key = key;
		}
	}

	public static void main(String[] args) throws Exception {
		BOJ_1194_TheMoonIsRising_LetsGo m = new BOJ_1194_TheMoonIsRising_LetsGo();
		m.service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new int[64][N][M];

		for (int r = 0; r < N; r++) {
			char[] line = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				map[r][c] = line[c];
				if (map[r][c] == '0') {
					startY = r;
					startX = c;
				}
			}
		}
		TheMoonIsRising();
	}

	private void TheMoonIsRising() {
		Queue<Info> queue = new LinkedList<>();
		queue.offer(new Info(startY, startX, 0, 0));
		visited[0][startY][startX] = 1;

		while (!queue.isEmpty()) {
			Info temp = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;
				if (visited[temp.key][ny][nx] > 0)
					continue;

				if (map[ny][nx] == '.' || map[ny][nx] == '0') { // 땅
					visited[temp.key][ny][nx] = temp.searchCnt + 1;
					queue.offer(new Info(ny, nx, temp.searchCnt + 1, temp.key));
				} else if (map[ny][nx] >= 'a' && map[ny][nx] <= 'f') { // 키
					int tempKey = (1 << (map[ny][nx] - 'a')) | temp.key;
					visited[tempKey][ny][nx] = temp.searchCnt + 1;
					queue.offer(new Info(ny, nx, temp.searchCnt + 1, tempKey));
				} else if (map[ny][nx] >= 'A' && map[ny][nx] <= 'F') { // 문
					int door = (1 << (map[ny][nx] - 'A')) & temp.key;
					if (door > 0) {
						visited[temp.key][ny][nx] = temp.searchCnt + 1;
						queue.offer(new Info(ny, nx, temp.searchCnt + 1, temp.key));
					}
				} else if (map[ny][nx] == '1') {
					System.out.println(++temp.searchCnt);
					return;
				}
			}
		}
		System.out.println(-1);
	}
}
