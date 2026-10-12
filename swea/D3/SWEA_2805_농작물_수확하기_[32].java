//package study;
import java.io.*;
import java.util.StringTokenizer;

//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB&categoryId=AV7GLXqKAWYDFAXB&categoryType=CODE&problemTitle=%EB%86%8D%EC%9E%91%EB%AC%BC&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&&&&&&&&&&

public class Solution{
	
	static int[][] st_to_int(String[] st, int size){
		
		int[][] ret_arr=new int[size][size];;
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				ret_arr[i][j] = Character.getNumericValue(st[i].charAt(j));
			}
		}
		
		return ret_arr;
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			
			int size = Integer.parseInt(br.readLine());
			String[] st_arr = new String[size];
			for(int i=0; i<size; i++) {
				st_arr[i] = br.readLine();
			}
			int[][] array = st_to_int(st_arr, size);
			int[] way = {1, -1};
			
			int middle_index = size/2;
			int start_index = middle_index;
			int end_index = middle_index;
			
			int ret = 0;
			int way_index=0;
			
			for(int i=0;i<size;i++) {
				for(int j=start_index;j<=end_index;j++) {
					ret+=array[i][j];
					if(start_index==0) {
						way_index=1;
					}
				}
				start_index-=way[way_index];
				end_index+=way[way_index];
			}
			System.out.println("#"+test_case+" "+ret);
		}
	}
	
}
