import java.util.*;
import java.io.*;
public class Solution_1233 {
	static String[] operator = {"+","-","*","/"};
//	class Node<T>{
//		public T data;
//		public Integer leftLink;
//		public Integer rightLink;
//		Node(T data){
//			this.data = data;
//		}
//		Node(T data, int leftLink, int rightLink){
//			this.data = data;
//			this.leftLink = leftLink;
//			this.rightLink = rightLink;
//		}
//	}
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		for(int test_case=1; test_case<=10; test_case++) {//1.  트리 계산 불가능, 2. 있어도 피연산자 연산자 불균형시 계산 불가능
			int answer = 1;
			int N = Integer.parseInt(br.readLine());
//			Node[] nodeList = new Node[N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				List<String> input = new ArrayList<String>();
				while(st.hasMoreTokens()) {
					input.add(st.nextToken());
				}
				if(input.size()==3) {					//1.  트리 계산 불가능
					answer = 0;
//					break;
				}
				else if(input.size()==2) {
					boolean isOperator=false;
					for(String operator:operator) {
						if(operator.equals(input.get(1))) {
							isOperator = true;
							break;
						}
					}
					if(isOperator) {
						answer = 0;
//						break;
					}
				}
				else {
					boolean isOperator=false;
					for(String operator:operator) {
						if(operator.equals(input.get(1))) {
							isOperator = true;
							break;
						}
					}
					if(!isOperator) {
						answer = 0;
//						break;
					}
				}
				if(answer == 0) {
//					break;
				}
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
