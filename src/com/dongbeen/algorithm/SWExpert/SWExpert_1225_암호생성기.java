package com.dongbeen.algorithm.SWExpert;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWExpert_1225_암호생성기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int T = sc.nextInt();
			Queue<Integer> cryptography = new LinkedList<Integer>();

			for (int i = 0; i < 8; i++) {
				cryptography.offer(sc.nextInt());
			}

			outer: while (true) {
				for (int i = 1; i <= 5; i++) {
					int temp = cryptography.poll();
					temp -= i;
					if (temp <= 0) {
						temp = 0;
						cryptography.offer(temp);
						break outer;						
					}
					cryptography.offer(temp);
				}
			}

			System.out.print("#" + T + " ");
			for (int q : cryptography) {
				System.out.print(q + " ");
			}
			System.out.println();
		}
	}
}
