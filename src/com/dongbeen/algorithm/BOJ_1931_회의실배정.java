/*
11
1 4
3 5
0 6
5 7
3 8
5 9
6 10
8 11
8 12
2 13
12 14
*/
package com.dongbeen.algorithm;

import java.util.Arrays;
import java.util.Scanner;


// VO를 이용한 1차원배열 버전
public class BOJ_1931_회의실배정 {

	static Meeting[] arr;
	static int answer = 1;
	
	public static void solve() {
		
		Arrays.sort(arr); // 회의 종료시간(오름) 순서대로 정렬
		
		Meeting temp = arr[0]; // 첫번째 회의 확정
		for (int j = 1; j < arr.length; j++) {
			// 확정된 앞 회의의 종료시간이 다음 회의시작보다 같거나 크다면 회의 확정
			if (temp.end <= arr[j].start) {
				temp = arr[j]; 
				answer++;
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		arr = new Meeting[N];

		for (int i = 0; i < N; i++) {
			arr[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}

		// 가능한 회의 찾기
		solve();
		
		System.out.println(answer);
		sc.close();

	}
	static class Meeting implements Comparable<Meeting> {
		int start;
		int end;
		
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			int value = end - o.end; 
			if(value != 0) { // 종료시간이 다르다면
				return value;
			}
			return start - o.start; // 종료시간이 같을 경우 시작시간이 빠른 순으로 정렬되도록 한다.
		}
	}
}

