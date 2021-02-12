package com.dongbeen.algorithm.BOJ;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_11279_최대힙 {
	int N;
	
	public static void main(String[] args) {
		BOJ_11279_최대힙 m = new BOJ_11279_최대힙();
		m.service();
	}
	
	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
			return o1 < o2 ? 1 : -1;
		});
		
		for(int n = 0; n < N; n++) {
			int temp = sc.nextInt();
			if(temp == 0)
				if (queue.isEmpty())
					System.out.println("0");
				else
					System.out.println(queue.poll());
			else
				queue.offer(temp);
		}
		
		sc.close();
	}
}
