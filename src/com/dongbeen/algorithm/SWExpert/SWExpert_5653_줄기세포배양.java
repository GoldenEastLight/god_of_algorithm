package com.dongbeen.algorithm.SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 시뮬레이션은 메서드로 빼놓는게 좋다. -> 부분부분 디버깅하기에 좋다.
 * 
 * 몇 시간 후.. 인게 많다.
 * 
 * 줄기세포배양 - 줄기세포상태 :: 비활성 x -> 활성 x (첫 활성상태일 때엔 사방에 동시에 번식한다.) -> 죽음
 * 
 * 번식을 동시에 같은곳에 하게 되면 생명력이 높은 줄기세포가 번식한다. -> PriorityQueue사용
 * 
 * 배양 용기의 크기는 무한하다. -> 가장 빠르게 번식하는게 생명력 1이니까 [N + K][M + K]크기로 주면 된다.더 넉넉하게 줘도 됨
 * 
 * 위치, 생명력, 상태가 필요하다.
 * 
 * -x 시간을 주고, 시간 == 0되면 활성상태가 된다. 시간 == x가 되면 죽음 (0주고 시간 == x활성, 시간 == 2x 죽음으로
 * 해도 된다.)
 * 
 * K시간 후 살아있는 줄기세포 수 출력.
 */

public class SWExpert_5653_줄기세포배양 {
	int N, M, K;
	int[][] map;
	boolean[][] visited;
	PriorityQueue<Cell> pQueue;
	final int INACTIVE = 0, ACTIVE = 1, DEAD = 2;
	int[] dr = { -1, 1, 0, 0 };
	int[] dc = { 0, 0, -1, 1 };

	class Cell implements Comparable<Cell> {
		// 행, 열, 생명력, 흐른 시간(음수부터 출발), 상태
		int r, c, x, time, status;

		Cell(int r, int c, int x) {
			this.r = r;
			this.c = c;
			this.x = x;
			this.time = -x;
			// 상태는 기본값이 0으로 비활성
		}

		@Override
		public String toString() {
			return "Cell [r=" + r + ", c=" + c + ", x=" + x + ", time=" + time + ", status=" + status + "]";
		}

		@Override
		public int compareTo(Cell o) {
			return Integer.compare(o.x, this.x); // 비교값이 음수, 약수가 섞여있을 때 일어날 수 있는 오버플로우, 언더플로우를 방지한다.
		}

		public void flowTime() {
			time++;
			if (time == 0 || time == x)
				status++; // 흐른시간이 0 : 활성상태가 되어야함. 흐른시간이 x : 죽음상태가 되어야함.
		}
	}

	public static void main(String[] args) throws Exception {
		new SWExpert_5653_줄기세포배양().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N + K + 2][M + K + 2];
			visited = new boolean[N + K + 2][M + K + 2];
			pQueue = new PriorityQueue<Cell>();

			// /2를 했을 때 홀수를 나누면 1이 날아가서 혹시 문제될 수 있으므로 +2를 더해준다.
			int a = (N + K + 2) / 2; // 행의 중간위치
			int b = (M + K + 2) / 2; // 열의 중간위치
			// i, j를 a - N / 2부터 시작하는걸 잘 생각하자.
			for (int i = a - N / 2, iCnt = 0; iCnt < N; i++, iCnt++) {
				st = new StringTokenizer(br.readLine());
				for (int j = b - M / 2, jCnt = 0; jCnt < M; j++, jCnt++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0) { // 생명력이 있다면 줄기세포임
						visited[i][j] = true; // 이미 번식 되었다고 처리
						pQueue.offer(new Cell(i, j, map[i][j])); // PQ에 넣기
					}
				}
			} // 배양 가능한 크기의 배열을 이용하여 중앙에 N * M 초기 배양 상태를 저장

			System.out.println("#" + t + " " + process());
		}
	}

	private int process() {
		int time = K;
		int nr, nc;
		Cell cell;
		ArrayList<Cell> list = new ArrayList<>(); // 다음 반복시 처리 해야 하는 줄기세포 저장할 임시 list

		while (time-- > 0) { // K시간 만큼 진행
			// 매 시간 마다 모든 줄기세포에 대해 시간의 흐름에 따른 처리
			while (!pQueue.isEmpty()) {
				cell = pQueue.poll();

				// 줄기세포의 상태가 활성상태이며 활성화된 첫 시간에만 번식
				if (cell.status == ACTIVE && cell.time == 0) {
					for (int d = 0; d < 4; d++) { // 4방으로 자신과 같은 생명력을 갖는 줄기세포 번식
						nr = cell.r + dr[d];
						nc = cell.c + dc[d];
						if (visited[nr][nc]) // 다른 줄기세포가 이미 번식시킨 자리라면 번식 불가(건너 뜀)
							continue;
						visited[nr][nc] = true; // 무조건 번식 시킴(뒤에 오는 줄기세포는 생명력이 같거나 작다.)
						list.add(new Cell(nr, nc, cell.x)); // 새로운 줄기세포는 자신을 번식시키는 모줄기세포와 같은 생명력으로 생성
					}
				}

				// 모든 세포에 흐른 시간 처리, 세포 자신의 상태는 자신이 처리하는게 객체지향적이니 Cell내부에 넣어준 메서드로 처리.
				cell.flowTime();

				if (cell.status == DEAD) // 죽은애들 빼고 list에 다시 넣는다.
					continue;
				list.add(cell);
			} // 매 시간 처리
			pQueue.addAll(list); // 죽지 않고 살아있는 줄기세포들을 pQueue에 다음 처리 위해 넣기.
			list.clear(); // 다음 처리를 위해 리스트를 비운다.
		}
		return pQueue.size();
	}
}
