import java.util.*;
import java.io.*;

public class Solution {
	static int N, X;
	static int[][] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			높이 차가 2 이상 나거나, 경사로 설치가 필요한데 설치를 못하는 경우.
			int usableLine = 0;
//			행검사
			for(int i=0; i<N; i++) {
				int prev = arr[i][0];
				int prevCnt = 0;
				boolean slide = false;
				int slideCnt = 0;
				boolean canUsing = true;
				for(int j=0; j<N; j++) {
					if(prev!=arr[i][j]) {
						if(slide) {
							canUsing=false;
							break;
						}
						if(Math.abs(prev-arr[i][j])>1) {
							canUsing = false;
							break;
						}
						if(prev<arr[i][j]) {
							if(prevCnt>=X) {}
							else {
								canUsing = false;
								break;
							}
						}
						if(prev>arr[i][j]) {
							slide = true;
							canUsing=false;
							slideCnt=1;
						}
						prev = arr[i][j];
						prevCnt = 1;
					}
					else {
						if(slide) {
							slideCnt++;
							if(slideCnt==X) {
								slide = false;
								canUsing=true;
								slideCnt = 0;
								prevCnt=0;
							}
						}else {
							prevCnt++;
						}
					}
				}
				if(canUsing) {
					usableLine++;
//					System.out.println(i+" 행");
				}
			}
//			열검사
			for(int i=0; i<N; i++) {
				int prev = arr[0][i];
				int prevCnt = 0;
				boolean slide = false;
				int slideCnt = 0;
				boolean canUsing = true;
				for(int j=0; j<N; j++) {
					if(prev!=arr[j][i]) {
						if(slide) {
							canUsing=false;
							break;
						}
						if(Math.abs(prev-arr[j][i])>1) {
							canUsing = false;
							break;
						}
						if(prev<arr[j][i]) {
							if(prevCnt>=X) {
								prev = arr[j][i];
								prevCnt = 1;
							}
							else {
								canUsing = false;
								break;
							}
						}
						if(prev>arr[j][i]) {
							slide = true;
							canUsing=false;
							slideCnt = 1;
							prev = arr[j][i];
							prevCnt=0;
						}
						
					}
					else {
						if(slide) {
							slideCnt++;
							if(slideCnt==X) {
								slide = false;
								canUsing=true;
								slideCnt = 0;
								prevCnt=0;
							}
						}else {
							prevCnt++;
						}
					}
				}
				if(canUsing) {
//					System.out.println(i+ " 열");
					usableLine++;
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(usableLine).append("\n");
		}
		System.out.println(sb);
	}
}


/*
1
7 2
1 1 1 1 2 1 1 
1 1 1 2 2 2 1 
2 2 2 2 2 2 2 
2 2 2 2 2 2 2 
2 2 2 2 2 2 2 
2 2 2 2 2 2 2 
2 2 2 2 2 2 2 
*/