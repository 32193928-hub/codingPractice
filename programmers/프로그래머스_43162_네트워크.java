import java.util.*;
import java.io.*;
public class Solution{
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
        	Queue<int[]> linked = new ArrayDeque<int[]>();
        	linked.offer(computers[i]);
        	if(visited[i]) continue;
        	else {
        		answer++;
        		while(!linked.isEmpty()) {
        			int[] thisLine = linked.poll();
        			for(int j=0; j<n; j++) {
        				if(thisLine[j]==1&&!visited[j]) {
        					if(i!=j) linked.offer(computers[j]); 
        					visited[j]=true;
        				}
        			}
        		}
        	}
        }
        return answer;
    }
    
    static int[][] floydWarShall(int n, int[][] computers) {
    	
    	int[][] retArr = new int[n][n];
    	for(int i=0; i<n; i++) {
    		retArr[i] = computers[i].clone();
    	}
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<n; j++) {
    			for(int k=0; k<n; k++) {
    				if(retArr[i][k]==1&&retArr[k][j]==1) retArr[i][j]=1;
    			}
    		}
    	}
    	return retArr;
    }
    
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		System.out.print("n 입력");
		int n = Integer.parseInt(br.readLine());
		System.out.println();
		
		System.out.println("computers 입력");
		int[][] computers = new int[n][n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				computers[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println();
		
		sb.append("#").append(1).append(" ").append(solution(n, computers));
		
		System.out.println(sb);
	}
}