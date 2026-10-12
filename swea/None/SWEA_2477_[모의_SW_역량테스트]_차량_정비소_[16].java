import java.util.*;
import java.io.*;

public class Solution {
	static int N, M, K, A, B;
	static int[] a;
	static int[][] aArr;
	static int[] b;
	static int[][] bArr;
	static int[] t;
	static boolean[] Acandidate;
	static boolean[] Bcandidate;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken())-1;
			B = Integer.parseInt(st.nextToken())-1;
			
			a = new int[N];
			aArr = new int[N][2];//창구번호, 0고객번호, 1들어온시간
			b = new int[M];
			bArr = new int[M][2];//창구번호, 0고객번호, 1들어온시간
			t = new int[K];
			Acandidate = new boolean[K];
			Bcandidate = new boolean[K];
			
			for(int i=0; i<N; i++) {
				aArr[i][0]=-1;
			}
			
			for(int i=0; i<M; i++) {
				bArr[i][0]=-1;
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<K; i++) {
				t[i] = Integer.parseInt(st.nextToken());
			}
			
			//한 테스트 케이스에서 쓸 모든 변수 입력 완료
			
			Queue<Integer> customer = new ArrayDeque<Integer>();
			Queue<Integer> repairDeskWait = new ArrayDeque<Integer>();
			Queue<Integer> receptionDeskWait = new ArrayDeque<Integer>();
			
			for(int i=0; i<K; i++) {
				customer.offer(i);
			}
			
			int time=0;
			
			while(!customer.isEmpty()||!repairDeskWait.isEmpty()||!receptionDeskWait.isEmpty()||!isAllEmpty()) {
				int size = customer.size();
				for(int i=0; i<size; i++) {
					if(t[customer.peek()]<=time) {
						repairDeskWait.offer(customer.poll());
					}else {
						break;
					}
					
				}
				for(int i=0; i<N; i++) {
//					System.out.print("#"+time+" "+aArr[i][0]+" "+aArr[i][1]+" ");
					if(aArr[i][0]!=-1) {
						if(time-aArr[i][1]>=a[i]) {
							receptionDeskWait.offer(aArr[i][0]);
							aArr[i][0]=-1;
						}
						else {
							continue;
						}
					}
					if(repairDeskWait.size()!=0) {
						if(i==A) Acandidate[repairDeskWait.peek()]=true;
						aArr[i][0]=repairDeskWait.poll();
						aArr[i][1]=time;
					}
				}
				
				for(int i=0; i<M; i++) {
//					System.out.println(bArr[i][0]+" "+bArr[i][1]);
					if(bArr[i][0]!=-1) {
						if(time-bArr[i][1]>=b[i]) {
							bArr[i][0]=-1;
						}
						else {
							continue;
						}
					}
					if(receptionDeskWait.size()!=0) {
						if(i==B) Bcandidate[receptionDeskWait.peek()]=true;
						bArr[i][0]=receptionDeskWait.poll();
						bArr[i][1]=time;

					}
				}
				time++;
			}
			int answer = 0;
			for(int i=0; i<K; i++) {
				if(Acandidate[i]&&Bcandidate[i]) answer+=i+1;
			}
//			System.out.println(Arrays.toString(Acandidate));
//			System.out.println(Arrays.toString(Bcandidate));
			if(answer==0) answer = -1;
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	static boolean isAllEmpty() {
		for(int[] a: aArr) {
			if(a[0]!=-1) return false;
		}
		for(int[] b: bArr) {
			if(b[0]!=-1) return false;
		}
		return true;
	}
}

/*

1
1 1 2 1 1
5
7
7 10
#1 3


 */

