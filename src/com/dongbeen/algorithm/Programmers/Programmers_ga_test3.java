package com.dongbeen.algorithm.Programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Programmers_ga_test3 {

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

	public Integer[] solution(int N, int[] coffee_times) {
		ArrayList<Integer> completeOrders = new ArrayList<>(); // 완료된 주문 순서대로 저장할 ArrayList
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
					completeOrders.add(temp.orderNum);
					continue;
				}
				tempQueue.offer(temp);
			}

			int tempQueueSize = tempQueue.size();
			for (int i = 0; i < tempQueueSize; i++) {
				making.offer(tempQueue.poll());
				if (order.isEmpty()) {
					completeOrders.add(making.poll().orderNum);
				}
			}
		}

		Integer[] answer;
		answer = completeOrders.toArray(new Integer[completeOrders.size()]);

		return answer;
	}
}
