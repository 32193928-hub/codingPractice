import java.util.*;
import java.io.*;
public class Solution3260 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String num1;
			String num2;
			num1 = st.nextToken();
			num2 = st.nextToken();
			int maxLength = 0;
			Stack stack = new Stack<Integer>();
			if(num1.length()>num2.length()) {
				maxLength = num1.length();
			} else {maxLength = num2.length();}
			int remain = 0;
			for(int i=1; i<=maxLength; i++) {
				
				int n1;
				int n2;
				if(num1.length()-i>=0) {
					n1 = num1.charAt(num1.length()-i) - '0';
				} else {n1 = 0;}
				if(num2.length()-i>=0) {
					n2 = num2.charAt(num2.length()-i) - '0';
				} else {n2 = 0;}
				int sum = n1 + n2 + remain;
				if(sum/10>0) {
					remain = sum/10;
				} else {
					remain = 0;
				}
				stack.push(sum%10);
			}
			if(remain!=0) {
				stack.push(remain);
			}
			sb.append("#").append(test_case).append(" ");
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
