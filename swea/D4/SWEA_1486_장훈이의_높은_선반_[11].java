import java.util.*;
import java.io.*;

public class Solution{
	static int N, B;
	static int[] arr;
	static int value; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr = new int[N];
			value = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			SumComb(0, 0);
			sb.append("#").append(test_case).append(" ").append(value-B).append("\n");
		}
		System.out.println(sb);
	}
	static void SumComb(int sum, int idx) {
		if(idx==arr.length) {
			if(sum>=B) {
				value = Math.min(sum, value);
			}
			return;
		}
		SumComb(sum+arr[idx], idx+1);
		SumComb(sum, idx+1);
	}
}