import java.util.*;

public class Main_60062 {
    static int answer;
    static int[] weakExtended;
    static boolean[] visited;
    static int[] pDist; 

    public static int solution(int n, int[] weak, int[] dist) {
        int wLen = weak.length;
        answer = dist.length + 1; 

        weakExtended = new int[wLen * 2];
        for (int i = 0; i < wLen; i++) {
            weakExtended[i] = weak[i];
            weakExtended[i + wLen] = weak[i] + n;
        }

        visited = new boolean[dist.length];
        pDist = new int[dist.length];
        
        permute(0, dist, wLen);

        return (answer > dist.length) ? -1 : answer;
    }

    private static void permute(int depth, int[] dist, int wLen) {
        if (depth == dist.length) {
            check(wLen); 
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                pDist[depth] = dist[i];
                permute(depth + 1, dist, wLen);
                visited[i] = false; 
            }
        }
    }

    private static void check(int wLen) {
        for (int start = 0; start < wLen; start++) {
            int friendCount = 1; 
            int coverage = weakExtended[start] + pDist[friendCount - 1];

            for (int idx = start; idx < start + wLen; idx++) {
                if (weakExtended[idx] > coverage) {
                    friendCount++; 

                    if (friendCount > pDist.length) {
                        break;
                    }

                    coverage = weakExtended[idx] + pDist[friendCount - 1];
                }
            }

            answer = Math.min(answer, friendCount);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(12, new int[] {1, 5, 6, 10}, new int[] {1, 2, 3, 4}));
    }
}