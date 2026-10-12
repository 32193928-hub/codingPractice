import java.util.*;
import java.io.*;

public class Solution{
	
	static int[] di = {1,0,-1,0};
	static int[] dj = {0,1,0,-1};
	static int[][] originBoard;
	static Set<List<Integer>> set1 = new HashSet<>();
	static int minTime;
	static int N;
	
	public static int solution(int[][] board) {
        int answer = 0;
        
        originBoard = new int[board.length][];
        for(int i=0; i<board.length;i++) {
        	originBoard[i] = board[i].clone();
        }
        N = originBoard.length;
        minTime = Integer.MAX_VALUE;
        set1.clear();
        set1.add(Arrays.asList(0,0,0,1));
        bfs(new int[] {0,0,0,1});
        
        answer+=minTime;
        
        return answer;
    }
	static void dfs(int[] nowPoint, int cnt) {
		if((nowPoint[0]==N-1&&nowPoint[1]==N-1)||(nowPoint[2]==N-1&&nowPoint[3]==N-1)) {minTime = Math.min(minTime, cnt); return;}
		boolean[] way = canGo(nowPoint);
		for(int i=0; i<4; i++) {
			if(way[i]) {
				int[] newPoint1, newPoint2=null;
				if(nowPoint[0]==nowPoint[2]) {
					if(i==0||i==2) {
						newPoint1 = new int[] {nowPoint[0], nowPoint[1], nowPoint[0]+di[i],nowPoint[1]+dj[i]};
						newPoint2 = new int[] {nowPoint[2]+di[i], nowPoint[3]+dj[i], nowPoint[2], nowPoint[3]};
					}
					else {
						newPoint1 = new int[] {nowPoint[0]+di[i], nowPoint[1]+dj[i], nowPoint[2]+di[i], nowPoint[3]+dj[i]};
					}
				}else { //nowPoint[1]==nowPoint[3]
					if(i==0||i==2) {
						newPoint1 = new int[] {nowPoint[0]+di[i], nowPoint[1]+dj[i], nowPoint[2]+di[i], nowPoint[3]+dj[i]};
					}
					else {
						newPoint1 = new int[] {nowPoint[0], nowPoint[1], nowPoint[0]+di[i],nowPoint[1]+dj[i]};
						newPoint2 = new int[] {nowPoint[2]+di[i], nowPoint[3]+dj[i], nowPoint[2], nowPoint[3]};
					}
				}
				int size = set1.size();
				set1.add(Arrays.asList(newPoint1[0], newPoint1[1], newPoint1[2], newPoint1[3]));
				if(size!=set1.size()) {
					dfs(newPoint1, cnt+1);
					set1.remove(Arrays.asList(newPoint1[0], newPoint1[1], newPoint1[2], newPoint1[3]));
				}
				if(newPoint2==null) {
					continue;
				}
				size = set1.size();
				set1.add(Arrays.asList(newPoint2[0], newPoint2[1], newPoint2[2], newPoint2[3]));
				if(size!=set1.size()) {
					dfs(newPoint2, cnt+1);
					set1.remove(Arrays.asList(newPoint2[0], newPoint2[1], newPoint2[2], newPoint2[3]));
				}
			}
		}
	}
	
	static void bfs(int[] Point) {
		Queue<int[]> pointList = new ArrayDeque<int[]>();
		Point = normalize(Point);
		pointList.offer(Point);
		int cnt = 0;
		while(!pointList.isEmpty()) {
			int size2 = pointList.size();
			for(int inter = 0; inter<size2; inter++) {
				int[] nowPoint = pointList.poll();
				//
				if((nowPoint[0]==N-1&&nowPoint[1]==N-1)||(nowPoint[2]==N-1&&nowPoint[3]==N-1)) {minTime = Math.min(minTime, cnt); return;}
				boolean[] way = canGo(nowPoint);
				for(int i=0; i<4; i++) {
					if(way[i]) {
						int[] newPoint1, newPoint2=null, newPoint3=null;
						if(nowPoint[0]==nowPoint[2]) {
							if(i==0||i==2) {
								newPoint1 = new int[] {nowPoint[0], nowPoint[1], nowPoint[0]+di[i],nowPoint[1]+dj[i]};
								newPoint2 = new int[] {nowPoint[2]+di[i], nowPoint[3]+dj[i], nowPoint[2], nowPoint[3]};
								newPoint3 = new int[] {nowPoint[0]+di[i], nowPoint[1]+dj[i], nowPoint[2]+di[i], nowPoint[3]+dj[i]};
							}
							else {
								newPoint1 = new int[] {nowPoint[0]+di[i], nowPoint[1]+dj[i], nowPoint[2]+di[i], nowPoint[3]+dj[i]};
							}
						}else { //nowPoint[1]==nowPoint[3]
							if(i==0||i==2) {
								newPoint1 = new int[] {nowPoint[0]+di[i], nowPoint[1]+dj[i], nowPoint[2]+di[i], nowPoint[3]+dj[i]};
							}
							else {
								newPoint1 = new int[] {nowPoint[0], nowPoint[1], nowPoint[0]+di[i],nowPoint[1]+dj[i]};
								newPoint2 = new int[] {nowPoint[2]+di[i], nowPoint[3]+dj[i], nowPoint[2], nowPoint[3]};
								newPoint3 = new int[] {nowPoint[0]+di[i], nowPoint[1]+dj[i], nowPoint[2]+di[i], nowPoint[3]+dj[i]};
							}
						}
						int size = set1.size();
						newPoint1 = normalize(newPoint1);
						set1.add(Arrays.asList(newPoint1[0], newPoint1[1], newPoint1[2], newPoint1[3]));
						if(size!=set1.size()) {
							pointList.add(newPoint1);
						}
						
						if(newPoint2==null) {
							continue;
						}
						
						size = set1.size();
						newPoint2 = normalize(newPoint2);
						set1.add(Arrays.asList(newPoint2[0], newPoint2[1], newPoint2[2], newPoint2[3]));
						if(size!=set1.size()) {
							pointList.add(newPoint2);
						}
						
						if(newPoint3==null) {
							continue;
						}
						
						size = set1.size();
						newPoint3 = normalize(newPoint3);
						set1.add(Arrays.asList(newPoint3[0], newPoint3[1], newPoint3[2], newPoint3[3]));
						if(size!=set1.size()) {
							pointList.add(newPoint3);
						}
					}
				}
				//
			}
			cnt++;
		}
	}
	
	static boolean[] canGo(int[] nowPoint) {
		boolean[] retDir = new boolean[4];
		for(int dir=0; dir<4; dir++) {
			int[] newPoint1 = {nowPoint[0]+di[dir], nowPoint[1]+dj[dir]};
			int[] newPoint2 = {nowPoint[2]+di[dir], nowPoint[3]+dj[dir]};
			if(isin(newPoint1)&&isin(newPoint2)) {
				retDir[dir]=true;
			}
		}
		return retDir;
	}
	
	static boolean isin(int[] point1) {
		if((originBoard.length>point1[0]&&point1[0]>=0)&&
				(originBoard.length>point1[1]&&point1[1]>=0&&
				originBoard[point1[0]][point1[1]]!=1)) return true;
		else return false;
	}
	
	static int[] normalize(int[] p) {
	    int r1 = p[0], c1 = p[1];
	    int r2 = p[2], c2 = p[3];

	    if (r1 > r2 || (r1 == r2 && c1 > c2)) {
	        return new int[] {r2, c2, r1, c1};
	    }
	    return p;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		System.out.print("N������ �Է� : ");
		int N = Integer.parseInt(br.readLine());
		System.out.println();
		
		System.out.println("�迭 �Է� : ");
		int[][] inputArr = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				inputArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sb.append("#").append(1).append(" ").append(solution(inputArr));
		System.out.println(sb);
	}
	
}
