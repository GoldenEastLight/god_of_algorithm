package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_문자열내p와y의개수 {
	boolean solution(String s) {
		int pCnt = 0, yCnt = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'p' || s.charAt(i) == 'P')
				pCnt++;
			else if (s.charAt(i) == 'y' || s.charAt(i) == 'Y')
				yCnt++;
		}
		
		if (pCnt == yCnt)
			return true;
		return false;
	}
}
