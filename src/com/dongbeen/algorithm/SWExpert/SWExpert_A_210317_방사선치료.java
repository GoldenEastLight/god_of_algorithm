package com.dongbeen.algorithm.SWExpert;
/**
건강검진 결과 검사자의 몸에 종양이 존재하면 그림과 같이 직사각형(정사각형 포함) 모양으로 매칭되어 표시가 된다.
검사 결과 N개의 종양이 발견되어 방사선 치료를 계획하고 있으며,
각 종양의 전체 면적에 방사선을 쪼이게 되면 해당 종양이 제거가 된다.
방사선 치료장비는 한 변의 길이가 K인 정사각형 형태로 방사선 범위를 조절할 수 있으며,
건강을 위해 단 한번의 방사선 치료만 가능하다.
최대한 적은 범위로 방사선 치료를 수행하여 종양의 수를 기준 값 M개 이하로 낮추고자 한다.

N개의 종양의 위치가 평면 좌표로 주어지고, 기준 값 M이 주어진다.
종양의 수를 기준 값 M개 이하로 낮출 수 있는 최소한의 방사선 치료의 범위 K(정사각형 한 변의 길이)를 구하여라.

1. 5 <= N <= 50
2. 0 <= M < N
3. 종양은 겹치거나 포함될 수 있다.
4. 종양 좌표는 (x1, y1), (x2, y2)
5. 0 <= x1, y1, x2, y2 <= 300
 */
/**
 * input
5
5 1
7 2 5 8
4 7 6 6
4 4 3 3
0 1 3 3
1 0 4 2
5 4
5 6 4 4
8 3 6 4
1 4 2 2
1 8 2 11
4 9 3 8
10 5
12 2 10 1
5 0 4 5
3 10 5 8
1 0 0 1
6 10 7 8
0 2 1 0
6 5 1 6
7 8 6 6
4 10 6 11
4 4 7 3
30 0
102 132 11 76
8 97 107 23
36 99 74 158
92 72 31 58
86 50 122 37
97 78 159 27
98 157 13 100
27 36 4 130
3 9 18 102
69 69 152 123
86 103 98 29
54 53 151 5
1 107 12 80
95 159 5 65
48 56 4 126
1 95 44 83
94 98 130 88
97 186 72 89
37 88 85 50
111 36 22 104
71 58 34 11
70 109 24 91
93 86 129 96
107 90 30 149
95 158 64 82
38 13 58 56
99 99 142 128
138 76 68 127
101 92 30 182
102 85 56 149
50 19
193 32 227 173
271 237 90 151
264 127 151 300
100 148 183 195
149 157 283 297
258 291 42 205
260 275 262 119
281 268 294 93
32 291 23 295
135 252 6 249
5 256 83 162
58 280 295 198
126 75 153 85
167 147 169 70
300 200 152 161
175 277 231 276
195 233 108 14
265 193 172 232
176 127 26 57
281 235 223 124
9 111 251 9
238 136 161 219
101 49 80 234
155 143 285 212
142 223 58 283
264 300 217 211
94 271 27 128
191 145 169 258
147 94 196 235
139 262 117 279
264 205 230 296
170 170 258 18
259 218 245 262
287 136 283 1
96 240 154 237
269 278 275 247
297 218 293 169
236 110 286 117
19 300 174 133
258 298 285 235
162 300 229 13
42 231 268 195
288 131 258 101
282 211 280 151
279 145 271 147
273 274 191 299
179 142 48 271
49 294 22 257
132 187 75 184
159 249 163 147

 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SWExpert_A_210317_방사선치료 {
	private int N;
	private int M;
	private List<Cancer> list;

	public static void main(String[] args) {
		new SWExpert_A_210317_방사선치료().service();
	}

	private void service() {
		Scanner scn = new Scanner(System.in);

		int T = scn.nextInt();
		for (int t = 1; t <= T; t++) {
			N = scn.nextInt();
			M = scn.nextInt();
			list = new ArrayList<Cancer>();
			for (int i = 0; i < N; i++) {
				int x1 = scn.nextInt();
				int y1 = scn.nextInt();
				int x2 = scn.nextInt();
				int y2 = scn.nextInt();
				list.add(new Cancer(x1, y1, x2, y2));
			}

			int start = 0;
			int end = 300;
			while (true) {
				if (end - start <= 1)
					break;
				int mid = (start + end) / 2;
				if (check(mid)) {
					end = mid;
				} else {
					start = mid;
				}
			}
			System.out.println("#" + t + " " + end);
		}

		scn.close();
	}

	private boolean check(int size) {
		for (int i = 0; i < 300 - size + 1; i++) {
			for (int j = 0; j < 300 - size + 1; j++) {
				if (isOk(i, j, i + size, j + size))
					return true;
			}
		}
		return false;
	}

	private boolean isOk(int cx1, int cy1, int cx2, int cy2) {
		int count = N;
		for (int i = 0; i < list.size(); i++) {
			int x1 = list.get(i).x1;
			int y1 = list.get(i).y1;
			int x2 = list.get(i).x2;
			int y2 = list.get(i).y2;
			if (cx1 <= x1 && cx1 <= x2 && cx2 >= x1 && cx2 >= x2) {
				if (cy1 <= y1 && cy1 <= y2 && cy2 >= y1 && cy2 >= y2) {
					count--;
				}
			}
		}
		if (count > M)
			return false;
		return true;
	}

}

class Cancer {
	public int x1, y1, x2, y2;

	public Cancer() {
	};

	public Cancer(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}