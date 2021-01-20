package com.dongbeen.algorithm;

import java.util.Scanner;
import java.util.Stack;

public class SWExpert_1223_계산기2 {
	private static Stack<Character> st = new Stack<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int N = sc.nextInt();
			char[] str = sc.next().toCharArray();

			String numbers = makePost(str, N);
			int resultCal = calculator(numbers, N);

			System.out.println("#" + t + " " + resultCal);
		}
	}

	private static int calculator(String numbers, int N) {
		int num1 = 0;
		int num2 = 0;
		char oper;
		Stack<Integer> result = new Stack<>();

		char[] cal = numbers.toCharArray();

		for (int i = 0; i < N; i++) {
			if (cal[i] >= '0' && cal[i] <= '9') {
				result.push(cal[i] - '0');
			} else {
				num2 = result.pop();
				num1 = result.pop();
				oper = cal[i];
				switch (oper) {
				case '+':
					result.push(num1 + num2);
					break;
				case '-':
					result.push(num1 - num2);
					break;
				case '*':
					result.push(num1 * num2);
					break;
				case '/':
					result.push(num1 / num2);
					break;
				}
			}
		}
		return result.pop();
	}

	private static String makePost(char[] str, int N) {
		String num = "";
		for (int i = 0; i < N; i++) {
			if (str[i] >= '0' && str[i] <= '9') {
				num += str[i];
				continue;
			}
			if (str[i] == '+' || str[i] == '-') {
				if (st.isEmpty()) {
					st.push(str[i]);
					continue;
				}
				if (st.peek() == '*' || st.peek() == '/') {
					num += st.pop();
					if (st.isEmpty()) {
						st.push(str[i]);
						continue;
					}
				}
				if (st.peek() == '+' || st.peek() == '-') {
					num += st.pop();
					st.push(str[i]);
					continue;
				}
			}
			if (str[i] == '*' || str[i] == '/') {
				if (st.isEmpty() || st.peek() == '+' || st.peek() == '-') {
					st.push(str[i]);
					continue;
				}
				if (st.peek() == '*' || st.peek() == '/') {
					num += st.pop();
					st.push(str[i]);
					continue;
				}
			}
		}
		while (!st.isEmpty()) {
			num += st.pop();
		}
		return num;
	}
}