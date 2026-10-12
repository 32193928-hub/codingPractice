import java.util.*;
import java.io.*;
public class Solution_4130 {
	static int[][] mag;
	static int[] magIdx;
	static int K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			mag = new int[5][8];
			magIdx = new int[5];
			K = Integer.parseInt(br.readLine());
			for(int i=1; i<= 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					mag[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			System.out.println();
			for(int i=0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int way = Integer.parseInt(st.nextToken());
				turning(num, way);
				for(int magnum=1; magnum<=4; magnum++) {
					for(int j=0; j<8; j++) {
//						System.out.print(mag[magnum][findIdx(magnum, j)]+" ");//현재 회전상태 확인
					}
//					System.out.println();//회전상태 확인 줄바꿈
				}
			}
			int answer = 0;
			for(int i=1; i<=4; i++) {
				if(mag[i][magIdx[i]]==1) answer += (int)Math.pow(2, i-1);
//				System.out.println(answer); //점수 증가 확인
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	static void turning(int magnum, int way) {
		int basenum = magnum;
		int[] turnList = new int[5];
		turnList[magnum] = way;
		if(magnum>1) {
			for(int num=magnum-1; num>=1; num--) {
				int baseIdx = findIdx(basenum, -2);
				int leftIdx = findIdx(num, 2);
				if(mag[basenum][baseIdx]!=mag[num][leftIdx]) {
					turnList[num] = turnList[basenum]*-1;
					basenum = num;
				}else {
					break;
				}
			}
		}
		basenum = magnum;
		if(magnum<4) {
			for(int num=magnum+1; num<=4; num++) {
				int baseIdx = findIdx(basenum, 2);
				int rightIdx = findIdx(num, -2);
				if(mag[basenum][baseIdx]!=mag[num][rightIdx]) {
					turnList[num]=turnList[basenum]*-1;
					basenum = num;
				}else {
					break;
				}
			}
		}
		for(int i=1; i<5;i++) {
			magIdx[i]=findIdx(i, turnList[i]*-1);
//			System.out.print(turnList[i]+" ");//회전 처리 리스트 확인
		}
//		System.out.println("::");//회전 처리 리스트 줄바꿈
	}
	static int findIdx(int magnum, int way) {
		if(magIdx[magnum]+way>=0&&magIdx[magnum]+way<8) {
			return magIdx[magnum]+way;
		}
		else {
			return (magIdx[magnum]+way+8)%8;
		}
	}
}
/*
1
2
0 0 1 0 0 1 0 0
1 0 0 1 1 1 0 1
0 0 1 0 1 1 0 0
0 0 1 0 1 1 0 1
1 1
3 -1

1
2
1 0 0 1 0 0 0 0
0 1 1 1 1 1 1 1
0 1 0 1 0 0 1 0
0 1 0 0 1 1 0 1
3 1
1 1

*/