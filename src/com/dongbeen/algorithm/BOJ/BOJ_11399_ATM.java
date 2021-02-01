package com.dongbeen.algorithm.BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11399_ATM {
	int N;
	int[] people;
	int[] selected;

	public static void main(String[] args) {
		BOJ_11399_ATM m = new BOJ_11399_ATM();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		people = new int[N];
		selected = new int[N];

		for (int i = 0; i < N; i++) {
			people[i] = sc.nextInt();
		}

		System.out.println(atm());
	}

	private int atm() {
		int totalSum = 0;
		int prevSum = 0;

		Arrays.sort(people);

		for (int i = 0; i < N; i++) {
			totalSum += prevSum + people[i];
			prevSum += people[i];
		}

		return totalSum;
	}
}
