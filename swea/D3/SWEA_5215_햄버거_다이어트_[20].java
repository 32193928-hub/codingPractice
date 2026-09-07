import java.util.*;
import java.io.*;

public class Solution{
	static int maxValue;
	static int L;
	static int N;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());								//재료의 수 N
			L = Integer.parseInt(st.nextToken());								//칼로리의 제한 L

			maxValue = 0;
			int[][] ingrediantStatus = new int[N][2];								//재료의 상태 [0]선호 점수, [1]칼로리
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				ingrediantStatus[i][0] = Integer.parseInt(st.nextToken());
				ingrediantStatus[i][1] = Integer.parseInt(st.nextToken());
			}
			combination(0, 0, ingrediantStatus, 0, 0);
			sb.append("#").append(test_case).append(" ").append(maxValue).append("\n");
		}
		System.out.println(sb);
	}
	static void combination(int cnt, int idx, int[][] ingrediantStatus, int val, int sumKcal) {
		if(cnt==N || idx==N) {
			if(sumKcal<=L) {
				maxValue = Math.max(maxValue, val);
			}
			return;
		}
		else {
			if(sumKcal>L) {return;}
			else {
				combination(cnt+1, idx+1, ingrediantStatus, val+ingrediantStatus[idx][0], sumKcal+ingrediantStatus[idx][1]);
				combination(cnt, idx+1, ingrediantStatus, val, sumKcal);
			}
		}
	}
}
