package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_두개뽑아서더하기 {
	public int[] solution(int[] numbers) {
        int[] answer = {};

        for(int i = 0; i < numbers.length; i++) {
        	combination(i, numbers);
        }
        
        return answer;
    }
	
	private void combination(int n, int[] numbers) {
		for(int i = n + 1; i < numbers.length; i++) {
			
		}
	}
}
