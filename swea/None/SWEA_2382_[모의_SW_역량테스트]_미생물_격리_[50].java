import java.util.*;
import java.io.*;
public class Solution_2382 {
	static int N, M, K;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Queue<int[]> listOfMicro = new ArrayDeque<int[]>();
			boolean[][] visited = new boolean[N][N];
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int[] micro = new int[4];
				for(int j=0; j<4; j++) {
					micro[j] = Integer.parseInt(st.nextToken());
					if(j==3) micro[j]-=1;
				}
				listOfMicro.offer(micro);
				visited[micro[0]][micro[1]] = true;
			}
			
			for(int time=0; time<M; time++) {
				int size = listOfMicro.size();
				Queue<int[]> unionMicroList = new ArrayDeque<int[]>();
				List<int[]> pointList = new ArrayList<int[]>();
				Queue<int[]> falseList = new ArrayDeque<int[]>();
				for(int i=0; i<size; i++) {
					int[] thisMicro = listOfMicro.poll();
					
					falseList.offer(thisMicro);
					
					thisMicro[0]+=di[thisMicro[3]];
					thisMicro[1]+=dj[thisMicro[3]];
					
					if(isBoundaryValue(new int[] {thisMicro[0], thisMicro[1]})) {
						thisMicro[2]/=2;
						if(thisMicro[3]==0) thisMicro[3]=1;
						else if(thisMicro[3]==1) thisMicro[3]=0;
						else if(thisMicro[3]==2) thisMicro[3]=3;
						else thisMicro[3]=2;
					}
					
					if(visited[thisMicro[0]][thisMicro[1]]) {
						pointList.add(new int[] {thisMicro[0], thisMicro[1]});
					}
					
					listOfMicro.offer(thisMicro);
					visited[thisMicro[0]][thisMicro[1]]=true;
					
				}
				
				while(!falseList.isEmpty()) {
					int[] falsePoint = falseList.poll();
					visited[falsePoint[0]][falsePoint[1]]=false;
				}
				
				for(int[] point:pointList) {
					size = listOfMicro.size();
					for(int i=0; i<size; i++) {
						int[] micro = listOfMicro.poll();
						if(micro[0]==point[0]&&micro[1]==point[1]) {
							unionMicroList.offer(micro);
						}else {
							listOfMicro.offer(micro);
						}
					}
					int[] unionedMicro = union(unionMicroList);
					listOfMicro.offer(unionedMicro);
					unionMicroList.clear();
				}
				pointList.clear();
			}
			
			int mc = 0;
			for(int[] micro: listOfMicro) {
				mc+=micro[2];
			}
			
			sb.append("#").append(test_case).append(" ").append(mc).append("\n");
		}
		System.out.print(sb);
	}
	static boolean isBoundaryValue(int[] point) {
		return (point[0]==0||point[0]==N-1||point[1]==0||point[1]==N-1);
	}
	static int[] union(Queue<int[]> microorganisms) { //겹치는 미생물 반환
		int[] retMicroorganism = new int[4];
		
		int[] micro = new int[4];
		micro = microorganisms.poll();
		retMicroorganism = micro.clone();
		int maxVal =micro[2];
		
		while(!microorganisms.isEmpty()) {
			micro = microorganisms.poll();
			
			if(micro[2]>maxVal) {
				retMicroorganism[3] = micro[3];
				maxVal = micro[2];
			}
			retMicroorganism[2]+=micro[2];
		}
		
		return retMicroorganism;
	}
}
