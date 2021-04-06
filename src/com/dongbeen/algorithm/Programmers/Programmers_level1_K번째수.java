package com.dongbeen.algorithm.Programmers;

import java.util.ArrayList;
import java.util.Collections;

public class Programmers_level1_K번째수 {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer;
		ArrayList<Integer> answerList = new ArrayList<>();

		for (int n = 0; n < commands.length; n++) {
			int i = commands[n][0];
			int j = commands[n][1];
			int k = commands[n][2];
			ArrayList<Integer> arrayCut = new ArrayList<>();

			for (int m = i - 1; m < j; m++) {
				arrayCut.add(array[m]);
			}
			Collections.sort(arrayCut);
			answerList.add(arrayCut.get(k - 1));
		}

		answer = new int[answerList.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = answerList.get(i);
		}
		return answer;
	}
}
