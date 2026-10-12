class Solution {
    static int[] arr;
	static int resultVal;
    
    public int solution(int[] number) {
        resultVal = 0;
        arr = number;
        nCr(0,0,0);
        return resultVal;
    }
    
    static void nCr(int sum, int start, int count) { //count가 없을 시 3개 이상의 합도 계산하기 때문에 count가 있어야 함
		if(sum==0&&start!=0&&count==3) {			//sum이 0이고, 시작할때의 0값도 아니고, 총 3번 안에 완수가 되었을때
			resultVal++;
			return;
		}
		if(start>=arr.length||count>3) {return;}	//만약 index가 범위를 초과했을때, 혹은 3개 이상을 이미 더한 상태일때, 나가리
		else {										//아니라면 아래의 재귀 호출
			nCr(sum+arr[start], start+1, count+1);
			nCr(sum, start+1, count);
		}
	}
}