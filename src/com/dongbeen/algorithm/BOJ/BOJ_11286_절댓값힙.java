package com.dongbeen.algorithm.BOJ;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 절댓값 힙은 다음과 같은 연산을 지원하는 자료구조이다.
 * 
 * 1. 배열에 정수 x (x ≠ 0)를 넣는다. 2.배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다. 절댓값이 가장
 * 작은 값이 여러개일 때는, 가장 작은 수를 출력하고, 그 값을 배열에서 제거한다.
 * 
 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
 */

public class BOJ_11286_절댓값힙 {
	int N;

	public static void main(String[] args) {
		BOJ_11286_절댓값힙 m = new BOJ_11286_절댓값힙();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
			int x = Math.abs(o1);
			int y = Math.abs(o2);

			if (x == y)
				return o1 > o2 ? 1 : -1;
			return x - y;
		});

		for (int n = 0; n < N; n++) {
			int temp = sc.nextInt();
			if (temp == 0) {
				if(queue.isEmpty())
					System.out.println("0");
				else
					System.out.println(queue.poll());
			} else
				queue.offer(temp);

		}
	}
}
