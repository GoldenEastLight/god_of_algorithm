package com.dongbeen.algorithm.BOJ;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1927_최소힙 {
	int N;

	public static void main(String[] args) {
		BOJ_1927_최소힙 m = new BOJ_1927_최소힙();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		for(int n = 0; n < N; n++) {
			int temp = sc.nextInt();
			if(temp == 0)
				if(queue.isEmpty())
					System.out.println("0");
				else
					System.out.println(queue.poll());
			else
				queue.add(temp);
		}
		sc.close();
	}
}
