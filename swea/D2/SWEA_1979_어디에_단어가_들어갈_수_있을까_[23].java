import java.util.*;
import java.io.*;

class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			
			int answer = 0;
			for(int i=0; i<N; i++) {
				int colCNT = 0;
				int rowCNT = 0;
				for(int j=0; j<N; j++) {
					if(arr[i][j]==1) {
						rowCNT++;
					} else if(rowCNT!=0) {
						if(rowCNT==K) {
							answer++;
						}
						rowCNT = 0;
					}
//					System.out.print("rowCNT : "+rowCNT+" ");
					if(arr[j][i]==1) {
						colCNT++;
					} else if(colCNT!=0) {
						if(colCNT==K) {
							answer++;
						}
						colCNT = 0;
					}
//					System.out.println("colCNT : "+colCNT);
				}
				if(colCNT==K) {answer++;}
				if(rowCNT==K) {answer++;}
				colCNT = 0;
				rowCNT = 0;
//				System.out.println("answer : "+answer);
			}
			System.out.println("#"+test_case+" "+answer);
		}
	}
}