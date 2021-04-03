package com.dongbeen.algorithm.Programmers;

import java.util.ArrayList;

public class Programmers_level1_같은숫자는싫어 {
	public int[] solution(int[] arr) {
		ArrayList<Integer> ansList = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				ansList.add(arr[i]);
				continue;
			} else if (arr[i - 1] != arr[i])
				ansList.add(arr[i]);
		}

		int[] answer = new int[ansList.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = ansList.get(i);
		}
		return answer;
	}
}
