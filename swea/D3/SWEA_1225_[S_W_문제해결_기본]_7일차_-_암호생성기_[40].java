import java.util.*;
import java.io.*;

public class Solution1225{
	static boolean hasZero(int[] rawNum) {
		boolean answer = false;
		for(int num: rawNum) {
			if(num<=0) {
				answer = true;
			}
		}
		return answer;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		for(int test_case=1;test_case<=10; test_case++) {
			int T = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<Integer> rawNum = new LinkedList<>();
			for(int i=0; i<8; i++) {
				rawNum.offer(Integer.parseInt(st.nextToken()));
			}
			int minusNum = 1;
			int tmp = 1;
			while(tmp>0) {
				for(int i=1; i<=5; i++) {
					tmp = rawNum.poll();
					tmp -= i;
					if(tmp>0) {rawNum.add(tmp);}
					else {
						rawNum.add(0);
						break;
					}
				}
			}
			sb.append("#").append(T).append(" ");
			for(int i:rawNum) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}