import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int maxLineLength;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];
            maxLineLength = 0;
            int maxHeight = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxHeight) {
                        visited[i][j] = true;
                        dfs(i, j, 1, false); 
                        visited[i][j] = false; 
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(maxLineLength).append("\n");
        }
        System.out.print(sb);
    }

    
    static void dfs(int y, int x, int length, boolean usedK) {
    	
        maxLineLength = Math.max(maxLineLength, length);

        for (int d = 0; d < 4; d++) {
            int ny = y + di[d];
            int nx = x + dj[d];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) {
                continue;
            }

            if (map[ny][nx] < map[y][x]) {
                visited[ny][nx] = true;
                dfs(ny, nx, length + 1, usedK);
                visited[ny][nx] = false; 
            }
            else if (!usedK && map[ny][nx] - K < map[y][x]) {
                int originalHeight = map[ny][nx];
                visited[ny][nx] = true;

                map[ny][nx] = map[y][x] - 1;
                dfs(ny, nx, length + 1, true);

                map[ny][nx] = originalHeight;
                visited[ny][nx] = false;
            }
        }
    }
}