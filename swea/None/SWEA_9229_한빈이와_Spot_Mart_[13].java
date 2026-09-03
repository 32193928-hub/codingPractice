import java.util.*;
import java.io.*;
public class Solution_9229 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int sum = -1;
			Integer[] snackN = new Integer[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				snackN[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(snackN);
			int start = 0;
			int end = N-1;
			while(start<end) {
				int weight = snackN[start]+snackN[end];
				if(weight<=M) {
					if(weight>sum) {
						sum = weight;
					}
					else {
						start++;
					}
				}
				else {
					end--;
				}
			}
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
