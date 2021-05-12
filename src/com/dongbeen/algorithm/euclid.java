package com.dongbeen.algorithm;

public class euclid {
	public static void main(String[] args) {
		int num1 = 74;
		int num2 = 12;
		int max = Math.max(num1, num2);
		int min = Math.min(num1, num2);

		// 최대공약수
		System.out.println(gcd(num1, num2));

	}

	private static int gcd(int max, int min) {

		while (min != 0) {
			int remain = max % min;
			max = min;
			min = remain;
		}
		return max;
	}
}
