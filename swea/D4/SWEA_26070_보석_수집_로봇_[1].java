import java.util.*;
import java.io.*;
public class Solution{
	static int[] di = {0, 1, 0, -1};
	static int[] dj = {1, 0, -1, 0};
	static int retDirection(int n) {
		return n%4;
	}
	static boolean rightDirection(int i, int j, int dir, int desti, int destj) {
		boolean answer = false;
		int destDirI = desti-i;
		int destDirJ = destj-j;
		int direction = retDirection(dir);
		if(di[direction]!=0 && di[direction]*destDirI>0) {answer = true;}
		if(dj[direction]!=0 && dj[direction]*destDirJ>0) {answer = true;}
//		System.out.println(answer);
		return answer;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			List<int[]> jewel = new LinkedList<int[]>();
			for(int i=0; i<N; i++) {											//arr에 map 생성
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]!=0) {jewel.add(new int[] {i,j});}
				}
			}
			List<int[]> newJewel = new LinkedList<int[]>();
			for(int i=1; i<=jewel.size(); i++) {
				for(int[] a:jewel) {
					if(arr[a[0]][a[1]]==i) {
						newJewel.add(a);
						continue;
					}
				}
			}
			int turnN = 0;
			int[] now = {0, 0};
			while(!newJewel.isEmpty()) {
				int[] nowJewel = newJewel.get(0);
//				System.out.print("dest is "+nowJewel[0]+","+nowJewel[1]+" ");
				if((now[0]==nowJewel[0])&&(now[1]==nowJewel[1])) {
//					System.out.print("--hit!--\n");
					newJewel.remove(0);
					continue;
				}
				if(rightDirection(now[0], now[1], turnN, nowJewel[0], nowJewel[1])) {
					now[0]+=di[retDirection(turnN)];
					now[1]+=dj[retDirection(turnN)];
				} else {
					if(rightDirection(now[0], now[1], turnN+1, nowJewel[0], nowJewel[1])) {
						turnN+=1;
						now[0]+=di[retDirection(turnN)];
						now[1]+=dj[retDirection(turnN)];
					}
					else if(rightDirection(now[0], now[1], turnN+2, nowJewel[0], nowJewel[1])){
						turnN+=2;
						now[0]+=di[retDirection(turnN)];
						now[1]+=dj[retDirection(turnN)];
					}
					else if(rightDirection(now[0], now[1], turnN+3, nowJewel[0], nowJewel[1])){
						turnN+=3;
						now[0]+=di[retDirection(turnN)];
						now[1]+=dj[retDirection(turnN)];
					}
				}
//				System.out.println("now is : "+now[0]+","+now[1]+" and turnN is "+turnN);
			}
			sb.append("#").append(test_case).append(" ").append(turnN).append("\n");
		}
		System.out.println(sb);
	}
}
/*
7
5
0 0 0 0 0
0 0 0 3 0
0 1 0 0 0
0 0 2 0 0
0 0 0 0 0
5
0 0 0 0 0
0 3 0 0 0
0 0 2 0 0
0 0 4 1 0
0 0 0 0 0
5
0 0 0 0 0
0 0 1 4 0
0 5 3 0 0
0 2 0 0 0
0 0 0 0 0
7
0 0 0 0 0 0 0
0 2 0 4 0 0 0
0 0 0 0 0 6 0
0 0 0 0 5 0 0
0 0 0 0 1 3 0
0 0 7 0 0 0 0
0 0 0 0 0 0 0
10
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 5 0 0 0 0
0 0 0 0 4 0 0 0 0 0
0 0 0 10 0 0 0 0 0 0
0 0 0 0 0 0 8 0 0 0
0 0 0 0 0 0 0 0 2 0
0 0 0 0 0 0 0 1 0 0
0 0 0 0 6 9 0 0 0 0
0 0 3 0 0 0 0 0 7 0
0 0 0 0 0 0 0 0 0 0
6
0 0 0 0 0 0
0 1 0 0 0 0
0 0 4 0 0 0
0 0 2 0 0 0
0 3 0 5 0 0
0 0 0 0 0 0
8
0 0 0 0 0 0 0 0
0 0 1 0 6 0 0 0
0 0 4 0 0 0 0 0
0 5 0 3 0 7 0 0
0 0 0 0 0 0 0 0
0 8 0 0 0 0 0 0
0 0 0 0 2 0 0 0
0 0 0 0 0 0 0 0

*/