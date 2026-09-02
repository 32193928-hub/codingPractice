import java.util.*;
import java.io.*;
public class Solution_1226 {
	static int[][] arr;
	static boolean[][] visited;
	static int[] di = {0, -1, 0, 1};
	static int[] dj = {-1, 0, 1, 0};
	
	static int[][] findZero(int[] p){
		List<int[]> list = new ArrayList<int[]>();
		int cnt = 0;
		for(int dir = 0; dir<4; dir++) {
			int newPi = p[0]+di[dir];
			int newPj = p[1]+dj[dir];
			if(arr[newPi][newPj]==0||arr[newPi][newPj]==3) {
				list.add(new int[] {newPi, newPj});
				cnt++;
			}
		}
		int[][] ret = new int[cnt][2];
		for(int i=0; i<cnt; i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case<=10; test_case++) {
			arr = new int[16][16];
			visited = new boolean[16][16];
			int T = Integer.parseInt(br.readLine());
			Queue<int[]> list = new ArrayDeque<int[]>();
			int[] startPoint= {1,1};
			
			for(int i=0; i<16; i++) {										//arr, visited 배열 초기화
				String line = br.readLine();
				for(int j=0; j<16; j++) {
					arr[i][j] = line.charAt(j)-'0';
					visited[i][j] = false;
					if(arr[i][j]==2) {
						startPoint = new int[] {i, j};
					}
				}
			}
			//arr 프린트
//			for(int[] i: arr) {
//				for(int j:i) {
//					System.out.print(j);
//				}
//				System.out.println();
//			}
			//arr 프린트
			int result = 0;
			list.offer(startPoint);
			while(!list.isEmpty()) {
				int size = list.size();
				for(int i=0; i<size; i++) {
					
					int[] nowPoint = list.poll();
					
					if(arr[nowPoint[0]][nowPoint[1]]==3) {
						result = 1;
						list.clear();
						break;
					}
					
					visited[nowPoint[0]][nowPoint[1]]=true;
					
//					//현재 위치 확인
//					System.out.println("\n========"+nowPoint[0]+","+nowPoint[1]+"========\n");
//					//현재 위치 확인					
					
					int[][] zeroList = findZero(nowPoint);
					
//					//방향 확인
//					for(int[] checkList:zeroList) {
//						for(int check:checkList) {
//							System.out.print(check+ " ");
//						}
//						System.out.println();
//					}
//					//방향 확인
					
					for(int[] pList:zeroList) {
						if(visited[pList[0]][pList[1]]) {
							continue;
						}
						else {
							list.add(pList);
						}
					}
				}
			}
			sb.append("#").append(T).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
