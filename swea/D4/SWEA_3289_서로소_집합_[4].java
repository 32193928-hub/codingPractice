import java.util.*;
import java.io.*;

public class Solution {
	static int[] parent;
	static int n,m;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			parent = new int[n+1];
			for(int i=1; i<=n; i++) {
				parent[i]=i;
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int operator = Integer.parseInt(st.nextToken());
				int element1 = Integer.parseInt(st.nextToken());
				int element2 = Integer.parseInt(st.nextToken());
				if(operator==0) {union(element1, element2);}
				else {
					sb.append(findParent(element1)==findParent(element2)?1:0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static int findParent(int index) {
		if(parent[index]==index) return index;
		return parent[index]=findParent(parent[index]);
	}
	
	static void union(int element1, int element2) {
		int Parent1 = findParent(element1);
		int Parent2 = findParent(element2);
		parent[Parent2] = Parent1;
	}
	
}