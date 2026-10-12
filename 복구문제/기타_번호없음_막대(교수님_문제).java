import java.util.*;
import java.io.*;
public class Solution_막대_장태종 {
	
	static int[] memo;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int  T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			memo = new int[N+1];

			sb.append("#").append(test_case).append(" ").append(calc(N)).append("\n");
		}
		System.out.println(sb);
	}
	
	static int calc(int n) {
		if(memo[n]!=0) return memo[n];
		else if(n==1) return 2;
		else if(n==2) return 5;
		else return memo[n]=calc(n-1)*2+calc(n-2);
	}
	
}
