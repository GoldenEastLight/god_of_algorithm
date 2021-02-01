package com.dongbeen.algorithm.SWExpert;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWExpert_3499_퍼팩트셔플 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int more = 0;

			Queue<String> deck = new LinkedList<>();
			Queue<String> deck1 = new LinkedList<>();
			Queue<String> deck2 = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				deck.offer(sc.next());
			}

			if (N % 2 > 0) {
				more = 1;
			}
			for (int i = 0; i < N / 2; i++) {
				deck1.offer(deck.poll());
			}
			if (more > 0)
				deck1.offer(deck.poll());
			for (int i = 0; i < N / 2; i++) {
				deck2.offer(deck.poll());
			}

			for (int i = 0; i < N / 2; i++) {
				deck.offer(deck1.poll());
				deck.offer(deck2.poll());
			}
			if (more > 0)
				deck.offer(deck1.poll());

			System.out.print("#" + t + " ");
			for (int i = 0; i < N; i++) {
				System.out.print(deck.poll() + " ");
			}
			System.out.println();

		}
	}
}
