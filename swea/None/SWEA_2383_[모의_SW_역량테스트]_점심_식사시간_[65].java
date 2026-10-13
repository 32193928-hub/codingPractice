import java.util.*;
import java.io.*;

public class Solution{
	
	static int N;
	static int[][] arr;

	static Queue<Integer> allMethod;
	static int people;
	
	static List<int[]> loc;
	static int[][] exit;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			allMethod = new ArrayDeque<Integer>();
			people = 0;
			
			exit = new int[2][2];
			
			int index = 0;
			loc = new ArrayList<int[]>();
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]>1) {
						exit[index][0]=i;
						exit[index][1]=j;
						index++;
					}else if(arr[i][j]==1) {
						people++;
						loc.add(new int[] {i, j});
					}
				}
			}
			
			findAllMethod(0, 0);
			
			int minTime = Integer.MAX_VALUE;
			while(!allMethod.isEmpty()) {
				List<Integer> exit1 = new ArrayList<Integer>();
				List<Integer> exit2 = new ArrayList<Integer>();
				int thisMethod = allMethod.poll();
				for(int i=0; i<people; i++) {
					if((thisMethod&(1<<i))==0) exit1.add(i);
					else exit2.add(i);
				}
				
				exit1.sort((a, b)->
				distance(a, 0)-distance(b, 0)
				);
				
				exit2.sort((a, b)->
				distance(a, 1)-distance(b, 1)
				);
				
				int time = Math.max(calcTime(exit1, 0), calcTime(exit2, 1));
				minTime = Math.min(minTime, time);
				
				
			}
			sb.append("#").append(test_case).append(" ").append(minTime).append("\n");
		}
		System.out.println(sb);
	}
	
	static void findAllMethod(int index, int maked) {
		if(index==people) {
			allMethod.offer(maked);
			return;
		}else {
			
			findAllMethod(index+1, maked|1<<index);
			findAllMethod(index+1,maked);
			
		}
	}
	
	static int distance(int peopleIndex, int exitIndex) {
		int[] peoloc = loc.get(peopleIndex);
		int[] exitloc = exit[exitIndex];
		return Math.abs(peoloc[0]-exitloc[0])+Math.abs(peoloc[1]-exitloc[1]);
	}
	
	static int calcTime(List<Integer> exitList, int exitIndex) {
		Queue<Integer> exitQ = new ArrayDeque<Integer>(exitList);
		Queue<Integer> stairs = new ArrayDeque<Integer>();
		int time = 0;
		while(!exitQ.isEmpty()||!stairs.isEmpty()) {
			time++;
			int size = stairs.size();
			for(int i=0; i<size; i++) {
				int thisTime = stairs.poll();
				thisTime--;
				if(thisTime!=0) stairs.offer(thisTime);
			}
			for(int i=0; i<3; i++) {
				if(!exitQ.isEmpty()&&(distance(exitQ.peek(), exitIndex)<time)) {
					if(stairs.size()<3) {
						exitQ.poll();
						stairs.offer(arr[exit[exitIndex][0]][exit[exitIndex][1]]);
					}
				}
			}
		}
		return time;
	}
	
}
