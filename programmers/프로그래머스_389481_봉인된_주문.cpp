#include <string>
#include <vector>
#include <iostream>
#include <math.h>
#include <algorithm>

using namespace std;

vector <long long> str_to_int(vector<string> str){
    vector<long long>bans_int;
    
    for (string bn : str){
        long long bn_to_int=0;
        int th=bn.size()-1;
        for (int i =0; (int)i<(int)bn.size();i++){
            long long times = pow(26, th);
            bn_to_int+=((long long)(bn[i])-96)*((long long)(times));
            th--;
        }
        bans_int.push_back(bn_to_int);
    }

    return bans_int;
}

string solution(long long n, vector <string> bans){

    vector <long long> bans_int = str_to_int(bans);

    long long n_use = n;

    sort(bans_int.begin(), bans_int.end());
    for (long long ban_element: bans_int){
        if(ban_element>n_use) break;
        else n_use++;
    }

    vector <long> int_to_char;
    
    while(true){
        long long quotient = n_use / 26;
        long long remainder = n_use % 26;

        if(remainder==0){
            quotient--;
            remainder +=26;
        }

        if (quotient <= 26&&quotient!=0){
            int_to_char.push_back(remainder);
            int_to_char.push_back(quotient);
            break;
        } else{
            int_to_char.push_back(remainder);
            if(quotient==0){
                break;
            }
            n_use = quotient;
        }
    }

    vector <char> str;
    for (int j=int_to_char.size()-1;j>=0;j--){

        long el_index = long(int_to_char[j])+96;
        str.push_back((char)(el_index));

    }

    string answer(str.begin(),str.end());
    return answer;
}
