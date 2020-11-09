package master_of_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JungOl_2283_RGB마을 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] houses = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			houses[i][0] = Integer.parseInt(st.nextToken());
			houses[i][1] = Integer.parseInt(st.nextToken());
			houses[i][2] = Integer.parseInt(st.nextToken());
		}

		for (int i = 2; i <= N; i++) {
			houses[i][0] += Math.min(houses[i - 1][1], houses[i - 1][2]);
			houses[i][1] += Math.min(houses[i - 1][0], houses[i - 1][2]);
			houses[i][2] += Math.min(houses[i - 1][0], houses[i - 1][1]);
		}

		System.out.println(Math.min(Math.min(houses[N][0], houses[N][1]), houses[N][2]));
	}
}
