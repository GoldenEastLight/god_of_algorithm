package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2309_일곱난쟁이_부분집합 {
	private static int[] heights, ans;
	private static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		heights = new int[9]; // 난쟁이들의 키
		isSelected = new boolean[9]; // 부분집합에 포함여부 저장
		ans = new int[7]; // 최종 선택된 7 난쟁이의 키 저장
		for (int i = 0; i < 9; i++)
			heights[i] = Integer.parseInt(br.readLine());

		find(0, 0, 0);
		// 선택된 일곱난쟁이를 찾아서 ans배열에 담자
		for (int i = 0, j = 0; i < 9; i++) {
			if (isSelected[i])
				ans[j++] = heights[i];
		}

		Arrays.sort(ans);
		for (int i = 0; i < 7; i++) {
			System.out.println(ans[i]);
		}
	}

	public static boolean find(int index, int cnt, int sum) {

		if (cnt == 7 && sum == 100) // 답
			return true;

		if (cnt == 7 || index == 9 || sum > 100)
			return false;

		// 선택
		isSelected[index] = true;
		if (find(index + 1, cnt + 1, sum + heights[index]))
			return true; // 선택한 상황을 반영하여 다음으로 넘어감.

		// 비선택
		isSelected[index] = false;
		return find(index + 1, cnt, sum);
	}
}
