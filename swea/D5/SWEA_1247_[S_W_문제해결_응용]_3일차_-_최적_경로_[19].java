import java.util.*;
import java.io.*;

public class Solution {
	static int[][] nPoints;
	static int[] distance;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			nPoints = new int[N+2][N+2];	//0회사 1~N 고객집 N+1집
			
			distance = new int[N+2];
			
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[0]=0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N+2; i++) {
				
				if(i==0) {
					nPoints[i][0]=Integer.parseInt(st.nextToken());
					nPoints[i][1]=Integer.parseInt(st.nextToken());
				}else if(i==1){
					nPoints[N+1][0]=Integer.parseInt(st.nextToken());
					nPoints[N+1][1]=Integer.parseInt(st.nextToken());
				}else {
					nPoints[i-1][0]=Integer.parseInt(st.nextToken());
					nPoints[i-1][1]=Integer.parseInt(st.nextToken());
				}
			}
//			System.out.println(Arrays.toString(distance));
			makeDistance(0, 0, 0, 0);
//			System.out.println(Arrays.toString(distance));
			int answer = Integer.MAX_VALUE;
			for(int i=1; i<=N; i++) {
				distance[i]=distance[i]+getDistance(i, N+1);
				answer = Math.min(answer, distance[i]);
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
			
		}
		System.out.println(sb);
		
	}
	
	static void makeDistance(int prevIndex, int flag, int dis, int cnt) {
		if(cnt==N) {
			distance[prevIndex]=Math.min(distance[prevIndex], dis);
			return;
		}
		else {
			for(int i=1; i<=N; i++) {
				int newFlag = flag;
				newFlag=newFlag|(1<<i);
				if(i==prevIndex) continue;
				if(newFlag!=flag) {
					
					makeDistance(i, newFlag, dis+getDistance(prevIndex, i), cnt+1);
				}
			}
		}
	}
	
	static int getDistance(int s, int e) {
		return Math.abs(nPoints[s][0]-nPoints[e][0])+Math.abs(nPoints[s][1]-nPoints[e][1]);
	}
	
}
