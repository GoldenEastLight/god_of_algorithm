package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11723_집합 {
	public static void main(String[] args) throws Exception {
		new BOJ_11723_집합().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		boolean[] arr = new boolean[21];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String s = st.nextToken();
			int no;

			switch (s) {
			case "add":
				no = Integer.parseInt(st.nextToken());
				arr[no] = true;
				break;

			case "remove":
				no = Integer.parseInt(st.nextToken());
				arr[no] = false;
				break;

			case "check":
				no = Integer.parseInt(st.nextToken());
				if (arr[no])
					sb.append(1 + "\n");
				else
					sb.append(0 + "\n");
				break;

			case "toggle":
				no = Integer.parseInt(st.nextToken());
				if (arr[no])
					arr[no] = false;
				else
					arr[no] = true;
				break;

			case "all":
				for (int j = 0; j < arr.length; j++)
					arr[j] = true;
				break;

			case "empty":
				arr = new boolean[21];
				break;
			}
		}
		System.out.println(sb);
	}
}
