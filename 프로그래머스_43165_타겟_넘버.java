import java.util.*;
class Solution {
    public int solution(int[] numbers, int target) {
		int answer = 0;
		Queue<Integer> q = new LinkedList<>();
		for(int num:numbers) {
			if(q.isEmpty()) {
				q.offer(num);
				q.offer(-1*num);
			}
			else {
				int cnt = q.size();
				for(int i=0;i<cnt;i++) {
					int item=q.poll();
					q.offer(item+num);
					q.offer(item-num);
				}
			}
		}
		while(!q.isEmpty()) {
			if(q.poll()==target) {
				answer++;
			}
		}
		return answer;
	}
}