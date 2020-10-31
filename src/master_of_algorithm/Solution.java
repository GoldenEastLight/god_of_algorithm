package master_of_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
 
public class Solution {
    static class Position implements Comparable<Position>{
        int x, y, time;
 
        public Position(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
 
        @Override
        public int compareTo(Position o) {
            return this.time - o.time;
        }
    }
    static int N, MIN;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = { {-1, 0},{0, 1},{1, 0},{0, -1} };
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;
         
        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; testcase++) {
            MIN = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
             
            for (int i = 0; i < N; i++) {
                str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }
             
            bfs();
             
            sb.append("#").append(testcase).append(" ").append(MIN).append("\n");
        }
        System.out.println(sb.toString());
         
    } //main
     
    private static void bfs() {
        PriorityQueue<Position> q = new PriorityQueue<>();
         
        q.offer(new Position(0, 0, 0));
        visited[0][0] = true;
         
        Position p;
        int x, y;
        while(!q.isEmpty()) {
            p = q.poll();
            if(p.x == N-1 && p.y == N-1) {
                if(MIN > p.time) MIN = p.time;
                continue;
            }
             
            for (int d = 0; d < 4; d++) {
                x = p.x + dir[d][0];
                y = p.y + dir[d][1];
                 
                if(x >= 0 && x < N && y < N && y >= 0 && !visited[x][y]) {
                    visited[x][y] = true;
                    q.offer(new Position(x, y, p.time+map[x][y]));
                }
            }
        }
    }
     
} // class