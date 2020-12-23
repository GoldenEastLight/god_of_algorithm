package com.dongbeen.algorithm;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2309_일곱난쟁이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] nanJang = new int[9];
		int sum = 0;

		for (int i = 0; i < 9; i++) {
			nanJang[i] = sc.nextInt();
			sum += nanJang[i];
		}

		outer: for (int i = 0; i < nanJang.length; i++) {
			for (int j = 0; j < nanJang.length; j++) {
				if (sum - (nanJang[i] + nanJang[j]) == 100 && nanJang[i] != nanJang[j]) {
					nanJang[i] = Integer.MAX_VALUE;
					nanJang[j] = Integer.MAX_VALUE;
					break outer;
				}
			}
		}

		Arrays.sort(nanJang);

		for (int i = 0; i < 7; i++) {
			System.out.println(nanJang[i]);
		}
	}
}
