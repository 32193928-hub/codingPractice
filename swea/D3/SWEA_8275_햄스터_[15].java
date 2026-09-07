import java.util.*;
import java.io.*;
public class Solution {
	static int N, X, M;
	static int[][] hist;
	static int[] nowArr;
	static int[] goodArr;
	static int max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			hist = new int[M][3];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				hist[i][0] = Integer.parseInt(st.nextToken());
				hist[i][1] = Integer.parseInt(st.nextToken());
				hist[i][2] = Integer.parseInt(st.nextToken());
			}
			
			nowArr = new int[N+1];
			goodArr = null;
			max = -1;
			
			dfs(1, 0);
			
			sb.append("#").append(test_case).append(" ");
			if(goodArr==null) {
				sb.append(-1);
			} else
			for(int i=1; i<=N; i++) {
				sb.append(goodArr[i]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int index, int sum) {
		if(index>N) {
			for(int i=0; i<M; i++) {
				int totalsum = 0;
				for(int j=hist[i][0]; j<=hist[i][1]; j++) {
					totalsum+=nowArr[j];
				}
				if(totalsum!=hist[i][2]) {
					return;
				}
			}
			
			if(sum>max) {
				max = sum;
				goodArr = nowArr.clone();
			}
			return;
		}
		for(int cnt = 0; cnt<=X; cnt++) {
			nowArr[index] = cnt;
			dfs(index+1, sum+cnt);
		}
	}
}
