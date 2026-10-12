import java.util.*;
import java.io.*;

public class Solution{
	static int N;
	static char[][] originArr;
	static char[][] arr;
	static int[] di = {-1,-1,-1, 0,0, 1,1,1};
	static int[] dj = {-1,0,1, -1,1, -1,0,1};
	static int minCnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
			 N = Integer.parseInt(br.readLine());
			 originArr = new char[N][N];
			 for(int i=0; i<N; i++) {
				 
				 String initializedLine = br.readLine().trim(); 
				 
				 for(int j=0; j<N; j++) {
					 originArr[i][j] = initializedLine.charAt(j);
				 }
				 
			 }
			 //위까지가 originArr에 초기화된 초기 map을 저장
			 arr = new char[N][N];
			 for(int i=0; i<N; i++) {
				 arr[i]=originArr[i].clone();
			 }
			 minCnt=Integer.MAX_VALUE;
			 search();
			 sb.append("#").append(test_case).append(" ").append(minCnt).append("\n");
			 
		}
		System.out.println(sb);
	}
	
	static void search() {
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(isClean(i, j)) {
					click(i,j);
					cnt++;
				}
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j]=='.') {
					cnt++;
				}
			}
		}
		minCnt = Math.min(minCnt, cnt);
	}
	
	static void click(int nowI, int nowJ) {
		int mineCnt = 0;
		int ground = 0;
		List<Integer> index = new ArrayList<Integer>();
		for(int i=0; i<8; i++) {
			int newI = nowI+di[i];
			int newJ = nowJ+dj[i];
			if((N>newI&&newI>=0)&&(N>newJ&&newJ>=0)) {
				if(arr[newI][newJ]=='*') {mineCnt++;}
				else if(arr[newI][newJ]=='.') {ground++; index.add(i);}
				else {ground++;}
			}
		}
		arr[nowI][nowJ]=(char)(mineCnt+65);
		
		if(mineCnt==0) {
			for(int i:index) {
				int newI = nowI+di[i];
				int newJ = nowJ+dj[i];
				if((N>newI&&newI>=0)&&(N>newJ&&newJ>=0)) {
					if(arr[newI][newJ]=='.') {
						click(newI, newJ);
					}
				}
			}
		}
	}
	
	static boolean isClean(int nowI, int nowJ) {
		if(arr[nowI][nowJ]!='.') return false;
		for(int i=0; i<8; i++) {
			int newI = nowI+di[i];
			int newJ = nowJ+dj[i];
			
			
			
			if((N>newI&&newI>=0)&&(N>newJ&&newJ>=0)) {
				if(arr[newI][newJ]=='*') {
					return false;
				}
			}
		}
		return true;
	}
}
