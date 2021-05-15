package com.dongbeen.algorithm.Programmers;

import java.util.ArrayList;

public class Programmers_gabia2 {
	ArrayList<String> good = new ArrayList<>(); // 좋은문자열 넣을 ArrayList

	public int solution(String s) {
		char[] sArr = s.toCharArray();

		for (int i = 0; i < sArr.length; i++) {
			comp(sArr, i);
		}

		return good.size();
	}

	public void comp(char[] sArr, int n) {
		StringBuilder temp = new StringBuilder();
		ArrayList<Character> check = new ArrayList<>();

		for (int i = n; i < sArr.length; i++) {
			if (!check.contains(sArr[i])) {
				check.add(sArr[i]);
				temp.append(sArr[i]);
				if (!good.contains(temp.toString())) {
					good.add(temp.toString());
				}
			} else
				return;
		}
	}
}
