import java.util.*;
import java.io.*;
public class Solution {
	static int[][] arr;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int test_case=1;test_case<=10; test_case++) {
			
			int T = Integer.parseInt(br.readLine());
			sb.append("#").append(T).append(" ");
			arr = new int[100][100];
			
			int[] startPoint = new int[2]; 
			for(int i=0; i<100; i++) {
				String[] strArr = br.readLine().split("");
				for(int j=0; j<100; j++) {
					arr[i][j] = Integer.parseInt(strArr[j]);
					if(arr[i][j]==2) {
						startPoint = new int[] {i, j};
					}
				}
			}
			
			int result = findPath(startPoint);
			sb.append(result).append("\n");
			
		}
		System.out.println(sb);
	}
	static int findPath(int[] startPoint) {
		Queue<int[]> ready = new ArrayDeque<int[]>();
		ready.offer(startPoint);
		while(!ready.isEmpty()) {
			int[] nowPoint = ready.poll();
			if(arr[nowPoint[0]][nowPoint[1]]==3) return 1;
			else {
				for(int dir=0; dir<4; dir++) {
					int newI = nowPoint[0]+di[dir];
					int newJ = nowPoint[1]+dj[dir];
					if(arr[newI][newJ]==0||arr[newI][newJ]==3) {
						ready.offer(new int[] {newI, newJ});
						if(arr[newI][newJ]==0) arr[newI][newJ]=1;
					}
				}
			}
		}
		return 0;
	}
}
