package com.dongbeen.algorithm;

public class BOJ_4673_셀프넘버 {
	boolean[] selfNumbers;

	public static void main(String[] args) {
		BOJ_4673_셀프넘버 m = new BOJ_4673_셀프넘버();

		m.service();
	}

	private void service() {
		selfNumbers = new boolean[10001];

		for (int i = 1; i <= 10000; i++) {
			int n = selfNumber(i);

			if (n <= 10000)
				selfNumbers[n] = true;
		}

		for (int i = 1; i <= 10000; i++) {
			if (!selfNumbers[i])
				System.out.println(i);
		}
	}

	private int selfNumber(int num) {
		int sum = num;

		while (num != 0) {
			sum = sum + (num % 10);
			num = num / 10;
		}

		return sum;
	}
}
