import java.util.*;
import java.io.*;
import java.sql.Array;

public class Solution{
	static int[] arr;
	static int N;
	static int[] calc;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			calc = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			calc[0]=1;
			for(int i=1; i<N; i++) {
				for(int j=i-1; j>=0; j--) {
					if(arr[i]>arr[j]) {
						calc[i]=Math.max(calc[j]+1,calc[i]);
					}
				}
				if(calc[i]==0) calc[i]=1;
			}
			int answer = 0;
			for(int i=0; i<N; i++) {
				answer = Math.max(calc[i], answer);
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
			
		}
		System.out.println(sb);
	}
}
