import java.util.*;
import java.io.*;

public class Solution {
	static int[][] arr;
	static int[][] makedMap;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			makedMap = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(makedMap[i], Integer.MAX_VALUE);
			}
			makedMap[0][0]=0;
			
			
			for(int i=0; i<N; i++) {
				String[] line = br.readLine().split("");
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			
			Queue<Integer> queue = new ArrayDeque<Integer>();
			queue.offer(encode(0, 0));
			
			while(!queue.isEmpty()) {
				int[] point = {getI(queue.peek()), getJ(queue.poll())};
				for(int i=0; i<4; i++) {
					int newI = point[0]+di[i];
					int newJ = point[1]+dj[i];
					if(0<=newI&&newI<N&&0<=newJ&&newJ<N) {
						if(makedMap[point[0]][point[1]]+arr[newI][newJ]<makedMap[newI][newJ]) {
							queue.offer(encode(newI, newJ));
							makedMap[newI][newJ]=makedMap[point[0]][point[1]]+arr[newI][newJ];
						}
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(makedMap[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
	}
	
	static int encode(int i, int j) {
		return i*N+j;
	}
	
	static int getI(int code) {
		return code/N;
	}
	
	static int getJ(int code) {
		return code%N;
	}
	
}
