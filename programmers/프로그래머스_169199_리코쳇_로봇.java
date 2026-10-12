import java.util.*;
import java.io.*;
public class Main_169199 {
	static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    static String[] staticBoard;
    static int iSize, jSize;
    public static void main(String[] args) {
		String[] board = {"...D..R",".D.G...","....D.D","D....D.","..D...."};
		System.out.println(solution(board));
	}
    public static int solution(String[] board) {
        int answer = 0;
        staticBoard = new String[board.length];
        for(int i=0; i<board.length; i++){
            staticBoard[i] = board[i];
        }
        iSize = board.length;
        jSize = board[0].length();
        int[] goalPoint = new int[2];
        int[] startPoint = new int[2];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length(); j++){
                if(board[i].charAt(j)=='G') goalPoint = new int[] {i, j};
                if(board[i].charAt(j)=='R') startPoint = new int[] {i,j};
            }
        }
        
        boolean canArrived=false;
        
        for(int dir=0; dir<4; dir++){
            int aroundIPoint = goalPoint[0]+di[dir];
            int aroundJPoint = goalPoint[1]+dj[dir];
            if((aroundIPoint<0||aroundIPoint>=iSize)||(aroundJPoint<0||aroundJPoint>=jSize)) canArrived = true;
            else{
                if(board[aroundIPoint].charAt(aroundJPoint)=='D') canArrived = true;
            }
        }
        
        if(!canArrived) return -1;
        
        Queue<int[]> queue = new ArrayDeque<int[]>();
        boolean[][] visited = new boolean[iSize][jSize];
        
        queue.offer(startPoint);
        visited[startPoint[0]][startPoint[1]]=true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int[] thisPoint = queue.poll();
                if(thisPoint[0]==goalPoint[0]&&thisPoint[1]==goalPoint[1]){
                	return answer;
                }
                for(int dir=0; dir<4; dir++){

                    int ni = thisPoint[0];
                    int nj = thisPoint[1];

                    while (isin(ni + di[dir], nj + dj[dir]) && staticBoard[ni + di[dir]].charAt(nj + dj[dir]) != 'D') {
                        ni += di[dir];
                        nj += dj[dir];
                    }
                    
                    if (!visited[ni][nj]) {
                        visited[ni][nj] = true;
                        queue.offer(new int[] {ni, nj});
                    }
                }
            }
            answer++;
        }
        
        return -1;
    }
    static boolean[] canGo(int[] point){
        boolean[] way = new boolean[4];
        for(int dir=0; dir<4; dir++){
            int newI = point[0]+di[dir];
            int newJ = point[1]+dj[dir];
            if(isin(newI, newJ)){
                if(staticBoard[newI].charAt(newJ)!='D'){
                    way[dir]=true;
                }
            }
        }
        return way;
    }
    static boolean isin(int i, int j){
        if((iSize>i&&i>=0)&&(jSize>j&&j>=0)) return true;
        return false;
    }
}
