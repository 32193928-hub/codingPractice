import java.util.*;
import java.io.*;

public class Solution {
	static Queue<int[]> statuses = new ArrayDeque<int[]>();
	static Set<List<Integer>> usedPoint = new HashSet<List<Integer>>();
	static Set<List<Integer>> crashedPoint = new HashSet<List<Integer>>();
	static int offset = 1000;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			for(int i=0; i<N; i++) {
				int[] status = new int[4];
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<4; j++) {
					status[j] = Integer.parseInt(st.nextToken());
					if(j==0||j==1) {
						status[j] = (status[j]+offset)*2;
					}
				}
				statuses.add(status);
			}
			int energy = 0;
			while(statuses.size()>1) {
				int size = statuses.size();
				
				for(int i=0; i<size; i++) {
					int[] status = statuses.poll();
					status[0] += dx[status[2]];
					status[1] += dy[status[2]];
					
					if (status[0] < 0 || status[0] > 4000 ||status[1] < 0 || status[1] > 4000) {
						continue;
					}
					statuses.offer(status);
					
					int setSize = usedPoint.size();
					usedPoint.add(Arrays.asList(status[0], status[1]));
					if(setSize!=usedPoint.size()) {} 
					else {
						crashedPoint.add(Arrays.asList(status[0], status[1]));
					}
					
				}
				
				if(crashedPoint.size()!=0) {
					for(int i=0; i<size; i++) {
						int[] status = statuses.poll();
						if(crashedPoint.contains(Arrays.asList(status[0], status[1]))) {
							energy+=status[3];
							continue;
						}
						else {
							statuses.offer(status);
						}
					}
				}
				usedPoint.clear();
				crashedPoint.clear();
				
			}
			
			sb.append("#").append(test_case).append(" ").append(energy).append("\n");
			statuses.clear();
		}
		System.out.println(sb);
	}
}
