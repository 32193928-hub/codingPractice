import java.util.*;

public class Solution1844 {
    public static int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        
        int[][] way = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        int answer = 0;
        
        while (!q.isEmpty()) {
            int size = q.size(); 
            answer++;
            
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int y = curr[0];
                int x = curr[1];
                
                if (y == n - 1 && x == m - 1) {
                    return answer;
                }
                
                for (int j = 0; j < 4; j++) {
                    int ny = y + way[j][0];
                    int nx = x + way[j][1];
                    
                    if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                    if (maps[ny][nx] == 0 || visited[ny][nx]) continue;
                    
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }
            }
        }
        
        return -1; 
    }

    public static void main(String[] args) {
        int[][] map = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println("정답은 " + solution(map)); 
    }
}