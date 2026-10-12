import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			int[] fee = new int[4];
			int[] schedule = new int[13];
			int[] lowerFee = new int[13];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=12; i++) {
				schedule[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<=12; i++) {
				
				int oneMonth = Math.min(schedule[i]*fee[0], fee[1]);
				lowerFee[i] = lowerFee[i-1]+oneMonth;
				
				if(i>=3) {
					lowerFee[i]=Math.min(lowerFee[i-3]+fee[2], lowerFee[i]);
				}
				else {
					lowerFee[i] = Math.min(lowerFee[i], fee[2]);
				}
			}
			
			int result = Math.min(lowerFee[12], fee[3]);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
