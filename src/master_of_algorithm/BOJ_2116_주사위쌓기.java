/**
5
2 3 1 6 5 4
3 1 2 4 6 5
5 6 4 1 3 2
1 3 6 2 4 5
4 1 6 5 2 3 

29
 */

package master_of_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2116_주사위쌓기 {
	static int N;
	static int[][] dices;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dices = new int[N][6];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int sum = 0;
		int maxSum = 0;
		int maxFloor = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (j+1 != dices[0][i] && j+1 != top(0, dices[0][i]))
					if (maxFloor < j+1)
						maxFloor = j+1;
			}
			sum = makeTop(1, top(0, dices[0][i]), maxFloor);
			if (sum > maxSum)
				maxSum = sum;
			maxFloor = 0;
		}

		System.out.println(maxSum);
	}

	static int makeTop(int level, int bottom, int sum) {
		int maxFloor = 0;


		for (int i = 0; i < 6; i++) {
			if (dices[level][i] != bottom && dices[level][i] != top(level, bottom))
				if (maxFloor < dices[level][i])
					maxFloor = dices[level][i];
		}
		sum += maxFloor;

		if (level + 1 == N)
			return sum;

		return makeTop(level + 1, top(level, bottom), sum);
	}

	static int top(int level, int bottom) {
		int top = 0;
		for (int i = 0; i < 6; i++) {
			if (dices[level][i] == bottom)
				top = dices[level][bottom(i)];
		}
		return top;
	}

	static int bottom(int idx) { // A / BCDE / F로 전개돼있음
		switch (idx) {
		case 0:
			return 5;
		case 1:
			return 3;
		case 2:
			return 4;
		case 3:
			return 1;
		case 4:
			return 2;
		}
		return 0; // 5(F)일땐 밖으로 나옴
	}
}
