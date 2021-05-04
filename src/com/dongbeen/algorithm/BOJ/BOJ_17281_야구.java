package com.dongbeen.algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17281_야구 { // 문제 명이 '⚾' 임
	int N;
	int[][] players; // 각 선수가 각 이닝에서 얻는 결과
	boolean[] selected;
	int[] turn; // 타순
	int answer;

	public static void main(String[] args) throws Exception {
		new BOJ_17281_야구().service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		players = new int[N + 1][10];
		for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }
		
		selected = new boolean[10];
		turn = new int[10];
		
		selected[4] = true; // 4번 타자는 무조건 1번 타자 고정
		turn[4] = 1;
		
		answer = 0;
		permutation(2);
		
		System.out.println(answer);
	}
	
	private void permutation(int num) { // 순열
		if (num == 10) {
			play();
			return;
		}
		
		for(int i = 1; i <= 9; i++) { // 타자는 9명이다.
			if (selected[i])
				continue;
			
			selected[i] = true;
			turn[i] = num;
			permutation(num + 1);
			selected[i] = false;
		}
	}
	
	private void play() {
		int score = 0;
        int startPlayer = 1; // 이닝에서 처음 시작하는 타자
        boolean[] base; // 홈, 1루, 2루, 3루를 표현.
 
        for (int i = 1; i <= N; i++) { // N번째 이닝까지 실행 가능.
            int outCnt = 0;
            base = new boolean[4]; // base를 새롭게 초기화.
 
            outer: while (true) {
                for (int j = startPlayer; j <= 9; j++) {
                    int hitter = players[i][turn[j]]; // j번째 타자의 행동.
 
                    switch (hitter) {
                    case 0: // out.
                        outCnt++;
                        break;
                    case 1: // 1루타
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k == 3) { // 3루에 있는 선수는 홈으로 들어옴.
                                    score++; // 동시에 점수 획득.
                                    base[k] = false; // 3루는 비어있게 됨.
                                    continue;
                                }
 
                                // 1, 2루에 경우 1루씩 진루하고 원래 있던 자리는 비어있게 됨.
                                base[k] = false;
                                base[k + 1] = true;
                            }
                        }
                        base[1] = true; // 홈에서 1루로 진루.
                        break;
                    case 2: // 2루타
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k == 3 || k == 2) { // 3루 혹은 2루에 있는 선수는 홈으로 들어옴.
                                    score++; // 동시에 점수 획득.
                                    base[k] = false; // 2루 또는 3루는 비어있게 됨.
                                    continue;
                                }
 
                                // 1루에 경우 2루씩 진루하고 원래 있던 자리는 비어있게 됨.
                                base[k] = false;
                                base[k + 2] = true;
                            }
                        }
                        base[2] = true; // 홈에서 2루로 진루.
                        break;
                    case 3: // 3루타
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) { // 홈 제외 모든 선수는 홈으로 들어옴.
                                score++; // 동시에 점수 획득.
                                base[k] = false;
                            }
                        }
 
                        base[3] = true; // 홈에서 3루로 진루.
                        break;
                    case 4: // 홈런
                        for (int k = 1; k <= 3; k++) {
                            if (base[k]) { // 루상에 모든 주자가 홈으로 들어옴.
                                score++; // 주자 1명 당 점수 1점씩 획득
                                base[k] = false;
                            }
                        }
                        score++; // 홈런친 타자의 점수 1점 추가.
                        break;
                    }
 
                    if (outCnt == 3) { // 아웃 카운트가 3개 일 경우
                        // startPlayer를 그 다음 타자로 초기화 함.
                        startPlayer = j + 1;
                        if (startPlayer == 10) {
                            startPlayer = 1;
                        }
                        break outer;
                    }
                }
                // startPlayer를 1로 초기화해 주는 이유
                // 1~9번까지 타자가 한 이닝에 전부 안타를 쳐서 아웃카운트가 발생하지 않게 되면,
                // 위 반복문이 무한 루프를 돌기때문에 startPlayer = 1로 초기화해야 함.
                startPlayer = 1;
            }
        }
 
        answer = Math.max(answer, score);
	}
}
