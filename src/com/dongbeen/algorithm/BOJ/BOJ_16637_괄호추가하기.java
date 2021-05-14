package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_16637_괄호추가하기 {
	int N;
	int max = Integer.MIN_VALUE;
	ArrayList<Integer> num = new ArrayList<>();
	ArrayList<Character> chars = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		new BOJ_16637_괄호추가하기().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String string = br.readLine();

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0)
				num.add(string.charAt(i) - '0');
			else
				chars.add(string.charAt(i));
		}
		makeBigNum(0, num.get(0));
		System.out.println(max);
	}

	private void makeBigNum(int idx, int sum) {
		if (idx >= chars.size()) {
			max = Math.max(max, sum);
			return;
		}

		int noParentheses = calculate(idx, sum, num.get(idx + 1));
		makeBigNum(idx + 1, noParentheses);

		if (idx + 1 < chars.size()) {
			int yesParentheses = calculate(idx + 1, num.get(idx + 1), num.get(idx + 2));
			int result = calculate(idx, sum, yesParentheses);
			makeBigNum(idx + 2, result);
		}
	}

	private int calculate(int charIdx, int a, int b) {
		switch (chars.get(charIdx)) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}
		return 0;
	}
}
