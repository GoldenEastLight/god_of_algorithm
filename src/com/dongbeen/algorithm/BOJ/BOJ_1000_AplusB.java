package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_1000_AplusB {
	int N;
	int M;
	
	public static void main(String[] args) {
		BOJ_1000_AplusB m = new BOJ_1000_AplusB();
		m.service();
	}
	
	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		System.out.println(N + M);
	}
}
