import java.util.*;
import java.io.*;
public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String hexLine = br.readLine();
			Set<String> numList = new HashSet<String>();
			Queue<String> cutLine = new ArrayDeque<String>();
			for(String str:hexLine.split("")) {
				cutLine.offer(str);
			}
			for(int i=0; i<N/4; i++) {
				for(int j=0; j<4; j++) {
					StringBuilder Inserted = new StringBuilder();
					for(int k=0; k<N/4; k++) {
						String poll = cutLine.poll();
						Inserted.append(poll);
						cutLine.offer(poll);
					}
					numList.add(Inserted.toString());	
				}
				cutLine.offer(cutLine.poll());
			}
			List<String> madeList = new ArrayList<String>(numList);
			String[] madeArray = madeList.toArray(new String[0]);
			Arrays.sort(madeArray, (s1, s2)->{
				return (Integer.parseInt(s1,16) - Integer.parseInt(s2,16))*-1;
			});
			sb.append("#").append(test_case).append(" ").append(Integer.parseInt(madeArray[K-1],16)).append("\n");
			
		}
		System.out.println(sb);
	}
}
