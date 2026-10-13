import java.util.*;
import java.io.*;

public class Solution{
	
	static int[] di = {-1,1,1,-1};
	static int[] dj = {1,1,-1,-1};
	
	static int N, M;
	static int[][] arr;
	static int[][][] cost;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			cost = new int[N][N][N+2];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			int maxCnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					for(int k=1; k<=N+1; k++) {
						calc(i, j, k);
						int need = k*k+(k-1)*(k-1);
						if(cost[i][j][k]*M>=need) {
							maxCnt = Math.max(maxCnt, cost[i][j][k]);
						}
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(maxCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	static void calc(int i, int j, int k) {
		int[][] p = new int[4][2];
		p[0] = new int[] {i, j-k+1};
		p[1] = new int[] {i-k+1, j};
		p[2] = new int[] {i, j+k-1};
		p[3] = new int[] {i+k-1, j};
		int outLine = 0;
		if(k==1) {
			if(arr[i][j]==1) outLine = 1;
		}
		else {
			for(int tmp=0; tmp<k-1; tmp++) {
				for(int dir=0; dir<4; dir++) {
					int newI = p[dir][0]+di[dir]*tmp;
					int newJ = p[dir][1]+dj[dir]*tmp;
					if(0<=newI&&newI<N&&0<=newJ&&newJ<N) {
						if(arr[newI][newJ]==1) outLine++;
					}
				}
			}
		}
		cost[i][j][k]=cost[i][j][k-1]+outLine;
	}
	
}

/*

#6 65
1
15 8
0 0 1 0 0 0 0 0 0 0 0 0 0 0 1
0 0 0 0 0 1 0 0 0 0 0 0 0 1 1
0 0 0 0 1 0 0 0 1 1 1 0 1 0 0
1 1 0 0 0 1 0 1 0 0 0 0 1 0 0
0 1 0 0 1 0 1 0 1 0 0 0 0 1 1
0 0 0 0 0 0 0 1 0 0 0 1 0 0 0
1 0 0 0 1 0 0 0 1 0 1 0 1 0 1
1 1 1 0 0 0 0 0 1 0 0 0 0 1 0
1 1 0 0 0 0 0 0 1 0 1 0 1 0 0
1 1 0 0 1 0 1 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1 0 0 1 1 0 1
0 0 0 0 1 0 0 0 1 0 0 0 0 0 0
0 1 0 0 1 1 0 0 0 0 0 0 0 0 1
1 0 0 0 0 0 0 1 1 1 0 0 0 1 1
1 0 0 0 0 1 0 0 0 0 0 1 0 1 1

#9
1
20 10
0 0 0 0 1 0 0 1 0 0 0 0 0 0 0 1 0 1 0 1
0 0 0 0 0 1 0 0 0 0 0 0 1 0 0 0 0 0 0 0
0 0 0 1 0 0 1 0 0 1 0 0 0 1 0 0 0 0 1 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0
1 0 1 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0
0 0 0 0 0 0 1 1 1 0 0 1 1 0 0 0 1 0 0 0
0 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 1 0 1 0 0 0 0 1 0 0 0 0
1 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 1 0 0 0 0 1 0 0 1 0 1 1 0
0 1 0 0 0 1 0 1 0 1 0 0 0 0 0 0 0 1 0 0
0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0 1 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 0
0 0 1 0 0 0 0 0 1 1 1 0 0 1 0 0 0 0 0 0
0 0 0 0 0 0 1 0 1 0 1 0 1 0 0 0 0 0 0 0
0 0 1 0 1 1 1 1 1 1 0 0 0 1 0 0 0 1 0 1
0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0 0 0
0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 1 0 0

 */

