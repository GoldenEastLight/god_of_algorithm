package master_of_algorithm;

import java.util.Scanner;

public class SWExpert_1952_SwimmingPool {
	int dayPay, monthPay, threeMonthPay, yearPay;
	int dayPay, monthPay, threeMonthPay, yearPay;
	int[] swimmingPlan;
	int minCost;

	public static void main(String[] args) {
		SWExpert_1952_SwimmingPool m = new SWExpert_1952_SwimmingPool();
		m.service();
	}

	private void service() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			dayPay = sc.nextInt();
			monthPay = sc.nextInt();
			threeMonthPay = sc.nextInt();
			yearPay = sc.nextInt();
			swimmingPlan = new int[12];
			minCost = Integer.MAX_VALUE;

			for (int i = 0; i < 12; i++) {
				swimmingPlan[i] = sc.nextInt();
			}
			
			makePlan();

			System.out.println("#" + t + " ");
		}
		sc.close();
	}

	private void makePlan() {
		for(int i = 0; i < 12; i++) {
			
		}
	}
}
