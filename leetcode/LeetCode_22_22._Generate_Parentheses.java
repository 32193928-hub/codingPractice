import java.util.*;
public class Solution {
	static List<String> answer = new ArrayList<String>();
	public static List<String> generateParenthesis(int n){
		answer.clear();
        make("",0,0,n);
		return answer;
	}
	public static void make(String seq,int open,int close, int n) {
		if(open<close) {
			return;
		} else if(open==close) {
			if(open==n) {
				answer.add(seq);
			}
			else {
				seq+="(";
				make(seq, open+1, close, n);
			}
		}else {
			if(open==n) {
				seq+=")";
				make(seq,open,close+1,n);
			}else {
				make(seq+"(",open+1,close,n);
				make(seq+")",open,close+1,n);
			}
		}
	}
}
