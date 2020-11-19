package master_of_algorithm;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17472_MakeBridge {

	private int N, M, ans;
	private Queue<Bridge> pQue = new PriorityQueue<Bridge>();
	private int[][] arr;
	private int[] dx = { 0, 1, 0, -1 };
	private int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		new BOJ_17472_MakeBridge().service();
	}

	private void service() {
		Scanner scn = new Scanner(System.in);

		N = scn.nextInt();
		M = scn.nextInt();
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = scn.nextInt();
			}
		}

		// 1. 각 섬 만들기
		int islandCount = 0;
		int val = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					dfs(i, j, val);
					val++;
					islandCount++;
				}
			}
		}
		// 2. 연결점 찾기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0) {
					getBridge(i, j);
				}
			}
		}
		// 3. union find 만들기
		DisjointSetForBridge dSet = new DisjointSetForBridge();
		// 4. 크루스칼
		int count = 0;
		while(pQue.size() != 0) {
			if(count == islandCount-1) break;
			Bridge bridge = pQue.poll();
			if(dSet.union(bridge.a,bridge.b)) {
				ans += bridge.len;
				count++;
			}
		}
		if(count != islandCount-1) ans = -1;
		System.out.println(ans);
		scn.close();
	}
	private void dfs(int x, int y, int val) {
		arr[x][y] = val;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (arr[nx][ny] != 1)
				continue;
			dfs(nx, ny, val);
		}
	}

	private void getBridge(int x, int y) {
		for (int i = 0; i < 4; i++) {
			connectBridge(x, y, i);
		}
	}

	private boolean connectBridge(int x, int y, int rot) {
		int count = 0;
		int cx = x;
		int cy = y;
		while (true) {
			int nx = cx + dx[rot];
			int ny = cy + dy[rot];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				return false;
			if (arr[nx][ny] == arr[x][y])
				return false;
			if (arr[nx][ny] != 0) {
				if (count >= 2) {
					Bridge bridge = new Bridge(arr[x][y], arr[nx][ny], count);
					pQue.offer(bridge);
					return true;
				} else {
					return false;
				}
			}
			cx = nx;
			cy = ny;
			count++;
		}
	}
}

class Bridge implements Comparable<Bridge>{
	public int a, b;
	public int len;

	public Bridge(int a, int b, int len) {
		this.a = a;
		this.b = b;
		this.len = len;
	}

	@Override
	public int compareTo(Bridge o) {
		return this.len - o.len;
	}
}

class DisjointSetForBridge {
	public int[] parent;
	public int[] rank;
	
	public DisjointSetForBridge() {
		parent = new int[20];
		for(int i = 0; i < 20; i++) {
			parent[i] = i;
		}
		rank = new int[20];
		for(int i = 0; i < 20; i++) {
			rank[i] = 1;
		}
	}
	
	public int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	public boolean union(int a, int b) {
		a = find(a); b = find(b);
		if(a == b) return false;
		if(rank[a] > rank[b]) {
			parent[b] = a;
		} else if(rank[a] < rank[b]) {
			parent[a] = b;
		} else {
			parent[b] = a;
			rank[a]++;
		}
		return true;
	}
}