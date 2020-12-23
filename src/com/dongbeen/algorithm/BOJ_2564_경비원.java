/**
10 5
3
1 4
3 2
2 8
2 3

23
*/

package com.dongbeen.algorithm;

import java.util.Scanner;

public class BOJ_2564_경비원 {
	static class Store {
		int location; // 1:북, 2:남, 3:서, 4:동
		int distance; // 1,2:왼쪽부터거리 / 3,4:위쪽부터거리

		public Store(int location, int distance) {
			this.location = location;
			this.distance = distance;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int width = sc.nextInt(); // 블록 가로길이
		int height = sc.nextInt(); // 블록 세로길이
		int N = sc.nextInt();
		Store[] stores = new Store[N];
		int sum = 0;

		for (int i = 0; i < N; i++) {
			stores[i] = new Store(sc.nextInt(), sc.nextInt());
		}

		Store dong = new Store(sc.nextInt(), sc.nextInt());

		for (int i = 0; i < N; i++) {
			if (dong.location == stores[i].location) {
				sum += Math.abs(stores[i].distance - dong.distance);
			} else if (stores[i].location == 1) { // 북
				if (dong.location == 2) {
					sum += Math.min(height + dong.distance + stores[i].distance,
							height + (width - dong.distance) + (width - stores[i].distance));
				} else if (dong.location == 3) {
					sum += dong.distance + stores[i].distance;
				} else if (dong.location == 4) {
					sum += (width - stores[i].distance) + dong.distance;
				}
			} else if (stores[i].location == 2) { // 남
				if (dong.location == 1) {
					sum += Math.min(height + dong.distance + stores[i].distance,
							height + (width - dong.distance) + (width - stores[i].distance));
				} else if (dong.location == 3) {
					sum += stores[i].distance + (height - dong.distance);
				} else if (dong.location == 4) {
					sum += (height - dong.distance) + (width - stores[i].distance);
				}
			} else if (stores[i].location == 3) { // 서
				if (dong.location == 1) {
					sum += dong.distance + stores[i].distance;
				} else if (dong.location == 2) {
					sum += (height - stores[i].distance) + dong.distance;
				} else if (dong.location == 4) {
					sum += Math.min(width + dong.distance + stores[i].distance,
							width + (height - dong.distance) + (height - stores[i].distance));
				}
			} else if (stores[i].location == 4) { // 동
				if (dong.location == 1) {
					sum += (width - dong.distance) + stores[i].distance;
				} else if (dong.location == 2) {
					sum += (width - dong.distance) + (height - stores[i].distance);
				} else if (dong.location == 3) {
					sum += Math.min(width + dong.distance + stores[i].distance,
							width + (height - dong.distance) + (height - stores[i].distance));
				}

			}
		}
		System.out.println(sum);
	}
}
