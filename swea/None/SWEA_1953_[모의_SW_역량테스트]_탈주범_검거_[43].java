import java.util.*;
import java.io.*;

public class Solution{
	static int N, M;
	static int R, C;
	static int L;
	
	static int[] di = {0,-1,0,1}; 
	static int[] dj = {-1,0,1,0};
	
	static int[][] arr;
	
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			arr = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<int[]> point = new ArrayDeque<int[]>();
			point.offer(new int[] {R, C});
			visited[R][C]=true;
			int time = 1;
			while(time!=L&&!point.isEmpty()) {
				int size = point.size();
				for(int qsize=0; qsize<size; qsize++) {
					int[] thisPoint = point.poll();
					int way = cango(thisPoint[0], thisPoint[1]);
					for(int i=0; i<4; i++) {
						if((way&1<<i)!=0) {
							int insertDir = (i+2)%4;
							int newI = thisPoint[0]+di[i];
							int newJ = thisPoint[1]+dj[i];
							if(!visited[newI][newJ]&&((cango(newI, newJ)&1<<insertDir)!=0)) {
								point.offer(new int[] {newI, newJ});
								visited[newI][newJ]=true;
							}
						}
					}
				}
				time++;
			}
			int answer = 0;
			for(int i=0; i<N;i++) {
				for(int j=0; j<M; j++) {
					if(visited[i][j]) answer++;
				}
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static int cango(int thisI, int thisJ) {
		int answer = 0;
		switch(arr[thisI][thisJ]) {
			case 1:
				for(int i=0; i<4; i++) {
					int newI = thisI+di[i];
					int newJ = thisJ+dj[i];
					if(N>newI&&newI>=0&&M>newJ&&newJ>=0) {
						int insertDir = (i+2)%4;
						answer|=1<<i;
					}
				}
				break;
			case 2:
				for(int i=0; i<4; i++) {
					if(i==0||i==2) continue;//湲곕뫁 �듅�꽦
					int newI = thisI+di[i];
					int newJ = thisJ+dj[i];
					if(N>newI&&newI>=0&&M>newJ&&newJ>=0) {
						int insertDir = (i+2)%4;
						answer|=1<<i;
					}
				}
				break;
			case 3:
				for(int i=0; i<4; i++) {
					if(i==1||i==3) continue;//湲곕뫁 �듅�꽦
					int newI = thisI+di[i];
					int newJ = thisJ+dj[i];
					if(N>newI&&newI>=0&&M>newJ&&newJ>=0) {
						int insertDir = (i+2)%4;
						answer|=1<<i;
					}
				}
				break;
			case 4:
				for(int i=0; i<4; i++) {
					if(i==0||i==3) continue;//湲곕뫁 �듅�꽦
					int newI = thisI+di[i];
					int newJ = thisJ+dj[i];
					if(N>newI&&newI>=0&&M>newJ&&newJ>=0) {
						int insertDir = (i+2)%4;
						answer|=1<<i;
					}
				}
				break;
			case 5:
				for(int i=0; i<4; i++) {
					if(i==0||i==1) continue;//湲곕뫁 �듅�꽦
					int newI = thisI+di[i];
					int newJ = thisJ+dj[i];
					if(N>newI&&newI>=0&&M>newJ&&newJ>=0) {
						int insertDir = (i+2)%4;
						answer|=1<<i;
					}
				}
				break;
			case 6:
				for(int i=0; i<4; i++) {
					if(i==1||i==2) continue;//湲곕뫁 �듅�꽦
					int newI = thisI+di[i];
					int newJ = thisJ+dj[i];
					if(N>newI&&newI>=0&&M>newJ&&newJ>=0) {
						int insertDir = (i+2)%4;
						answer|=1<<i;
					}
				}
				break;
			case 7:
				for(int i=0; i<4; i++) {
					if(i==2||i==3) continue;//湲곕뫁 �듅�꽦
					int newI = thisI+di[i];
					int newJ = thisJ+dj[i];
					if(N>newI&&newI>=0&&M>newJ&&newJ>=0) {
						int insertDir = (i+2)%4;
						answer|=1<<i;
					}
				}
				break;
			default:
				break;
		}
		return answer;
	}
	
}
