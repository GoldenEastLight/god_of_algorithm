package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_5525_IOIOI {
	public static void main(String[] args) {
		new BOJ_5525_IOIOI().service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int M = Integer.parseInt(sc.nextLine());
		String s = sc.nextLine();
		char[] ch = s.toCharArray();
		int cnt = 0;
		int result = 0;

		for (int i = 1; i < M - 1; i++) {
			if (ch[i] == 'O' && ch[i - 1] == 'I' && ch[i + 1] == 'I') {
				cnt++;
				i++;
				if (cnt == N) {
					cnt--;
					result++;
				}
			} else
				cnt = 0;
		}
		System.out.println(result);
		sc.close();
	}
}
