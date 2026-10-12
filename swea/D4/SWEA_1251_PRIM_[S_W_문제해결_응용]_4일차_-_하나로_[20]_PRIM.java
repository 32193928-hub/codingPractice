import java.util.*;
import java.io.*;
public class Solution {
	static long[] distance;
	static boolean[] visited;
	static int N;
	static double taxRate;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			int[] X = new int[N];
			int[] Y = new int[N];
			
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				X[i]=Integer.parseInt(st1.nextToken());
				Y[i]=Integer.parseInt(st2.nextToken());
			}
			
			taxRate = Double.parseDouble(br.readLine());
			
			distance = new long[N];
			visited = new boolean[N];
			Arrays.fill(distance, Long.MAX_VALUE);
			
			PriorityQueue<long[]> edgeList = new PriorityQueue<long[]>((E1, E2)->
				Long.compare(E1[1], E2[1])
			);
			
			distance[0]=0;
			for(long i=0; i<N; i++) {
				edgeList.offer(new long[] {i, distance[(int)i]});
			}
			
			int cnt=0;
			long totalCost = 0;
			while(cnt<N) {
				long[] node = edgeList.poll();
				if(visited[(int)node[0]]) continue;
				visited[(int)node[0]]=true;
				cnt++;
				for(int i=0; i<N; i++) {
					long di = X[(int)node[0]]-X[i];
					long dj = Y[(int)node[0]]-Y[i];
					if(!visited[i]) {
						if(distance[i]>di*di+dj*dj) {
							distance[i]=di*di+dj*dj;
							edgeList.offer(new long[] {i, distance[i]});
						}
					}
//					System.out.print(distance[i]+" ");
//					
//					System.out.print(visited[i]+" ");
//					
//					System.out.println();
				}
//				System.out.println();
			}
			
			for(int i=0; i<N; i++) {
				totalCost+=distance[i];
			}
			totalCost = Math.round(totalCost*taxRate);
			
			sb.append("#").append(test_case).append(" ").append(totalCost).append("\n");
			
		}
		System.out.println(sb);
	}
}


/*

1
4
0 0 400 400
0 100 0 100
1.0

#2 180000

*/
