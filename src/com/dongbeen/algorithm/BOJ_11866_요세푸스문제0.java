package com.dongbeen.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_11866_요세푸스문제0 {
	int N;
	int K;
	
	public static void main(String[] args) {
		BOJ_11866_요세푸스문제0 m = new BOJ_11866_요세푸스문제0();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		sc.close();
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		System.out.print("<");
		int temp = 0;
		while(queue.size() > 1) {
			for(int i = 0; i < K - 1; i++) {
				temp = queue.poll();
				queue.offer(temp);
			}
			temp = queue.poll();
			System.out.print(temp + ", ");
		}
		temp = queue.poll();
		System.out.print(temp + ">");
	}
}
