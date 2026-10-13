import java.util.*;

public class Main_72413 {
	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] cost = new int[n + 1][n + 1];
		int INF = 100000000;

		for(int i=1; i<=n; i++) {
			Arrays.fill(cost[i], INF);
			cost[i][i] = 0;
		}

		for(int i=0; i<fares.length; i++) {
			int start = fares[i][0];
			int end = fares[i][1];
			int fare = fares[i][2];
			cost[start][end] = Math.min(cost[start][end], fare);
			cost[end][start] = Math.min(cost[end][start], fare);
		}

		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
				}
			}
		}

		int answer = INF;
		for(int i=1; i<=n; i++) {
			answer = Math.min(answer, cost[s][i] + cost[i][a] + cost[i][b]);
		}

		return answer;
	}

	public static void main(String[] args) {
		int[][] fares = {
				{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41},
				{5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22},
				{1, 6, 25}
		};
		System.out.println(solution(6, 4, 6, 2, fares));
	}
}
