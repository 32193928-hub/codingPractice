import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] dp = new int[K + 1];

			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int volume = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				for(int j=K; j>=volume; j--) {
					dp[j] = Math.max(dp[j], dp[j-volume] + value);
				}
			}

			sb.append("#").append(test_case).append(" ").append(dp[K]).append("\n");
		}

		System.out.print(sb);
	}
}
