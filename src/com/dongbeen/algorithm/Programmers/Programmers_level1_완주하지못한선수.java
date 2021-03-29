package com.dongbeen.algorithm.Programmers;

import java.util.Arrays;

public class Programmers_level1_완주하지못한선수 {
	public String solution(String[] participant, String[] completion) {
		Arrays.sort(participant);
		Arrays.sort(completion);
		String answer = participant[(participant.length) - 1];

		for (int i = 0; i < completion.length; i++) {
			if (!completion[i].equals(participant[i])) {
				answer = participant[i];
				break;
			}
		}

		return answer;
	}
}
