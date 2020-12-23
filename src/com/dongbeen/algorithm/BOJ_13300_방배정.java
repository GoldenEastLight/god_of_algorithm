package com.dongbeen.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//남학생 1, 여학생 0
public class BOJ_13300_방배정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] gradesBoys = new int[7]; // 소년 1~6학년 수
		int[] gradesGirls = new int[7]; // 소녀 1~6학년 수
		int roomCnt = 0;

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());

			if (sex == 0) {
				if (gradesGirls[grade] == 0) {
					roomCnt++;
					gradesGirls[grade]++;
					continue;
				}
				if (gradesGirls[grade] % K == 0) {
					roomCnt++;
				}
				gradesGirls[grade]++;
			} else {
				if (gradesBoys[grade] == 0) {
					roomCnt++;
					gradesBoys[grade]++;
					continue;
				}
				if (gradesBoys[grade] % K == 0) {
					roomCnt++;
				}
				gradesBoys[grade]++;
			}
		}
		System.out.println(roomCnt);
	}
}
