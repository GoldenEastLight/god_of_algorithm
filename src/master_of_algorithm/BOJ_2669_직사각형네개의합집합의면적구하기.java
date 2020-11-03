package master_of_algorithm;
import java.util.Scanner;

public class BOJ_2669_직사각형네개의합집합의면적구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[][] DoHwaJi = new int[100][100];
		int black = 0;

		for (int i = 0; i < 4; i++) {
			int yStart = sc.nextInt();
			int xStart = sc.nextInt();
			int yEnd = sc.nextInt();
			int xEnd = sc.nextInt();
			
			for (int r = yStart; r < yEnd; r++) {
				for (int c = xStart; c < xEnd; c++) {
					DoHwaJi[r][c] = 1;
				}
			}
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (DoHwaJi[i][j] == 1)
					++black;
			}
		}

		System.out.println(black);
	}
}
