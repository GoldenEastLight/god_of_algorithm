package com.dongbeen.algorithm.BOJ;
import java.util.Scanner;

public class BOJ_2605_줄세우기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] students = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			students[i] = i;
		}

		int num = 0;
		for (int i = 1; i <= N; i++) {
			num = sc.nextInt();
			if (num != 0) {
				for (int j = i; j >= i - num; j--) {
					students[j] = students[j - 1];
				}
				students[i - num] = i;
//				int temp = students[i];
//				students[i] = students[i - num];
//				students[i - num] = temp;
			}
		}

		for (int i = 1; i <= N; i++) {
			System.out.print(students[i] + " ");
		}
	}
}
