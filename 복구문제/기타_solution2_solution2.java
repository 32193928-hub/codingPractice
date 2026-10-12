import java.util.*;
import java.io.*;

public class Solution_2 {
	
	static int N, M;
	static int[][] OriginalArr;
	static int[][] arr;
	static int[] di = {0,-1,0,1};
	static int[] dj = {-1,0,1,0};
	static boolean[][] foodArr;
	static int MaxFood;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case=1; test_case<=T; test_case++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			OriginalArr = new int[N][N];
			
			MaxFood = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0; j<N; j++) {
					OriginalArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//입력 초기화 완료
			
			for(int i=0;i<N;i++) {
				for(int j=0; j<N; j++) {
					for(int k=0; k<4; k++) {
						arr = copyArr(OriginalArr);
						if(arr[i][j]==0) {
							
							foodArr = new boolean[N][N];
							robotMoving(0, i, j, k, 0);
						}
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(MaxFood).append("\n");
		}
		System.out.println(sb);
	}
	
	
	//nowDays = 재귀 돌면서 날짜 카운트, not I, J는 현재 위치, dir은 현재 방향, cnt는 수확횟수
	static void robotMoving(int nowDays, int nowI, int nowJ, int dir,int cnt) { 
		OneDays();
		int newDir = canMove(nowI, nowJ, dir);
		if(foodArr[nowI][nowJ]==true) { //오전
			cnt++;
			foodArr[nowI][nowJ]=false;
		}else {

			if(newDir==-1) {//움직일 수 없다는뜻.
				//아무것도 할 수 없으므로 대기 오전 오후 모두. 현재 위치가 빈 농지더라도
				
			}
			else {
				arr[nowI][nowJ]=-5;
				foodArr[nowI][nowJ]=true;
			}
		}
		
		if(nowDays==M-1) {
			MaxFood = Math.max(MaxFood, cnt);
			return;
		}
		if(newDir==-1) {
			robotMoving(nowDays+1, nowI, nowJ, dir, cnt);
		}else {
			robotMoving(nowDays+1, nowI+di[newDir], nowJ+dj[newDir], newDir, cnt);
		}
	}
	
	
	
	static int canMove(int nowI, int nowJ, int dir) {
		int retDir = -1;
		int newDir = dir+5;
		
		for(int i=0; i<4; i++) {
			
			int newDir_ = newDir-i;
			newDir_=newDir_%4;
			int newI = nowI+di[newDir_];
			int newJ = nowJ+dj[newDir_];
			if(arr[newI][newJ]==0) {
				retDir = newDir_;
				break;
			}
			
		}
		return retDir;
	}
	
	
	
	static void OneDays() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j]<0) {
					arr[i][j]++;
				}
			}
		}
	}
	
	static int[][] copyArr(int[][] originArr){
		int[][] arr = new int[originArr.length][originArr.length];
		for(int i=0; i<originArr.length; i++) {
			arr[i] = originArr[i].clone();
		}
		return arr;
	}
}
/*
10
6 11
1 1 1 1 1 1
1 1 0 0 0 1
1 0 0 0 0 1
1 1 0 0 0 1
1 0 0 0 0 1
1 1 1 1 1 1
6 10
1 1 1 1 1 1
1 0 1 0 1 1
1 1 0 1 0 1
1 0 1 0 1 1
1 1 0 1 0 1
1 1 1 1 1 1
7 26
1 1 1 1 1 1 1
1 0 0 0 0 0 1
1 1 0 1 0 0 1
1 0 0 0 0 1 1
1 0 1 0 0 0 1
1 0 0 1 0 0 1
1 1 1 1 1 1 1
7 32
1 1 1 1 1 1 1
1 1 0 0 1 0 1
1 0 1 0 0 0 1
1 0 1 1 0 0 1
1 0 0 1 0 0 1
1 1 0 0 0 1 1
1 1 1 1 1 1 1
7 35
1 1 1 1 1 1 1
1 1 0 0 1 1 1
1 0 0 0 0 0 1
1 1 0 0 1 0 1
1 0 0 1 0 0 1
1 0 1 0 0 1 1
1 1 1 1 1 1 1
8 40
1 1 1 1 1 1 1 1
1 0 0 0 0 0 1 1
1 0 0 1 1 0 1 1
1 0 1 0 1 0 0 1
1 0 0 0 1 0 1 1
1 0 1 1 0 0 0 1
1 1 0 0 0 1 1 1
1 1 1 1 1 1 1 1
8 42
1 1 1 1 1 1 1 1
1 0 0 0 0 1 0 1
1 0 0 1 0 1 0 1
1 1 0 1 0 0 0 1
1 0 0 0 0 0 1 1
1 1 0 1 1 0 1 1
1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1
9 45
1 1 1 1 1 1 1 1 1
1 0 1 1 1 0 0 1 1
1 0 0 0 1 0 0 0 1
1 0 0 0 1 0 1 1 1
1 1 1 0 1 0 0 0 1
1 1 0 1 1 0 0 1 1
1 1 1 0 1 1 0 0 1
1 0 0 0 0 1 0 0 1
1 1 1 1 1 1 1 1 1
9 48
1 1 1 1 1 1 1 1 1
1 0 1 0 0 0 0 1 1
1 0 1 0 0 0 1 0 1
1 0 1 0 0 0 0 0 1
1 1 0 0 1 0 1 1 1
1 1 0 0 0 1 1 1 1
1 0 0 1 0 1 1 0 1
1 1 1 0 1 1 1 1 1
1 1 1 1 1 1 1 1 1
9 50
1 1 1 1 1 1 1 1 1
1 0 1 1 1 0 0 1 1
1 0 1 0 0 1 0 1 1
1 0 1 0 0 0 0 1 1
1 1 1 0 0 0 0 1 1
1 0 0 1 1 1 1 1 1
1 1 0 0 1 1 0 1 1
1 1 1 1 0 0 1 0 1
1 1 1 1 1 1 1 1 1
*/
