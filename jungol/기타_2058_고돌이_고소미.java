import java.util.*;
import java.io.*;
public class Jungol_2058 {
	static int N;
	static int[][] arr;
	static int[] X,Y;
	
	static boolean[][] xvisited;
	static boolean[][] yvisited;
	static Set<List<Integer>> xyvisited;
	
	static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1, 0}; //12-1-3-5-6-7-9-11-����
	static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1, 0};
	
	public static void main(String[] args) throws IOException{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(sc.next()); //N
		X = new int[4];
		Y = new int[4];
		
//		String[] str = sc.next().split(" "); //X 4��
		for(int i=0; i<4; i++) {
			X[i] = sc.nextInt()-1;
		}
//		str = sc.nextLine().split(" "); // Y 4��
		for(int i=0; i<4; i++) {
			Y[i] = sc.nextInt()-1;
		}
		
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
//			str = sc.nextLine().split(" ");
			for(int j=0; j<N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		xvisited = new boolean[N][N];
		yvisited = new boolean[N][N];
		xyvisited = new HashSet<List<Integer>>();
		
		Queue<int[]> points = new ArrayDeque<int[]>();
		points.offer(new int[] {X[0], X[1], Y[0], Y[1]});
		
		int time=-1;
		
		while(!points.isEmpty()) {
			int size = points.size();
			for(int i=0; i<size; i++) {
				int[] nowPoints = points.poll();
				int[] nowX = new int[] {nowPoints[0], nowPoints[1]};
				int[] nowY = new int[] {nowPoints[2], nowPoints[3]};
				
				if((nowX[0]==X[2]&&nowX[1]==X[3])&&(nowY[0]==Y[2]&&nowY[1]==Y[3])) {
					points.clear();
					break;
				}
				
				boolean[] nowXcanGo = new boolean[9];
				boolean[] nowYcanGo = new boolean[9];
				
				nowXcanGo = cango(nowX);
				nowYcanGo = cango(nowY);
				for(int dirX=0; dirX<9; dirX++) {
					if(!nowXcanGo[dirX]) continue;
					
					int[] newX = new int[] {nowX[0]+di[dirX], nowX[1]+dj[dirX]};
					
					for(int dirY=0; dirY<9; dirY++) {
						
						if(!nowYcanGo[dirY]) continue;
						int[] newY = new int[] {nowY[0]+di[dirY], nowY[1]+dj[dirY]};
						
						if((Math.abs(newX[0]-newY[0])<=1)&&(Math.abs(newX[1]-newY[1])<=1)) continue;
						
						int visitedSize = xyvisited.size();
						xyvisited.add(Arrays.asList(newX[0], newX[1], newY[0], newY[1]));
						if(visitedSize==xyvisited.size()) continue;
						
						points.offer(new int[] {newX[0], newX[1], newY[0], newY[1]});
						xvisited[newX[0]][newX[1]]=true;
						yvisited[newY[0]][newY[1]]=true;
						
					}
				}
				
			}
			time++;
		}
		System.out.println(time);
	}
	
	static boolean[] cango(int[] point) { //type=true�� y 
		boolean[] retBoolArr = new boolean[9];
		for(int i=0; i<8; i++) {
			int newI = point[0]+di[i];
			int newJ = point[1]+dj[i];
			if(isin(new int[] {newI, newJ})) {
				if((arr[newI][newJ]==0)) retBoolArr[i]=true;
			}
		}
		retBoolArr[8]=true;
		return retBoolArr;
	}
	
	static boolean isin(int[] point) {
		boolean retBool=false;
		if((N>point[0]&&point[0]>=0)&&(N>point[1]&&point[1]>=0)) retBool = true;
		return retBool;
	}
	
}
