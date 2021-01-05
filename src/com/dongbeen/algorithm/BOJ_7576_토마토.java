package com.dongbeen.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_7576_토마토 {
   static int ans = 0;
   static int[] dr = { 0, 0, 1, -1 };
   static int[] dc = { 1, -1, 0, 0 };
   static int M, N;
   static int[][] box;

   public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);
      M = sc.nextInt();
      N = sc.nextInt();
      box = new int[N][M];
      for (int r = 0; r < N; r++) {
         for (int c = 0; c < M; c++) {
            box[r][c] = sc.nextInt();
         }
      }

      Queue<Tomato> qu = new LinkedList<>();
      boolean[][] visited = new boolean[N][M];
      for (int r = 0; r < N; r++) {
         for (int c = 0; c < M; c++) {
            if (box[r][c] == 1) {
               qu.offer(new Tomato(r, c, 0));
            }
         }
      }
      while (!qu.isEmpty()) {
         Tomato to = qu.poll();
         visited[to.r][to.c] = true;
         ans = Math.max(ans, to.day);
         for (int i = 0; i < 4; i++) {
            int nr = to.r + dr[i];
            int nc = to.c + dc[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && box[nr][nc] == 0 && !visited[nr][nc]) {
               qu.offer(new Tomato(nr, nc, to.day + 1));
            }
         }
      }
      if (check(visited)) {
         ans = -1;
      }
      System.out.println(ans);
   }

   private static boolean check(boolean[][] visited) {
      for (int r = 0; r < N; r++) {
         for (int c = 0; c < M; c++) {
            if (box[r][c] == 0 && !visited[r][c]) {
               return true;
            }
         }
      }
      return false;
   }

   static class Tomato {
      int r, c, day;

      public Tomato(int r, int c, int day) {
         this.r = r;
         this.c = c;
         this.day = day;
      }
   }
}