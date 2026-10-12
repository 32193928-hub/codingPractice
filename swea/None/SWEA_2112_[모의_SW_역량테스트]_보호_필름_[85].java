import java.util.*;
import java.io.*;

public class Solution{
	
	static int D, W, K;
	static int[][] film;
	static Queue<int[]> masks;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			film = new int[D][W];
			int[] masked = new int[D];
			masks = new ArrayDeque<int[]>();
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int answer = checking(K);
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
			
		}
		System.out.println(sb);
	}
	
	static void makeMask(int t, int idx, int cnt, int[] masked){
		if(idx==0&&cnt==0) {
			for(int i=0; i<masked.length; i++) {
				masked[i]=-1;
			}
		}
		if(idx==D||cnt==t) {
			if(cnt==t) {
				masks.offer(masked.clone());
			}
			return;
		}
		else {
			int[] newMasked1 = masked.clone();
			newMasked1[idx] = 0;
			
			int[] newMasked2 = masked.clone();
			newMasked2[idx] = 1;
			makeMask(t, idx+1, cnt, masked);
			makeMask(t, idx+1, cnt+1, newMasked1);
			makeMask(t, idx+1, cnt+1, newMasked2);
		}
	}
	
	static boolean check(int[] masking, int[][] film) {
	    if (K == 1) return true; 
	    for (int i = 0; i < W; i++) { 
	        boolean pass = false;
	        int cnt = 1;
	        int prevState = (masking[0] != -1) ? masking[0] : film[0][i];
	        for (int j = 1; j < D; j++) {
	            int State = (masking[j] != -1) ? masking[j] : film[j][i];
	            if (State == prevState) {
	                cnt++;
	            } else {
	                cnt = 1; 
	                prevState = State;
	            }
	            if (cnt >= K) {
	                pass = true;
	                break; 
	            }
	        }
	        if (!pass) return false;
	    }
	    return true;
	}
	
	static int checking(int K) {
		int answer = K;
		
		for(int i=0; i<=K; i++) {
			masks.clear();
			makeMask(i, 0, 0, new int[D]);
			while(!masks.isEmpty()) {
				int[] polledMask = masks.poll();
				if(check(polledMask, film)) {
					answer = i;
					return answer;
				}
			}
		}
		
		return answer;
	}
	
}
