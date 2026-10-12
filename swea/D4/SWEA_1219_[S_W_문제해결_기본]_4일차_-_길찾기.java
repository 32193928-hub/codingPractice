import java.util.*;
import java.io.*;
public class Solution5105 {
	public static boolean arrive(int[] way1, int[] way2) {
		boolean[] visited = new boolean[100];
		int result = 0;
		Queue q = new LinkedList<Integer>();
		if(way2[0]!=0) {
			q.offer(way1[0]);
			q.offer(way2[0]);
		}
		else {
			q.offer(way1[0]);
		}
		while(!q.isEmpty()) {
			for(int i=0; i<q.size(); i++) {
				int el = (int)q.poll();
//				System.out.print(el+" ");
				if(el==99) {return true;}
				else {
					if(q.contains(way1[el]) && q.contains(way2[el])) {continue;}
					if((way1[el]==0)&&(way2[el]==0)) {}
					else if(way1[el]==0) {
						q.offer(way2[el]);
//						System.out.println(way2[el]+" ");
					}
					else if(way2[el]==0) {
						q.offer(way1[el]);
//						System.out.println(way1[el]+" ");
					}
					else {
						q.offer(way1[el]);
//						System.out.print(way1[el]+" ");
						q.offer(way2[el]);
//						System.out.println(way2[el]);
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int testcase_num = Integer.parseInt(st.nextToken());
			int ways_count = Integer.parseInt(st.nextToken());
			
			int[] way1 = new int[100];
			int[] way2 = new int[100];
			
			st = new StringTokenizer(br.readLine());
			
			int[] str_pair = new int[(ways_count*2)];
			int index = 0;
			
			while(st.hasMoreTokens()) {
				str_pair[index]=Integer.parseInt(st.nextToken());
				index++;
			}
			for(int i=0; i<ways_count; i++) {
				if(way1[str_pair[i*2]]==0) {way1[str_pair[i*2]]=str_pair[i*2 +1];}
				else {way2[str_pair[i*2]]=str_pair[i*2 +1];}
			}
			
			boolean arrive = arrive(way1, way2);
			int ret=0;
			if(arrive) {
				ret = 1;
			}
			System.out.println("#"+testcase_num+" "+ret);
		}
	}
}
/*
1 16
0 1 0 2 1 4 1 3 4 8 4 3 2 9 2 5 5 6 5 7 7 99 7 9 9 8 9 10 6 10 3 7
2 159 
0 4 0 10 1 4 1 10 2 11 2 8 3 13 4 8 4 11 5 10 5 8 6 10 6 11 7 8 7 15 8 14 9 10 9 20 10 14 10 17 11 21 12 21 13 14 13 17 14 20 15 22 16 22 16 20 17 19 18 28 18 29 19 27 20 29 21 31 21 30 22 24 22 30 23 24 23 26 24 27 25 31 26 31 26 37 27 34 27 30 28 38 28 30 29 32 30 38 30 32 31 35 31 36 32 34 32 37 33 40 33 44 34 44 35 39 35 46 36 38 36 41 37 40 38 40 38 49 39 41 39 44 40 45 41 44 41 50 42 44 42 51 43 45 43 52 44 45 44 52 45 48 45 52 46 47 46 55 47 48 47 58 48 53 49 55 50 59 50 60 51 57 51 60 52 60 52 63 53 57 53 62 54 62 54 65 55 62 56 58 57 66 58 64 58 61 59 69 60 62 61 63 62 68 62 64 63 66 64 68 64 71 65 75 65 67 66 75 66 73 67 71 67 72 68 72 68 70 69 72 70 71 70 80 71 80 72 81 72 83 73 77 73 75 74 83 74 78 75 81 75 85 76 79 76 82 77 86 77 87 78 86 78 81 79 89 80 84 80 86 81 83 81 88 82 87 82 86 83 86 83 94 84 94 84 88 85 95 86 91 86 97 87 93 88 92 88 90 89 97 89 92 90 99 91 95 92 96 92 97 94 95 95 97 95 99 96 97 
*/

