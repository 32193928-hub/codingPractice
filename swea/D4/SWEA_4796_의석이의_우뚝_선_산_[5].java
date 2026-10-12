import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	public static void main(String[] args) throws IOException{
//		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
//		int T = Integer.parseInt(br.readLine());
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int test_case = 1; test_case<=T; test_case++) {
			
			N = sc.nextInt();
			
			int[] arr = new int[N];
			
//			String readLine = sc.nextLine();
//			System.out.println(readLine);
//			String[] readArr = readLine.split(" ");
//			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			
			int down = 0;
			int up = 0;
			int lastIndex = -1;
			int mountainCnt = 0;
			for(int i=1; i<N; i++) {
				if(arr[i]>arr[i-1]) {
					if(down!=0) {
						mountainCnt += down*up;
						down = 0;
						up = 1;
						continue;
					}
					else {
						up++;
					}
					
				}
				else {
					if(up==0) {continue;}
					down++;
				}
				
			}
			mountainCnt += down*up;
			sb.append("#").append(test_case).append(" ").append(mountainCnt).append("\n");
		}
		System.out.println(sb);
	}
}
