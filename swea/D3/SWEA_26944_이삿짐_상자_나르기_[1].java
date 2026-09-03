import java.util.*;
import java.io.*;
public class Solution_26944 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Integer[] boxN = new Integer[N];
			Integer[] workerM = new Integer[M];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				boxN[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				workerM[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(workerM,Collections.reverseOrder());
			Arrays.sort(boxN,Collections.reverseOrder());
			int sum = 0;
			int workerIndex = 0;
			for(int box:boxN) {
				if(workerIndex==workerM.length) {
					break;
				}
				if(workerM[workerIndex]>=box) {
					sum+=box;
					workerIndex++;
				}
			}
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
