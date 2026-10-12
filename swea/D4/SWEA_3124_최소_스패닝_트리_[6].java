import java.util.*;
import java.io.*;

public class Solution_3124 {
	static int[] head;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			int V, E;
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
//			int[][] distance = new int[V][V];
			head = new int[V];
			for(int i=0; i<V; i++) {
				head[i] = i;
			}
			PriorityQueue<int[]> edgeList = new PriorityQueue<int[]>((E1, E2)->
				Integer.compare(E1[2], E2[2])
			);
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken())-1;
				int B = Integer.parseInt(st.nextToken())-1;
				int C = Integer.parseInt(st.nextToken());
//				distance[A][B] = C;
				edgeList.offer(new int[] {A, B, C});
			}
			
			
			int cnt = 0;
			long totalCost = 0;
			while(!edgeList.isEmpty()&&cnt!=V-1) {
				int[] point = edgeList.poll();
				if(union(point[0],point[1])) {
					cnt++;
//					totalCost+=distance[point[0]][point[1]];
					totalCost+=point[2];
				} else {
					continue;
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(totalCost).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static boolean union(int i, int j) {
		if(findHead(i)==findHead(j)) return false;
		else {
			head[findHead(j)]=findHead(i);
			return true;
		}
	}
	
	static int findHead(int i) {
		if(head[i]==i) return i;
		else return head[i]=findHead(head[i]);
	}
	
}
