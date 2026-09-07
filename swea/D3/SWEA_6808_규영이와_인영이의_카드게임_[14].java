import java.util.*;
import java.io.*;
public class Solution{
	static int[] cardList;
	static boolean[] visited;
	static List<int[]> allList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
			allList = new ArrayList<int[]>();
			int[] numList = new int[9];
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean[] eighteen = new boolean[19];
			for(int i=0; i<9; i++) {
				numList[i] = Integer.parseInt(st.nextToken());
				eighteen[numList[i]] = true;
			}
			cardList = new int[9];
			int idx = 0;
			for(int i=1; i<=18; i++) {
				if(!eighteen[i]) {cardList[idx]=i; idx++;}
			}
			visited = new boolean[9];
			makeList(0, new int[9]);
			
//			int testIndex = 0;
//			for(int[] list: allList) {
//				for(int element:list) {
//					System.out.print(element+" ");
//				}
//				testIndex++;
//				System.out.println();
//			}
			
			int winCnt = 0;
			int loseCnt = 0;
			for(int[] list:allList) {
				int winSum = 0;
				int loseSum = 0;
				for(int i=0; i<9; i++) {
					if(numList[i]>list[i]) {loseSum+=numList[i]+list[i];}
					else if(numList[i]<list[i]) {winSum+=numList[i]+list[i];}
					else {}
				}
				if(winSum>loseSum) {winCnt++;}
				else if(loseSum>winSum) {loseCnt++;}
			}
			sb.append("#").append(test_case).append(" ").append(loseCnt).append(" ").append(winCnt).append("\n");
		}
		System.out.println(sb);
	}
	static void makeList(int cnt, int[] answerList){
		if(cnt==9) { 
			int[] addList = answerList.clone();
			allList.add(addList);
			
		}
		else {
			for(int i=0; i<9; i++) {
				if(!visited[i]) {
					visited[i] = true;
					answerList[cnt] = cardList[i];
					makeList(cnt+1, answerList);
					visited[i] = false;
				}
			}
		}
	}
}
