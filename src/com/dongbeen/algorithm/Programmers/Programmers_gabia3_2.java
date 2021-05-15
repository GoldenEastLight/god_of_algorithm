package com.dongbeen.algorithm.Programmers;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Programmers_gabia3_2 {

	class CoffeeOrder implements Comparable<CoffeeOrder> {
		int time;
		int orderNum;

		public CoffeeOrder(int time, int orderNum) {
			this.time = time;
			this.orderNum = orderNum;
		}

		@Override
		public int compareTo(CoffeeOrder target) {
			if (this.time > target.time) {
				return 1;
			} else if (this.time < target.time) {
				return -1;
			}
			return this.orderNum >= target.orderNum ? 1 : -1;
		}
	}

	public int[] solution(int N, int[] coffee_times) {
		int[] answers = new int[coffee_times.length];
		int ans = 0;
		Queue<CoffeeOrder> order = new LinkedList<>(); // 주문 받은 순서대로 저장할 Queue
		PriorityQueue<CoffeeOrder> making = new PriorityQueue<>(); // 커피 추출하는 시간동안 저장해둘 making PriorityQueue

		for (int i = 0; i < coffee_times.length; i++) {
			order.offer(new CoffeeOrder(coffee_times[i], i + 1));
		}

		while (!order.isEmpty()) {
			while (making.size() < N && !order.isEmpty()) {
				making.offer(order.poll());
			}

			Queue<CoffeeOrder> tempQueue = new LinkedList<>();
			int makingSize = making.size();
			int tempTime = making.peek().time;
			for (int i = 0; i < makingSize; i++) {
				CoffeeOrder temp = making.poll();
				temp.time -= tempTime;
				if (temp.time <= 0) {
					answers[ans++] = temp.orderNum;
					continue;
				}
				tempQueue.offer(temp);
			}

			int tempQueueSize = tempQueue.size();
			for (int i = 0; i < tempQueueSize; i++) {
				making.offer(tempQueue.poll());
				if (order.isEmpty()) {
					answers[ans++] = making.poll().orderNum;
				}
			}
		}

		return answers;
	}
}
