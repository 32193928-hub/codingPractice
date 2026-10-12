import java.util.*;

class Solution {
    public static String solution(int[] numbers) {
        String answer = "";
        String[] strNumbers = new String[numbers.length];
        for(int i=0; i<numbers.length; i++) {
        	strNumbers[i] = String.valueOf(numbers[i]);
        }
        System.out.println();
        Arrays.sort(strNumbers, (s1, s2) -> (s2+s1).compareTo(s1+s2));
        if(strNumbers[0].equals("0")) {return "0";}
        StringBuilder sb = new StringBuilder();
        for(String str:strNumbers) {
        	sb.append(str);
        }
        answer = sb.toString();
        return answer;
    }
}