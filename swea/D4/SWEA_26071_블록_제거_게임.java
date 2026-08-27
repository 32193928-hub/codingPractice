import java.util.*;
import java.io.*;
public class Solution26071 {
	static int max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			max = 0;
			LinkedList<Integer> arr = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			dfs(arr, 0);
			sb.append("#").append(test_case).append(" ").append(max).append("\n");
		}
		System.out.print(sb);
	}
	public static void dfs(LinkedList<Integer> arr, int sum) {
		if(arr.isEmpty()) {
			if(sum>max) {
				max = sum;
			}
		}
		for(int i=0; i<arr.size(); i++) {
			int newSum = 0;
			if(i-1>=0 && i+1<=arr.size()-1) {
				newSum = sum+(arr.get(i-1)*arr.get(i+1));
			} else if(i-1>=0 && i+1>arr.size()-1) {
				newSum = sum+(arr.get(i-1));
			} else if(i+1<=arr.size()-1 && i-1<0) {
				newSum = sum+(arr.get(i+1));
			} else {
				newSum = sum+arr.get(i);
			}
			LinkedList<Integer> newArr;
			newArr = (LinkedList<Integer>) arr.clone();
			newArr.remove(i);
			dfs(newArr, newSum);
		}
	}
}
