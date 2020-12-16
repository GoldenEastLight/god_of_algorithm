package master_of_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWExpert_4012_요리사 {
	static int N;
	static int[][] taste;
	static int minSynergy;
	static int[] selected;
	static List<Integer> food = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			taste = new int[N][N];
			selected = new int[N];
			minSynergy = Integer.MAX_VALUE;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					taste[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			cook(0);

			System.out.println("#" + t + " " + minSynergy);
		}
	}

	static void cook(int choice) {
		if (choice == 2) {
			int a = food.get(0);
			int b = food.get(1);
			int synergy = 0;
			food.remove(0);
			food.remove(1);
			
			synergy = taste[a][b] + taste[b][a];
		}
		
		for (int i = 0; i < N; i++) {
				if (selected[i] == 0) {
					selected[i] = 1;
					food.add(i);
					cook(i + 1);
					selected[i] = 0;
			}
		}
	}
}