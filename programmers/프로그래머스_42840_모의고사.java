class Solution {
    public int[] solution(int[] answers) {
        int[] answer;
        int st1 = 0;
        int st2 = 0;
        int st3 = 0;
        for(int i=0; i<answers.length;i++){
            if(answers[i]==eachAnswer(i)[0]){st1++;}
            if(answers[i]==eachAnswer(i)[1]){st2++;}
            if(answers[i]==eachAnswer(i)[2]){st3++;}
        }
        if(st1>st2){
            if(st1>st3){
                answer = new int[] {1};
            }
            else if(st1==st3){
                answer = new int[] {1, 3};
            } else{ answer = new int[] {3};}
        } else if(st1==st2){
            if(st1>st3){
                answer = new int[] {1, 2};
            }
            else if(st1==st3){
                answer = new int[] {1, 2, 3};
            }
            else{
                answer = new int[] {3};
            }
        } else{
            if(st2>st3){
                answer = new int[] {2};
            } else if (st2==st3){
                answer = new int[] {2, 3};
            } else{
                answer = new int[] {3};
            }
        }
        return answer;
    }
    public int[] eachAnswer(int n){
        int stu1 = n%5+1;
        int stu2 = 0;
        int[] stu2List = {1, 3, 4, 5};
        if(n%2 == 0){
            stu2 = 2;
        } else{
            stu2 = stu2List[(n/2)%4];
        }
        int stu3 = 0;
        int[] stu3List = {3, 1, 2, 4, 5};
        stu3 = stu3List[(n/2)%5];
        int [] ret = {stu1, stu2, stu3};
        return ret;
    }
    
}