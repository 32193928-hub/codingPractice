import java.io.*;
import java.util.*;

public class Solution{
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int OFFSET = 350;
            boolean[][] visited = new boolean[700][700];

            Queue<int[]> q = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life > 0) {
                        int r = OFFSET + i;
                        int c = OFFSET + j;
                        visited[r][c] = true;
                        q.offer(new int[]{r, c, life, life, 0});
                    }
                }
            }

            for (int t = 0; t < K; t++) {
                int size = q.size();
                PriorityQueue<int[]> breedCandidates = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));

                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    int r = cur[0], c = cur[1], life = cur[2], time = cur[3], state = cur[4];

                    if (state == 0) { 
                        time--;
                        if (time == 0) {
                            q.offer(new int[]{r, c, life, life, 1}); 
                        } else {
                            q.offer(new int[]{r, c, life, time, 0});
                        }
                    } else { 
                        if (time == life) {
                            breedCandidates.offer(cur);
                        }

                        time--;
                        
                        if (time > 0) {
                            q.offer(new int[]{r, c, life, time, 1});
                        }
                    }
                }

                while (!breedCandidates.isEmpty()) {
                    int[] parent = breedCandidates.poll();
                    for (int d = 0; d < 4; d++) {
                        int nr = parent[0] + di[d];
                        int nc = parent[1] + dj[d];

                        if (!visited[nr][nc]) {
                            visited[nr][nc] = true; 
                            q.offer(new int[]{nr, nc, parent[2], parent[2], 0});
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(q.size()).append("\n");
        }
        System.out.print(sb);
    }
}