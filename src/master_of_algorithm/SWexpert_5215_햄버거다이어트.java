package master_of_algorithm;

import java.util.Scanner;

/**
 * 
 * 1 2 300 50 250 15 60
 * 
 * 
 * @author Administrator
 *
 */
public class SWexpert_5215_햄버거다이어트 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 재료 갯수
			int maxCal = sc.nextInt();
			int[][] data = new int[N][2]; // [(taste, cal), ...]
			for (int i = 0; i < data.length; i++) {
				data[i][0] = sc.nextInt();
				data[i][1] = sc.nextInt();
			}
			int idx = 0;
			int taste = 0;
			int max = find(data, idx, taste, maxCal);
			System.out.println("#" + tc + " " + max);
		}
	}

	static int find(int[][] R, int idx, int score, int maxCal) {
		if (maxCal < 0) {
			return 0; // score:=0
		}
		if (maxCal == 0) {
			return score;
		}
		if (idx == R.length) {
			return score;
		}
		// idx번째 재료를 안쓰는 경우
		int result1 = find(R, idx + 1, score, maxCal);
		int result2 = find(R, idx + 1, score + R[idx][0], maxCal - R[idx][1]);
		return Math.max(result1, result2);
	}
}
