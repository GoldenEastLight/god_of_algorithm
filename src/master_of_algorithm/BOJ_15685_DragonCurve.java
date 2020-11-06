package master_of_algorithm;

import java.util.Scanner;

public class BOJ_15685_DragonCurve {
	int N;
	Info[] info;
	int[][] map = new int[100][100];

	public class Info {
		int x;
		int y;
		int d;
		int g;

		public Info(int x, int y, int d, int g) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}
	}

	public static void main(String[] args) {
		BOJ_15685_DragonCurve m = new BOJ_15685_DragonCurve();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		info = new Info[N];

		for (int n = 0; n < N; n++) {
			info[n].x = sc.nextInt();
			info[n].y = sc.nextInt();
			info[n].d = sc.nextInt();
			info[n].g = sc.nextInt();
		}
	}
}
