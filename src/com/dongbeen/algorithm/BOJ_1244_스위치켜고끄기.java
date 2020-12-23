package com.dongbeen.algorithm;

import java.util.Scanner;

public class BOJ_1244_스위치켜고끄기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] switchs = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			switchs[i] = sc.nextInt();
		}

		int students = sc.nextInt();

		for (int i = 0; i < students; i++) {
			int student = sc.nextInt();
			int location = sc.nextInt();
			switch (student) {
			case 1: // 남학생 - 스위치 번호가 받은 수의 배수면 스위치 상태 바꿈
				for (int loc = location; loc <= N;) {
					if (switchs[loc] == 1)
						switchs[loc] = 0;
					else
						switchs[loc] = 1;
					loc += location;
				}
				break;

			case 2: // 여학생 - 자기번호 중심 대칭으로 스위치 상태 다바꿈
				int wide = 0;
				while (true) {
					if (location - wide < 1 || location + wide > N) {
						break;
					}
					if (switchs[location - wide] == switchs[location + wide]) {
						if (wide > 0) {
							if (switchs[location - wide] == 1)
								switchs[location - wide] = 0;
							else
								switchs[location - wide] = 1;
							if (switchs[location + wide] == 1)
								switchs[location + wide] = 0;
							else
								switchs[location + wide] = 1;
						} else {
							if (switchs[location] == 1)
								switchs[location] = 0;
							else
								switchs[location] = 1;
						}
						wide++;
					} else {
						break;
					}
				}
				break;
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.print(switchs[i] + " ");
			if (i % 20 == 0) {
				System.out.println();
			}
		}
	}
}