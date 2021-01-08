package com.dongbeen.algorithm;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

public class SWExpert_1218_괄호짝짓기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int num = sc.nextInt();
			char[] data = sc.next().toCharArray();
			Stack<Character> s = new Stack<>();
			int result = 1;
			outer: for (char c : data) {
				switch (c) {
				case '(':
				case '{':
				case '[':
				case '<':
					s.push(c);
					break;
				default:
					if (check(s, c) == false) {
						result = 0;
						break outer;
					}
				}
			}
			System.out.println("#" + t + " " + result);
		}
	}

	private static boolean check(Stack<Character> s, char c) {
		if (s.isEmpty())
			return false;
		char ch = s.pop();
		switch (c) {
		case ')':
			return ch == '(';
		case '}':
			return ch == '{';
		case ']':
			return ch == '[';
		case '>':
			return ch == '<';
		}
		return false;
	}
}
