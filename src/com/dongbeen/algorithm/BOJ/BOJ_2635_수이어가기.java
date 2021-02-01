package com.dongbeen.algorithm.BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2635_수이어가기 {
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		sc.close();
		int maxCnt = 0;
		ArrayList<Integer> maxArr = null;

		for (int i = N; i > N / 2; i--) {
			ArrayList<Integer> arr = new ArrayList<>();
			int minus = i;
			int num = N;

			while (true) {
				arr.add(num);
				int temp = minus;
				minus = num - minus;
				if (minus < 0) {
					arr.add(temp);
					break;
				}
				num = temp;
			}
			if (arr.size() > maxCnt) {
				maxCnt = arr.size();
				maxArr = arr;
			}
		}
		System.out.println(maxCnt);
		for (int i = 0; i < maxArr.size(); i++)
			System.out.print(maxArr.get(i) + " ");
	}
}
