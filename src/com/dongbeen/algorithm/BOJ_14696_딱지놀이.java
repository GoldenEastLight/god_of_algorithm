package com.dongbeen.algorithm;

import java.util.Scanner;

// 별4 > 원3 > 네모2 > 세모1, 무승부 D
public class BOJ_14696_딱지놀이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		outer: for (int n = 0; n < N; n++) {
			int[] aDdack = new int[5];
			int[] bDdack = new int[5];
			int A = sc.nextInt();
			for (int i = 0; i < A; i++) {
				++aDdack[sc.nextInt()];
			}
			int B = sc.nextInt();
			for (int i = 0; i < B; i++) {
				++bDdack[sc.nextInt()];
			}

			for (int i = 4; i >= 1; i--) {
				if (aDdack[i] > bDdack[i]) {
					System.out.println("A");
					continue outer;
				} else if (aDdack[i] < bDdack[i]) {
					System.out.println("B");
					continue outer;
				}
			}
			System.out.println("D");
		}
	}
}
