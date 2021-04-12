package com.dongbeen.algorithm.Programmers;

import java.util.ArrayList;
import java.util.Arrays;

public class Programmers_level1_나누어떨어지는숫자배열 {
	public int[] solution(int[] arr, int divisor) {
		int[] answer = { -1 };
		ArrayList<Integer> answers = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % divisor == 0)
				answers.add(arr[i]);
		}
		if (answers.size() > 0) {
			answer = new int[answers.size()];
			for (int i = 0; i < answer.length; i++) {
				answer[i] = answers.get(i);
			}
		} else {
			return answer;
		}
		Arrays.sort(answer);

		return answer;
	}
}
