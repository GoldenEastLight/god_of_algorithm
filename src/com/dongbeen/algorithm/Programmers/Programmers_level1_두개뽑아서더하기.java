package com.dongbeen.algorithm.Programmers;

import java.util.ArrayList;
import java.util.Collections;

public class Programmers_level1_두개뽑아서더하기 {
	ArrayList<Integer> arr = new ArrayList<>();

	public int[] solution(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			combination(i, numbers);
		}

		int[] answer = new int[arr.size()];
		Collections.sort(arr);

		for (int i = 0; i < arr.size(); i++) {
			answer[i] = arr.get(i);
		}

		return answer;
	}

	private void combination(int n, int[] numbers) {
		for (int i = n + 1; i < numbers.length; i++) {
			if (!arr.contains(numbers[n] + numbers[i]))
				arr.add(numbers[n] + numbers[i]);
		}
	}
}