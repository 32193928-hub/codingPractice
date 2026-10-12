import java.util.*;
class Solution{
	static Set<Integer> setNumber = new HashSet<>();
    
    public static int solution(String numbers) {
    	boolean[] visited = new boolean[numbers.length()];
        make2("",numbers,visited);
        int answer = 0;
        for(int num: setNumber) {
            System.out.println(num);
        	if(isPrime(num)) {answer++;}
        }
        return answer;
    }

    public static void make2(String make, String numbers, boolean[] visited) {
    	if(make!="") {
    		setNumber.add(Integer.parseInt(make));
    	}
    	for(int i=0; i<numbers.length(); i++) {
    		if(!visited[i]) {
    			visited[i]=true;
    			make2(make+numbers.charAt(i), numbers, visited);
    			visited[i]=false;
    		}
    	}
    }
    
    public static boolean isPrime(int a) {
    	if(a<2) {return false;}
    	else {
    		for(int i=2; i<=Math.sqrt(a);i++) {
    			if(a%i==0) {return false;}
    		}
    	}
    	return true;
    }
}