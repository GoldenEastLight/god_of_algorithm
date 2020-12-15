package master_of_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트링크 {
	static int N;
	static int[][] persons;
	static int[] selected;
	static int minSynergy = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		persons = new int[N][N];
		selected = new int[N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				persons[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		makeTeam(0, 0);

		System.out.println(minSynergy);
	}

	public static void makeTeam(int cnt, int cur) {
		if (cnt == N / 2) {
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (selected[i] == selected[j]) {
						if (selected[j] == 1)
							sum1 += persons[i][j] + persons[j][i];
						else
							sum2 += persons[i][j] + persons[j][i];
					}
				}
			}
			minSynergy = Math.min(minSynergy, Math.abs(sum1 - sum2));
			return;
		}

		for (int i = cur; i < N; i++) {
			selected[i] = 1;
			makeTeam(cnt + 1, i + 1);
			selected[i] = 0;
		}
	}
}
