class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        for(int i=1; i<=yellow; i++) {
        	if(!(yellow%i==0)) {continue;}
        	else {
        		int j=yellow/i;
        		int bI = i+2;
        		int bJ = j+2;
        		if(((bI*2)+(bJ*2)-4)==brown) {
        			if(i>=j) {
        				answer = new int[] {bI,bJ};        				
        			}
        			else {
        				answer = new int[] {bJ,bI};
        			}
        		} else {continue;}
        	}
        }
        return answer;
    }
}