package com.dongbeen.algorithm.Programmers;

import java.util.ArrayList;

public class Programmers_level1_모의고사_Array {
	int[] studentOne = { 1, 2, 3, 4, 5 };
	int[] studentTwo = { 2, 1, 2, 3, 2, 4, 2, 5 };
	int[] studentThree = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
	int oneCnt = 0;
	int twoCnt = 0;
	int threeCnt = 0;
	int maxCnt = 0;
	int[] answer;

	public int[] solution(int[] answers) {
		solve(answers);
		return answer;
	}

	public void solve(int[] answers) {
		ArrayList<Integer> answerList = new ArrayList<>();

		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == studentOne[(i) % 5])
				oneCnt++;
			if (answers[i] == studentTwo[(i) % 8])
				twoCnt++;
			if (answers[i] == studentThree[(i) % 10])
				threeCnt++;
		}
		maxCnt = Math.max(oneCnt, Math.max(twoCnt, threeCnt));

		if (maxCnt == oneCnt)
			answerList.add(1);
		if (maxCnt == twoCnt)
			answerList.add(2);
		if (maxCnt == threeCnt)
			answerList.add(3);

		answer = new int[answerList.size()];

		for (int i = 0; i < answer.length; i++) {
			answer[i] = answerList.get(i);
		}
	}
}
