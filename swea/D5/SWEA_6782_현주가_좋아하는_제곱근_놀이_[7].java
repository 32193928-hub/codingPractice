import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			long N = Long.parseLong(br.readLine());
			
			int cnt=0;
			while(N!=2) {
				long rootedNumber = (long) Math.sqrt(N);
				if(rootedNumber*rootedNumber==N) {
					N = rootedNumber;
					cnt++;
				}
				else {
					long nextNumber = rootedNumber+1;
					long nextSquaredNumber = nextNumber*nextNumber;
					cnt+=nextSquaredNumber-N;
					N = nextSquaredNumber;
				}
			}
			sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
