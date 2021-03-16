package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_2742_기찍N {
	int N;
	
	public static void main(String[] args) {
		BOJ_2742_기찍N m = new BOJ_2742_기찍N();
		m.service();
	}
	
	private void service() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for(int n = N; n > 0; n--) {
			System.out.println(n);
		}
	}
}
