package com.dongbeen.algorithm.BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2164_카드2 {
	int N;
	int last;

	public static void main(String[] args) {
		BOJ_2164_카드2 m = new BOJ_2164_카드2();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++)
			queue.offer(i);
		
		while(queue.size() > 1) {
			queue.poll();
			last = queue.poll();
			queue.offer(last);
		}
		
		if(last == 0) {
			last = 1;
		}
		System.out.print(last);
		
		sc.close();
	}
}
