import java.util.*;
import java.io.*;
public class Solution {
	static int N;
	static int[] numberList;
	static char[] operator = {'+','-','*','/'};
	static int maxVal;
	static int minVal;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T; test_case++) {
			
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] operatorCnt = new int[4];
			for(int i=0; i<4; i++) {
				
				operatorCnt[i] = Integer.parseInt(st.nextToken());
				
				
			}
			
			numberList = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				numberList[i] = Integer.parseInt(st.nextToken());
			}
			
			maxVal = Integer.MIN_VALUE;
			minVal = Integer.MAX_VALUE;
			
			calc(numberList[0], 1, operatorCnt);
			
			sb.append("#").append(test_case).append(" ").append(maxVal-minVal).append("\n");
			
		}
		System.out.println(sb);
	}
	
	static void calc(int calc, int idx, int[] operatorCnt) {
		if(idx==N) {
			maxVal = Math.max(maxVal, calc);
			minVal = Math.min(minVal, calc);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(operatorCnt[i]!=0) {
				int[] newOpCnt = operatorCnt.clone();
				newOpCnt[i]--;
				int newCalc;
				if(i==0) {
					newCalc = calc+numberList[idx];
				}else if(i==1) {
					newCalc = calc-numberList[idx];
				}else if(i==2) {
					newCalc = calc*numberList[idx];
				}else {
					newCalc = calc/numberList[idx];
				}
				calc(newCalc, idx+1, newOpCnt);
			}
		}
		return;
	}
	
}
