package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_1764_듣보잡 {
	public static void main(String[] args) throws Exception {
		new BOJ_1764_듣보잡().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<String> arr = new HashSet<>();
		ArrayList<String> ddudbo = new ArrayList<>();

		for (int i = 0; i < N; i++)
			arr.add(br.readLine());

		for (int i = 0; i < M; i++) {
			String people = br.readLine();
			if (arr.contains(people))
				ddudbo.add(people);
		}

		Collections.sort(ddudbo);
		int size = ddudbo.size();
		sb.append(size + "\n");
		for (int i = 0; i < size; i++)
			sb.append(ddudbo.get(i).toString() + "\n");
		System.out.print(sb);
	}
}
