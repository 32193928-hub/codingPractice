import java.util.*;
import java.io.*;
public class Solution{
	static int V, E;
	static int[] needCnt;
	static boolean[][] connectedArr;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for(int test_case=1; test_case<=T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			needCnt = new int[V+1];
			connectedArr = new boolean[V+1][V+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<E; i++) {
				int start = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				needCnt[to]++;
				connectedArr[start][to] = true;
			}
//			System.out.println(Arrays.toString(needCnt));
			Queue<Integer> readyList = new ArrayDeque<Integer>();
			boolean[] booleanV = new boolean[V+1];
			
			for(int i=1;i<=V;i++) {
				if(needCnt[i]==0) {readyList.add(i); booleanV[i]=true;}
			}
			
			while(!readyList.isEmpty()) {
				int thisNode = readyList.poll();
				if(needCnt[thisNode]==0) {sb.append(thisNode).append(" ");}
				else if(needCnt[thisNode]>0){
					readyList.offer(thisNode);
					continue;
				}
				else {
					sb.append("\n").append("음수발생");
					break;
				}
				//needCnt == 0인 것들만 통과됨
				List<Integer> connectedList = new ArrayList<Integer>();
				for(int i=1; i<=V;i++) {
					if(connectedArr[thisNode][i]) {connectedList.add(i);}
				}
				if(connectedList.size()==0) {continue;}
				for(int nextNode:connectedList) {
					needCnt[nextNode]--;
					if(!booleanV[nextNode]) {readyList.offer(nextNode); booleanV[nextNode]=true;}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
