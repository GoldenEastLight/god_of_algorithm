package master_of_algorithm;

import java.util.Scanner;

public class SWExpert_1203_햄스터 {
	int N, X, M, max;// 집의갯수, 집의최대정원, 기록의수, 지금까지 알려진 최대 햄스터 수
	int[] ans;

	int[] cage;
	int[] sum;

	class Data {
		int l, r, s;

		Data(int l, int r, int s) {
			this.l = l;
			this.r = r;
			this.s = s;
		}
	}

	Data[] datas;

	public static void main(String[] args) {
		SWExpert_1203_햄스터 m = new SWExpert_1203_햄스터();
		m.service();
	}

	void service() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			X = sc.nextInt();
			M = sc.nextInt();
			datas = new Data[M];
			cage = new int[N + 1];
			sum = new int[N + 1];
			ans = new int[N + 1];
			max = -1;
			for (int i = 0; i < M; i++) {
				datas[i] = new Data(sc.nextInt(), sc.nextInt(), sc.nextInt());
			}
			solve(1);
			if (max == -1)
				System.out.println("#" + tc + " " + max);
			else {
				StringBuilder sb = new StringBuilder("#" + tc + " ");
				for (int i = 1; i <= N; i++)
					sb.append(ans[i]).append(" ");
				System.out.println(sb);
			}
		}

	}

	void solve(int idx) {
		// idx가 N번째 집까지 완료한 후에 N + 1에 도달하면 기저파트.
		if (idx == N + 1) {
			// datas배열에 들어있는 햄스터 마리수에 일치하는 조합인지 검사.
			for (int i = 0; i < M; i++) {
				// data[i].l부터 data[i].r까지의 햄스터 마리수가 data[i].s 마리가 아니라면
				if (sum[datas[i].r] - sum[datas[i].l - 1] != datas[i].s)
					return;
			}
			// 여기까지 왔다는건 M개의 기록을 모두 만족한다는 것.
			if (max < sum[N]) {
				// 갱신.
				max = sum[N];
				for (int i = 1; i <= N; i++)
					ans[i] = cage[i];
			}
			return;
		}

		for (int hamster = 0; hamster <= X; hamster++) {
			// idx번째 집에 hamster만큼의 햄스터를 배치하고.
			cage[idx] = hamster;
			sum[idx] = sum[idx - 1] + hamster;
			// 다음 집 햄스터 넣어주러 가자.
			solve(idx + 1);
		}
	}
}