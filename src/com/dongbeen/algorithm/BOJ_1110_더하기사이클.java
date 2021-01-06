package com.dongbeen.algorithm;

import java.util.Scanner;

public class BOJ_1110_더하기사이클 {
	int N;

	public static void main(String[] args) {
		BOJ_1110_더하기사이클 m = new BOJ_1110_더하기사이클();

		m.service();
	}

	public void service() {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		int temp = N;
		int positionSum = 0;
		int sumCnt = 0;
		int positionOne;

		do {
			positionOne = temp % 10;
//			System.out.println(positionOne);
			positionSum = temp / 10 + temp % 10;
//			System.out.println(positionSum);
			temp = positionOne * 10 + positionSum % 10;
//			System.out.println(temp);
			sumCnt++;
		} while (temp != N);

		System.out.println(sumCnt);
	}
}
