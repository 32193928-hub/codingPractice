import java.util.*;
import java.io.*;

public class Solution{
	static int N;
	static int[][] dessertArr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			N = Integer.parseInt(br.readLine());
			dessertArr = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					dessertArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxValue = -1;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					for(int k=1; j-k>=0; k++) {
						for(int l=1; j+l<N; l++) {
							int[] top = {i, j};
							int[] left = {i+k, j-k};
							int[] right = {i+l, j+l};
							int[] down = {i+l+k, j+l-k};
							if((i+k<N&&j-k>=0)&&(i+l<N&&j+l<N)&&(i+l+k<N&&N>j+l-k&&j+l-k>=0)) {
								int cnt = calc(left, top, right, down);
								maxValue = Math.max(maxValue, cnt);
							}
						}
					}
				}
			}
			sb.append(maxValue).append("\n");
		}
		System.out.println(sb);
	}
	
	static int calc(int[] left, int[] top, int[] right, int[] down) {
		int ret = -1;
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; left[1]+i<top[1]; i++) {					//left to top -1 +1
			int dessertNum = dessertArr[left[0]-i][left[1]+i];
			if(list.contains(dessertNum)) {return ret;}
			else {list.add(dessertNum);}
		}
		for(int i=0; top[1]+i<right[1]; i++) {					//top to right +1 +1
			int dessertNum = dessertArr[top[0]+i][top[1]+i];
			if(list.contains(dessertNum)) {return ret;}
			else {list.add(dessertNum);}
		}
		for(int i=0; right[1]-i>down[1]; i++) {					//right to down +1 -1
			int dessertNum = dessertArr[right[0]+i][right[1]-i];
			if(list.contains(dessertNum)) {return ret;}
			else {list.add(dessertNum);}
		}
		for(int i=0; down[1]-i>left[1]; i++) {					//down to left -1 -1
			int dessertNum = dessertArr[down[0]-i][down[1]-i];
			if(list.contains(dessertNum)) {return ret;}
			else {list.add(dessertNum);}
		}
		
		return list.size();
		
	}
	
}
