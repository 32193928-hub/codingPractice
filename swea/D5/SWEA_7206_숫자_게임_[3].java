import java.util.*;
import java.io.*;

public class Solution {
	static int maxTurn;
	static Map<Integer, Integer> memo = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			int startNum = Integer.parseInt(br.readLine());
			maxTurn = dfs(startNum);
			
			
			sb.append("#").append(test_case).append(" ").append(maxTurn).append("\n");
		}
		System.out.println(sb);
	}

	static int dfs(int n) {
	    if (n < 10) {
	        return 0;
	    }

	    Integer cached = memo.get(n);
	    if (cached != null) {
	        return cached;
	    }

	    List<Integer> divideList = new ArrayList<>();
	    devide(String.valueOf(n).split(""),
	           new ArrayList<Integer>(), 0, 1, divideList);

	    int best = 0;
	    for (int next : divideList) {
	        best = Math.max(best, 1 + dfs(next));
	    }

	    memo.put(n, best);
	    return best;
	}

	static void devide(String[] Num, List<Integer> numList, int i, int j, List<Integer> DevideList) {
		if(Num.length==0) {return;}
		if(j == Num.length) {
			String num = "";
			for(int idx = i; idx<j; idx++) {
				num+=Num[idx];
			}
			numList.add(Integer.parseInt(num));
			if(numList.size()==1) {return;}
			int intNum = 1;
			for(int number:numList) {
				intNum = intNum*number;
			}
			DevideList.add(intNum);
		}
		else {
			List<Integer> newNumList = new ArrayList<Integer>(numList);
			String num = "";
			for(int idx=i; idx<j; idx++) {
				num+=Num[idx];
			}
			newNumList.add(Integer.parseInt(num));
			devide(Num, newNumList, j, j+1, DevideList);
			devide(Num, numList, i, j+1, DevideList);
		}
	}
}