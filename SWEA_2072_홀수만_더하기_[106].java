import java.util.*;
import java.io.*;
public class Solution2072 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sum = 0;
			while(st.hasMoreTokens()) {
				int t = Integer.parseInt(st.nextToken());
				if(t%2!=0) {sum+=t;}
			}
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
