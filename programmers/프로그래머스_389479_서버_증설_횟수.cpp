#include <string>
#include <vector>

using namespace std;

int solution(vector<int> players, int m, int k) {
    
    vector<int> player = players;
    int m_size = m;
    int k_size = k;
    
    int answer = 0;
    int server_size = 0;
    vector<int> server_ontime(k_size);
    
    for(int p_size : player){
        server_size -= server_ontime[0];
        server_ontime.erase(server_ontime.begin());
        
        int needed = p_size /m;
        if (needed > server_size) {
            int plus_server_size = needed - server_size; 
            answer += plus_server_size;
            server_size += plus_server_size;
            server_ontime.push_back(plus_server_size); 
        } else {
            
            server_ontime.push_back(0);
        }
    }
    return answer;
}