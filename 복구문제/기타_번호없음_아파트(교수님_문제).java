import java.util.*;
import java.io.*;

public class Solution_아파트_장태종 {
	
	static Map<Integer, Integer> memo; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			memo = new HashMap<Integer, Integer>();
			memo.put(1, 2);
			memo.put(2, 3);
			sb.append("#").append(test_case).append(" ").append(calc(N)).append("\n");
		}
		System.out.println(sb);
	}
	static int calc(int n) {
		if(memo.getOrDefault(n, -1)<0) {
			memo.put(n, calc(n-1)+calc(n-2));
			return memo.get(n);
			
		} else {
			return memo.getOrDefault(n, -1);
		}
	}
}
