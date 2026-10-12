import java.util.*;
import java.io.*;

public class Solution{
	static int N, M, C;
	static int[][] arr;
	static int maxVal;
	static int totalMax;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			totalMax = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(j+M-1 > N-1) continue;
					int kStart = i, lStart = j+M;
					if(lStart+M-1 > N-1) {
						kStart += 1; lStart=0;
					}
					
					maxVal = 0;
					findMax(i, j, j, new ArrayList<Integer>(), 0);
					int maxOne = maxVal;
					
					for(int k=kStart; k<N; k++) {
						if(i!=k) lStart = 0;
						for(int l=lStart; l<N; l++) {
							if(l+M-1 > N-1) continue;
							
							maxVal = 0;
							findMax(k, l, l, new ArrayList<Integer>(), 0);
							int maxTwo = maxVal;
							
							totalMax = Math.max(totalMax, maxOne+maxTwo);
							
						}
					}
					
					
					
				}
			}
			sb.append("#").append(test_case).append(" ").append(totalMax).append("\n");
		}
		System.out.println(sb);
	}
	
	static void findMax(int di, int dj, int idx, List<Integer> list, int sum) {
		if(idx>dj+M-1) {
			int powSum = 0;
			for(int element:list) {
				powSum+=element*element;
			}
			maxVal = Math.max(maxVal, powSum);
		}
		
		else {
			List<Integer> newList = new ArrayList<Integer>(list);
			newList.add(arr[di][idx]);
			int newSum = sum+arr[di][idx];
			if(newSum<=C) {
				findMax(di, dj, idx+1, newList, newSum);
				
			}
			findMax(di, dj, idx+1, list, sum);
		}
		
	}
	
}
