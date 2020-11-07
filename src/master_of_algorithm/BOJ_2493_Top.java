package master_of_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493_Top {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stt = new StringTokenizer(br.readLine());
		int[] idx = new int[N + 1];
		Stack<int[]> topStack = new Stack<int[]>();
		int[] first = new int[2];
		first[0] = Integer.parseInt(stt.nextToken());
		first[1] = 1;
		topStack.push(first);
		
		for (int i = 2; i <= N; i++) {
			int temp = Integer.parseInt(stt.nextToken());
			
			while (!topStack.isEmpty()) {
				if (temp < topStack.peek()[0]) {
					idx[i] = topStack.peek()[1];
					break;
				}
				if (temp > topStack.peek()[0]) {
					topStack.pop();
				}				
			}
			topStack.push(new int[] {temp, i});			
		}
		for (int i = 1; i <= N; i++) {
			System.out.print(idx[i] + " ");
		}
	}
}