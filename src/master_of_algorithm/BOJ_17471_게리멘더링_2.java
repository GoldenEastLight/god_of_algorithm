package master_of_algorithm;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17471_게리멘더링_2 {
	static int N;
	static int[] peoples;
	static int[][] maps;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		peoples = new int[N+1];
		int[] teams = new int[N+1];
//		각 구역별 인원 입력받기
		for(int i = 1; i <= N; i++) {
			peoples[i] = sc.nextInt();			
		}		
//		구역별 연결된 정보 입력 받기
		maps = new int[N+1][N+1];
		int cnt = 0;
		for(int i = 1;i <= N; i++) {
			cnt = sc.nextInt();
			int idx;
			for(int c = 0; c < cnt; c++) {
				 idx = sc.nextInt();
				 maps[i][idx] = 1;
			}
		}
		sc.close();
		solve(teams, 1);
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
	}
	static void solve(int[] teams, int cnt) {
//		모든 지역 배치해보기
		if(cnt == N + 1) {
//			두 구역으로 정확하게 분리되었나 확인하기
//			System.out.println(Arrays.toString(teams));
			if(checkbfs(teams,0) && checkbfs(teams,1)) {
				result = Math.min(result, doCount(teams));
			}
			return;
		}
		teams[cnt] = 0;
		solve(teams, cnt + 1);
		teams[cnt] = 1;
		solve(teams, cnt + 1);
		
	}	
	static boolean checkbfs(int[] teams, int type) {
		boolean[] v = new boolean[N+1];
		Queue<Integer> q = new LinkedList<Integer>();
		
//		시작하는 정점을 찾아서 큐에 삽입한다.
		for(int i = 1; i <= N; i++) {
			if(teams[i] == type) {
				q.offer(i);
				v[i] = true;
				break;
			}
		}
//		두 구역으로 분리될 수 없으면 바로 반환
		if(q.isEmpty()) {
			return false;
		}
//		그 정점으로 모두 연결되어 있는지 BFS 검색
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int i = 1; i <= N; i++) {
//				이미 방문한 정점 무시
				if(v[i]) {
					continue;
				}
//				같은 구역이 아니면 무시
				if(teams[i] != type) {
					continue;
				}
//				연결되어 있지 않으면 무시
				if(maps[cur][i] == 0) {
					continue;
				}
//				그렇지 않으면 큐에 삽입하고 방문 체크를 한다.
				q.offer(i);
				v[i] = true;
			}
		}
		System.out.println(Arrays.toString(v));
		System.out.println(Arrays.toString(teams));
//		모든 정점을 방문해 보면서 다른 구역은 무시하고 같은 구역이면 방문했는지 체크해서  
//		방문 체크되어 있지 않으면 연결되어 있지 않음으로 바로 false 값을 반환
		for(int i = 1; i <= N; i++) {
			if(teams[i] != type) {
				continue;
			}
			if( !v[i] ) {
				return false;
			}
		}
//		최종까지 오면 모든 구역이 하나로 연결되어 있음
		return true;
	}
	static int doCount(int[] teams) {
//		두 구역별 합계를 구하고 그 차이값을 반환
		int sum1 = 0;
		int sum2 = 0;
		for(int i = 1; i <= N; i++) {
			if(teams[i] == 0) {
				sum1 += peoples[i];
			}else {
				sum2 += peoples[i];
			}
		}
		return Math.abs(sum1 - sum2);
	}
}
