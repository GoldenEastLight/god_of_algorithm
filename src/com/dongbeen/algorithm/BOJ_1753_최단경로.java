package com.dongbeen.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {
	static int V;
	static int E;
	static int K;
	static List<Node>[] list;
	static int[] dist;

	static class Node {
		int end;
		int weight;

		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		list = new ArrayList[V + 1];
		dist = new int[V + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);

		for (int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			list[Integer.parseInt(st.nextToken())]
					.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] check = new boolean[V + 1];
		queue.offer(new Node(start, 0));
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int cur = curNode.end;

			if (check[cur] == true)
				continue;
			
		}
	}
}
