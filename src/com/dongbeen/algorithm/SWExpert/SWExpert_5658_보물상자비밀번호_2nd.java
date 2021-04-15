package com.dongbeen.algorithm.SWExpert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 28분 걸렸음, 25분 걸릴 수 있었는데 허니브레드 먹는다고 3분 씀
 */

public class SWExpert_5658_보물상자비밀번호_2nd {
	int N;
	int K;
	int ans;

	public static void main(String[] args) {
		new SWExpert_5658_보물상자비밀번호_2nd().service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			K = sc.nextInt();
			String numbers = sc.next();
			char[] nums = numbers.toCharArray();
			ArrayList<String> bignums = new ArrayList<>();
			ArrayList<Integer> answers = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				String one = "";
				String two = "";
				String three = "";
				String four = "";

				for (int j = 0; j < N / 4; j++)
					one += nums[j];
				for (int j = N / 4; j < N / 2; j++)
					two += nums[j];
				for (int j = N / 2; j < N / 4 * 3; j++)
					three += nums[j];
				for (int j = N / 4 * 3; j < N; j++)
					four += nums[j];

				if (!bignums.contains(one))
					bignums.add(one);
				if (!bignums.contains(two))
					bignums.add(two);
				if (!bignums.contains(three))
					bignums.add(three);
				if (!bignums.contains(four))
					bignums.add(four);

				char temp = nums[N - 1];
				for (int j = N - 1; j > 0; j--) {
					nums[j] = nums[j - 1];
				}
				nums[0] = temp;
			}

			for (int i = 0; i < bignums.size(); i++) {
				answers.add(Integer.parseInt(bignums.get(i), 16));
			}
			Collections.sort(answers, Collections.reverseOrder());
			ans = answers.get(K - 1);
			System.out.println("#" + t + " " + ans);
		}
	}
}
