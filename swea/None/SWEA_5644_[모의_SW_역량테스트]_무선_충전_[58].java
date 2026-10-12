import java.util.*;
import java.io.*;
public class Solution {
	static int M, A;
	static int[] M1;
	static int[] M2;
	static List<int[]> ANumList;
	static int[] di = {0, 0, 1, 0, -1};
	static int[] dj = {0, -1, 0, 1, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			M1 = new int[M];
			M2 = new int[M];
			
			ANumList = new ArrayList<int[]>();
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			for(int i=0; i<M; i++) {
				M1[i] = Integer.parseInt(st1.nextToken());
				M2[i] = Integer.parseInt(st2.nextToken());
			}
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				int[] BCStatus = new int[4];
				for(int j=0; j<4; j++) {
					BCStatus[j] = Integer.parseInt(st.nextToken());
					
				}
				ANumList.add(BCStatus);
			}
			ANumList.sort((a1, b1)->{
				return b1[3]-a1[3];
			});
			/*
			 
			 여기까지가 
			 1. M에는 움직임의 회수 M1, M2의 배열의 사이즈 저장
			 2. A에는 BC의 개수 를 저장
			 3. M1에는 M1의 움직임을 저장
			 4. M2에는 M2의 움직임을 저장
			 5. ANumList에는 BC들의 Status를 int[]로 입력받아 .add로 저장 ANumList.get(i)에는 i번째 BC에 대한 status 정보가 들어가 있음
			 
			 추가적으로 방향 변수 인덱스가 5개 인 점도 고려해야함
			 
			 */
			int[] M1Point = {1,1};
			int[] M2Point = {10, 10};
			int sum = 0;
			
			
			int first = calOneTime(M1Point[0],M1Point[1], M2Point[0], M2Point[1]);
			
			sum+=first;

			for(int i=0; i<M; i++) {
				M1Point[0] += di[M1[i]];
				M1Point[1] += dj[M1[i]];
				M2Point[0] += di[M2[i]];
				M2Point[1] += dj[M2[i]];
				
				sum+=calOneTime(M1Point[0], M1Point[1], M2Point[0], M2Point[1]);
				
			}
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
	
	static int calOneTime(int y1, int x1, int y2, int x2) {
		boolean[] whatBCM1 = whatBCCanUse(y1, x1);
		boolean[] whatBCM2 = whatBCCanUse(y2, x2);
		Queue<Integer> BCM1q = new ArrayDeque<Integer>();
		Queue<Integer> BCM2q = new ArrayDeque<Integer>();
		for(int i=0; i<A; i++) {
			if(whatBCM1[i]) {
				BCM1q.offer(i);
			}
			if(whatBCM2[i]) {
				BCM2q.offer(i);
			}
		}
		
		if(BCM1q.size()==0&&BCM2q.size()==0) {return 0;}
		
		if(BCM1q.peek()==BCM2q.peek()) {
			if(BCM1q.size()==1 && BCM2q.size()==1) {
				return ANumList.get(BCM1q.poll())[3];
			}
			else if(BCM1q.size()>1&&BCM2q.size()==1) {
				BCM1q.poll();
				int sum = ANumList.get(BCM1q.poll())[3]+ANumList.get(BCM2q.poll())[3];
				return sum;
			}
			else if(BCM2q.size()>1&&BCM1q.size()==1) {
				BCM2q.poll();
				int sum = ANumList.get(BCM2q.poll())[3]+ANumList.get(BCM1q.poll())[3];
				return sum;
			}
			else {
				int firstM1 = ANumList.get(BCM1q.poll())[3];
				int secondM1 = ANumList.get(BCM1q.poll())[3];
				int firstM2 = ANumList.get(BCM2q.poll())[3];
				int secondM2 = ANumList.get(BCM2q.poll())[3];
				int sum = Math.max(firstM1+secondM2, firstM2+secondM1);
				return sum;
			}
		}
		else {
			int M1Val;
			if(BCM1q.peek()==null) {M1Val = 0;}
			else {M1Val = ANumList.get(BCM1q.poll())[3];}
			int M2Val;
			if(BCM2q.peek()==null) {M2Val = 0;}
			else {M2Val = ANumList.get(BCM2q.poll())[3];}
			return M1Val+M2Val;
		}
		
	}
	
	static boolean[] whatBCCanUse(int y, int x) {
		boolean[] returnUsable = new boolean[A];
		for(int i=0; i<A; i++) {
			int[] thisTimeBC = ANumList.get(i);
			int distance = Math.abs(y-thisTimeBC[0])+Math.abs(x-thisTimeBC[1]);
			if(distance<=thisTimeBC[2]) {returnUsable[i]=true;}
		}
		return returnUsable;
	}
	
}
