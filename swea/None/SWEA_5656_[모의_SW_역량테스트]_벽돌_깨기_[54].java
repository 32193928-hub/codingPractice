import java.io.*;
import java.util.*;

public class Solution{
    static int N, W, H;
    static int blockCnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] arr = new int[H][W];
            blockCnt = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, arr);
            sb.append("#").append(test_case).append(" ").append(blockCnt).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int n, int[][] currentArr) {
        if (n == N) {
            int answer = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (currentArr[i][j] != 0) {
                        answer++;
                    }
                }
            }
            blockCnt = Math.min(blockCnt, answer);
            return;
        }

        if (countBlocks(currentArr) == 0) {
            blockCnt = 0;
            return;
        }

        for (int col = 0; col < W; col++) {
            int[][] newArr = copyArr(currentArr);
            afterBreak(col, newArr);
            dfs(n + 1, newArr);
        }
    }

    static void afterBreak(int targetCol, int[][] map) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];

        int topRow = -1;
        for (int i = 0; i < H; i++) {
            if (map[i][targetCol] != 0) {
                topRow = i;
                break;
            }
        }

        if (topRow == -1) return;

        q.offer(new int[]{topRow, targetCol, map[topRow][targetCol]});
        visited[topRow][targetCol] = true;
        map[topRow][targetCol] = 0; 

        while (!q.isEmpty()) {
            int[] point = q.poll();
            int r = point[0];
            int c = point[1];
            int power = point[2];

            for (int d = 0; d < 4; d++) {
                for (int p = 1; p < power; p++) {
                    int nr = r + dx[d] * p;
                    int nc = c + dy[d] * p;

                    if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
                        
                        if (!visited[nr][nc] && map[nr][nc] > 0) {
                            visited[nr][nc] = true;
                            q.offer(new int[]{nr, nc, map[nr][nc]});
                            map[nr][nc] = 0; 
                        }
                    }
                }
            }
        }

        
        shiftDownAll(map);
    }

    
    static void shiftDownAll(int[][] map) {
        for (int c = 0; c < W; c++) {
            int bottom = H - 1;
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] > 0) {
                    int val = map[r][c];
                    map[r][c] = 0;
                    map[bottom][c] = val;
                    bottom--;
                }
            }
        }
    }

    static int countBlocks(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0) cnt++;
            }
        }
        return cnt;
    }

    static int[][] copyArr(int[][] origin) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) {
            copy[i] = origin[i].clone();
        }
        return copy;
    }
}