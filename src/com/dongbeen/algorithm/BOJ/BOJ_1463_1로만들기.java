package com.dongbeen.algorithm.BOJ;

import java.util.Scanner;

public class BOJ_1463_1로만들기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] D = new int[N + 1];
		D[0] = 0;
		D[1] = 0;

		for (int i = 2; i <= N; i++) {
			D[i] = D[i - 1] + 1;
			if (i % 2 == 0 && D[i / 2] + 1 < D[i])
				D[i] = D[i / 2] + 1;
			if (i % 3 == 0 && D[i / 3] + 1 < D[i])
				D[i] = D[i / 3] + 1;
		}

		System.out.println(D[N]);
	}
}


//import java.util.Scanner;
//public class Main1 {
//   static int[] d = new int[1000001];
//   static int dp(int x) {
//      if(x == 1) return 0;
//      if(d[x] != 0) return d[x];
//      int div3 = Integer.MAX_VALUE;
//      int div2 = Integer.MAX_VALUE;
//      if(x % 3 == 0) div3 = dp(x/3) + 1;
//      if(x % 2 == 0) div2 = dp(x/2) + 1;
//      return d[x] = Math.min(Math.min(div2, div3), dp(x-1) + 1);
//   }
//   public static void main(String[] args) {
//      Scanner sc = new Scanner(System.in);
//      int N = sc.nextInt();
//      System.out.println(dp(N));
//   }
//}