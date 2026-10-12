import java.util.*;
import java.io.*;
public class Solution2806{
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			answer = 0;
			find(N, 0, new ArrayList<Integer>(), new ArrayList<Integer>());

			System.out.println("#"+test_case+" "+answer);
		}
	}
	public static void find(int n,int x, List<Integer> xList, List<Integer>yList) {
		if(xList.size()==n) {
			answer++;
			return;
		}
		for(int y=0; y<n;y++) {
			boolean rightplace = true;
			
			for (int i = 0; i < xList.size(); i++) {
				if (xList.get(i) == x || yList.get(i) == y || Math.abs(xList.get(i) - x) == Math.abs(yList.get(i) - y)) {
					rightplace = false;
					break; 
				}
			}
			
			if(rightplace) {
				xList.add(x);
				yList.add(y);
				
				find(n, x+1, xList, yList);
				
				xList.remove(xList.size()-1);
				yList.remove(yList.size()-1);
			}
		}
	}
}
