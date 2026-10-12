import java.util.*;
import java.io.*;
public class Solution {
	static Queue<char[]> lineUp; 
	static char[] friends = {'A','C','F','J','M','N','R','T'};
	static char[] friendsLine;
	
	public static int solution(int n, String[] data) {
		int answer = 0;
		
		lineUp = new ArrayDeque<char[]>();
		Queue<char[]> saved = new ArrayDeque<char[]>();
		friendsLine = new char[8];
		
		allLine(0, 0);
		for(int i=0; i<data.length; i++) {

			char friend1 = data[i].charAt(0);
			char friend2 = data[i].charAt(2);
			char operator = data[i].charAt(3);
			int condition = data[i].charAt(4)-'0';
			
			char[] polledLine;
			
			if(lineUp.isEmpty()) {answer = 0; break;}
			
			while(!lineUp.isEmpty()) {
				polledLine = lineUp.poll();
				int idx1 = getIndex(polledLine, friend1);
				int idx2 = getIndex(polledLine, friend2);
				if(filter(idx1, idx2, operator, condition)) {
					saved.offer(polledLine);
				}
			}
			lineUp = new ArrayDeque<char[]>(saved);
			saved.clear();
		}
		
		answer = lineUp.size();
		
		return answer;
	}
	
	static void allLine(int cnt, int flags) {
		if(cnt==8) {
			lineUp.offer(friendsLine.clone());
			return;
		}
		for(int i=0; i<8; i++) {
			if((flags&1<<i)==0) {
				friendsLine[cnt]=friends[i];
				allLine(cnt+1, flags|1<<i);
			}
		}
	}
	
	static boolean filter(int idx1, int idx2, char operator, int condition) {
		int term = Math.abs(idx1-idx2)-1;
		if(operator=='=') {return term==condition;}
		else if(operator=='<') {return term<condition;}
		else if(operator=='>'){return term>condition;}
		else {return false;}
	}
	
	static int getIndex(char[] line, char a) {
		for(int i=0; i<line.length; i++) {
			if(line[i]==a) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException{
		int answer = solution(2, new String[] {"N~F=0","R~T>2"});
		System.out.println(answer);
	}
}
