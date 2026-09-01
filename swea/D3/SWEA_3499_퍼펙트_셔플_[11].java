import java.util.*;
import java.io.*;
public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] words = new String[N];
			int words1 = 0;
			int words2;
			if(N%2==0) {words2 = N/2;}
			else{words2 = N/2+1;}
			for(int i=0; i<N; i++) {
				words[i] = st.nextToken();
			}
			String[] answer = new String[N];
			for(int i=0; i<N; i++) {
				if(i%2==0) {
					answer[i] = words[words1];
					words1++;
				}else {
					answer[i] = words[words2];
					words2++;
				}
			}
			sb.append("#").append(test_case).append(" ");
			for(String str:answer) {
				sb.append(str).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
