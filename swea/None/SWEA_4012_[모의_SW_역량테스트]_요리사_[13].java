import java.util.*;

import java.io.*;
public class Solution4012 {
	static List<List> halfList1;
	static List<List> halfList2;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			halfList1 = new ArrayList<List>();
			halfList2 = new ArrayList<List>();
			findHalf(0, 0, new ArrayList<>());
			
			List listAll = new ArrayList<>();
			for(int i=0; i<N; i++) {
				listAll.add(i);
			}
			
			for(List list:halfList1) {
				List el = new ArrayList<>(listAll);
				el.removeAll(list);
//				System.out.println(el);
				halfList2.add(el);
			}
			int min=Integer.MAX_VALUE;
			for(int i=0; i<halfList1.size(); i++) {
				int food1 = foodSum(arr, halfList1.get(i));
				int food2 = foodSum(arr, halfList2.get(i));
				if(Math.abs(food1-food2)<min) {
					min = Math.abs(food1-food2);
				}
			}
			sb.append("#").append(test_case).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}
	static void findHalf(int idx, int cnt,List selected1) {
		if(idx==N) {return;}
		if(cnt==N/2) {
			halfList1.add(selected1);
			return;
		}
		List newSelected1 = new ArrayList<>(selected1);
		newSelected1.add(idx);
		idx++;
		findHalf(idx, cnt, selected1);
		findHalf(idx, cnt+1, newSelected1);
		
	}
	static int foodSum(int[][] arr, List<Integer> list) {
		int answer = 0;
		int size = list.size();
		for(int i=0; i<size; i++) {
			
			for( int j=i; j<size; j++) {
				answer+=(arr[list.get(i)][list.get(j)]+arr[list.get(j)][list.get(i)]);
			}
			
		}
		return answer;
	}
}
