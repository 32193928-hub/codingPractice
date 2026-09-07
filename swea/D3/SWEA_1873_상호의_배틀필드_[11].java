import java.util.*;
import java.io.*;

public class Solution{
	static int H;
	static int W;
	static String[] directionToken = {"<", "^", "v", ">"};
	static int[] di = {0, -1, 1, 0};
	static int[] dj = {-1, 0, 0, 1};
	static int[] nowPoint;
	static int dir;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			String[][] arr = new String[H][W];
			for(int i=0; i<H; i++) {
				arr[i] = br.readLine().split("");
			}
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					for(int k=0; k<4; k++) {
						if(arr[i][j].equals(directionToken[k])) {nowPoint = new int[] {i, j}; dir=k;}
					}
				}
			}
			int N = Integer.parseInt(br.readLine());
			String[] commands = br.readLine().split("");
			for(String command:commands) {
				if(command.equals("S")) {
					arr = shoot(arr);
				}else {
					arr = move(arr, command);
				}
			}
			String[][] retString = new String[0][0];
			sb.append("#").append(test_case).append(" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					sb.append(arr[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
		
	}
	
	static String[][] move(String[][] map, String command){
		String[][] newMap = new String[map.length][];
		for(int i=0; i<map.length; i++) {
			newMap[i] = map[i].clone();
		}
		switch (command) {
		case "U":
			dir = 1;
			break;
		case "D":
			dir = 2;
			break;
		case "L":
			dir = 0;
			break;
		case "R":
			dir = 3;
			break;
		default:
			break;
		}
		newMap[nowPoint[0]][nowPoint[1]] = directionToken[dir];
		int[] newPoint = {nowPoint[0]+di[dir], nowPoint[1]+dj[dir]};
		if(canGo(newMap, newPoint)) {
			newMap[nowPoint[0]][nowPoint[1]] = ".";
			nowPoint = newPoint;
			newMap[nowPoint[0]][nowPoint[1]] = directionToken[dir];
		}
		return newMap;
	}
	
	
	static boolean canGo(String[][] arr, int[] point) {
		if(point[0]>=0&&point[0]<H) {
			if(point[1]>=0&&point[1]<W) {
				if(arr[point[0]][point[1]].equals(".")) {return true;}
			}
		}
		return false;
	}
	static String[][] shoot(String[][] map){
		String[][] newMap = new String[map.length][];
		for(int i=0; i<map.length; i++) {
			newMap[i] = map[i].clone();
		}
		switch (dir) {
		case 0:
			for(int j=nowPoint[1]; j>=0; j--) {
				if(newMap[nowPoint[0]][j].equals("#")) {break;}
				if(newMap[nowPoint[0]][j].equals("*")) {newMap[nowPoint[0]][j]="."; break;}
			}
			break;
		case 1:
			for(int j=nowPoint[0]; j>=0; j--) {
				if(newMap[j][nowPoint[1]].equals("#")) {break;}
				if(newMap[j][nowPoint[1]].equals("*")) {newMap[j][nowPoint[1]]="."; break;}
			}
			break;
		case 2:
			for(int j=nowPoint[0]; j<H; j++) {
				if(newMap[j][nowPoint[1]].equals("#")) {break;}
				if(newMap[j][nowPoint[1]].equals("*")) {newMap[j][nowPoint[1]]="."; break;}
			}
			break;
		case 3:
			for(int j=nowPoint[1]; j<W; j++) {
				if(newMap[nowPoint[0]][j].equals("#")) {break;}
				if(newMap[nowPoint[0]][j].equals("*")) {newMap[nowPoint[0]][j]="."; break;}
			}
			break;
		default:
			break;
		}
		return newMap;
	}
}
