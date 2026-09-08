import java.util.*;
import java.io.*;

public class Solution {
    static int N, M, ans;
    static int[] bad; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            bad = new int[N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                
                bad[u] |= (1 << v);
                bad[v] |= (1 << u);
            }

            ans = 0;
            dfs(1, 0);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

    
    static void dfs(int idx, int mask) {
        if (idx > N) {
            ans++;
            return;
        }

       
        dfs(idx + 1, mask);

        
        if ((mask & bad[idx]) == 0) {
            dfs(idx + 1, mask | (1 << idx));
        }
    }
}