package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ_1655_가운데를말해요 {
	int N;

	public static void main(String[] args) throws IOException {
		BOJ_1655_가운데를말해요 m = new BOJ_1655_가운데를말해요();
		m.service();
	}

	private void service() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> minQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);
		PriorityQueue<Integer> maxQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);

		for (int n = 0; n < N; n++) {
			int temp = Integer.parseInt(br.readLine());

			if (minQueue.size() == maxQueue.size())
				maxQueue.offer(temp);
			else
				minQueue.offer(temp);

			if (!minQueue.isEmpty() && !maxQueue.isEmpty())
				if (minQueue.peek() < maxQueue.peek()) {
					int swap = minQueue.poll();
					minQueue.offer(maxQueue.poll());
					maxQueue.offer(swap);
				}
			bw.write(maxQueue.peek().toString());
			bw.newLine();
		}
		bw.close();
	}
}
