import java.util.*;
import java.io.*;

public class Solution{
    static int N;
    static int[][] arr;
    static int[][][] wormhole;
    static int[] wormholeCount;

    // 0: 상, 1: 하, 2: 좌, 3: 우
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static int startY, startX;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            line = br.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = br.readLine();
            }
            if (line == null) break;

            N = Integer.parseInt(line.trim());
            arr = new int[N][N];
            wormhole = new int[11][2][2];
            wormholeCount = new int[11];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (6 <= arr[i][j] && arr[i][j] <= 10) {
                        int num = arr[i][j];
                        int idx = wormholeCount[num]++;
                        wormhole[num][idx][0] = i;
                        wormhole[num][idx][1] = j;
                    }
                }
            }

            int maxScore = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 0) {
                        for (int dir = 0; dir < 4; dir++) {
                            int thisScore = start(i, j, dir);
                            maxScore = Math.max(maxScore, thisScore);
                        }
                    }
                }
            }
            sb.append("#").append(test_case).append(" ").append(maxScore).append("\n");
        }
        System.out.print(sb);
    }

    static int start(int y, int x, int dir) {
        startY = y;
        startX = x;
        return move(y, x, dir);
    }

    static int move(int y, int x, int dir) {
        int dy = y;
        int dx = x;
        int nowDir = dir;
        int nowScore = 0;

        while (true) {
            dy += di[nowDir];
            dx += dj[nowDir];

            // 경계 내부인 경우
            if (0 <= dy && dy < N && 0 <= dx && dx < N) {
                // 시작점 복귀 또는 블랙홀 탐지시 종료
                if ((dy == startY && dx == startX) || arr[dy][dx] == -1) {
                    break;
                }
                // 웜홀 이동
                else if (6 <= arr[dy][dx] && arr[dy][dx] <= 10) {
                    int[] wormholeOutPoint = findWormhole(dy, dx, arr[dy][dx]);
                    dy = wormholeOutPoint[0];
                    dx = wormholeOutPoint[1];
                }
                // 블록 충돌
                else if (1 <= arr[dy][dx] && arr[dy][dx] <= 5) {
                    nowDir = getNextDir(nowDir, arr[dy][dx]);
                    nowScore++;
                }
            }
            // 벽 충돌 처리
            else {
                nowDir = getNextDir(nowDir, 5); // 벽은 5번 블록과 동일하게 반사
                nowScore++;

                if (dy == startY && dx == startX) {
                    break;
                }
            }
        }
        return nowScore;
    }

    static int[] findWormhole(int y, int x, int wormholeNum) {
        if (wormhole[wormholeNum][0][0] == y && wormhole[wormholeNum][0][1] == x) {
            return wormhole[wormholeNum][1];
        } else {
            return wormhole[wormholeNum][0];
        }
    }

    static int getNextDir(int dir, int blockType) {
        switch (blockType) {
            case 1:
                if (dir == 0) return 1;
                if (dir == 1) return 3;
                if (dir == 2) return 0;
                if (dir == 3) return 2;
                break;
            case 2:
                if (dir == 0) return 3;
                if (dir == 1) return 0;
                if (dir == 2) return 1;
                if (dir == 3) return 2;
                break;
            case 3:
                if (dir == 0) return 2;
                if (dir == 1) return 0;
                if (dir == 2) return 3;
                if (dir == 3) return 1;
                break;
            case 4:
                if (dir == 0) return 1;
                if (dir == 1) return 2;
                if (dir == 2) return 3;
                if (dir == 3) return 0;
                break;
            case 5:
                if (dir == 0) return 1;
                if (dir == 1) return 0;
                if (dir == 2) return 3;
                if (dir == 3) return 2;
                break;
        }
        return dir;
    }
}