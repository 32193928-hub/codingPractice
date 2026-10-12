import java.util.*;
import java.io.*;

public class Solution{
	static int N;
	static int[][] cheeseArr;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			N = Integer.parseInt(br.readLine());
			cheeseArr = new int[N][N];
			
			int maxCheese = 0;
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					cheeseArr[i][j] = Integer.parseInt(st.nextToken());
					maxCheese = Math.max(maxCheese, cheeseArr[i][j]);
				}
			}
			
			int maxCnt = 0;
			for(int i=0; i<=Math.min(maxCheese, 100); i++) {
				int cnt = findLinkedCnt(i);
				maxCnt = Math.max(maxCnt, cnt);
			}
			sb.append(maxCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	static int findLinkedCnt(int day) {
		int cnt = 0;
		boolean[][] visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(cheeseArr[i][j]>day&&!visited[i][j]) {
					Queue<int[]> q = new ArrayDeque<int[]>();
					q.offer(new int[] {i,j});
					visited[i][j]=true;
					while(!q.isEmpty()) {
						int[] newPoint = q.poll();
						for(int dir=0; dir<4; dir++) {
							int newI = newPoint[0]+di[dir];
							int newJ = newPoint[1]+dj[dir];
							if((N>newI&&newI>=0)&&(N>newJ&&newJ>=0)) {
								if(!visited[newI][newJ]&&cheeseArr[newI][newJ]>day) {
									q.offer(new int[] {newI, newJ});
									visited[newI][newJ]=true;
								}
							}
						}
					}
					cnt++;
				}
				else continue;
			}
		}
		return cnt;
	}
}
