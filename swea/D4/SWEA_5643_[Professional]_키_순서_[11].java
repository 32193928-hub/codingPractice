import java.util.*;
import java.io.*;
import java.io.*;
public class Solution5643 {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			boolean[][] tallList = new boolean[N+1][N+1];
			boolean[][] shortList = new boolean[N+1][N+1];
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				tallList[a][b] = true;							//tallList[x][y] 의 경우 tallList[x]에는 x보다 큰 사람의 index에 true가 되어있음 
				shortList[b][a] = true;							//shortList[x][y]의 경우 shortList[x]에는 x보다 작은 사람의 index에 true가 되어있음
			}
			int answer = 0;
			
			for(int i=1;i<=N;i++) {
				boolean[] visited = new boolean[N+1];
				int tallcnt = 0;
				Queue q = new LinkedList<Integer>();
				q.offer(i);
				tallcnt = count(visited, tallList, q);

				visited = new boolean[N+1];
				int shortcnt = 0;
				q.clear();
				q.offer(i);
				shortcnt = count(visited, shortList, q);
				
				if(tallcnt + shortcnt == N-1) {answer++;}
//				System.out.println(i+" "+tallcnt+" "+shortcnt);
				
			}
			System.out.println("#"+test_case+" "+answer);
		}
	}
	public static int count(boolean[] visited, boolean[][] list, Queue<Integer> q) {
		int answer=0;
		while(!q.isEmpty()) {
			int idx = q.poll();
			for(int i=1; i<list[idx].length; i++) {
				if(!visited[i]&&list[idx][i]) {
					q.offer(i);
					visited[i]=true;
					answer++;
				}
			}
		}
		return answer;
	}
}
/*

1
6
6
1 5
3 4
5 4
4 2
4 6
5 2

 */