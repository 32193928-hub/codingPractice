import java.util.*;
import java.io.*;

public class Solution {
	static int N, M;
	static boolean[][] linked;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			linked = new boolean[N+1][N+1];
			for(int i=1; i<=N; i++) {
				linked[i][i]=true;
			}
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = -1;
				int to = -1;
				start = Integer.parseInt(st.nextToken());
				if(st.hasMoreTokens()) to = Integer.parseInt(st.nextToken());
				else to = start;
				linked[start][to]=true;
				linked[to][start]=true;
			}
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(linked[i][k]&&linked[k][j]) linked[i][j]=true;
					}
				}
			}
			
			boolean[] visited = new boolean[N+1];
			int cnt=0;
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					cnt++;
					for(int j=1; j<=N; j++) {
						if(linked[i][j]) visited[j]=true;
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
