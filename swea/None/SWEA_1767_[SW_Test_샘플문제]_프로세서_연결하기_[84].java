import java.util.*;
import java.io.*;

public class Solution1767 {
	static class Core{
		int i, j;
		Core(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	static int N;
	static int[][] arr;
	static ArrayList<Core> cores;
	static int maxCore, minLength;
	
	static int[] di = {0, -1, 0, 1};
	static int[] dj = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			cores = new ArrayList<Core>();
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]==1) {
						if(i>0 && i<N-1 && j>0 && j<N-1) {
							cores.add(new Core(i, j));
						}
					}
				}
			}
			maxCore = 0;
			minLength = Integer.MAX_VALUE;
			
			dfs(0, 0, 0);
			sb.append("#").append(test_case).append(" ").append(minLength).append("\n");
			
		}
		System.out.print(sb);
	}
	
	static void dfs(int idx, int coreCount, int wireLength) {
		if(idx==cores.size()) {
			if(coreCount>maxCore) {
				maxCore = coreCount;
				minLength = wireLength;
			} else if(coreCount==maxCore) {
				minLength = Math.min(minLength, wireLength);
			}
			return ;
		}
		
//		if(coreCount + (cores.size()-idx) < maxCore) {return;}
		
		Core cur = cores.get(idx);
		
		for(int d=0; d<4; d++) {
			int count = canLine(cur.i, cur.j, d);
			if(count>0) {
				setLine(cur.i, cur.j, d, 2);
				dfs(idx+1, coreCount+1, wireLength+count);
				setLine(cur.i, cur.j, d, 0);
			}
		}
		
		dfs(idx+1, coreCount, wireLength);
	}
	
	static int canLine(int i, int j, int dir) {
		int ni = i + di[dir];
		int nj = j + dj[dir];
		int length = 0;
		
		while(ni>=0 && ni<N && nj>=0 && nj<N) {
			if(arr[ni][nj] != 0) {
				return 0;
			}
			length++;
			ni += di[dir];
			nj += dj[dir];
		}
		return length;
	}
	
	static void setLine(int i, int j, int dir, int val) {
		int ni = i+di[dir];
		int nj = j+dj[dir];
		
		while(ni>=0 && ni<N && nj>=0 && nj<N) {
			arr[ni][nj]=val;
			ni += di[dir];
			nj += dj[dir];
		}
	}
}
