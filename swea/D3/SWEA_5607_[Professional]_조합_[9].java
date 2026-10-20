/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5607 {
    private static int MAX_N = 1_000_000;
    private static long MOD = 1_234_567_891L;
    private static long[] FACTORIAL = new long[MAX_N + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        initializeFactorials();

        int testCaseCount = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= testCaseCount; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            long answer = combination(n, r);
            sb.append('#')
                    .append(testCase)
                    .append(' ')
                    .append(answer)
                    .append('\n');
        }

        System.out.print(sb);
    }

    private static void initializeFactorials() {
        FACTORIAL[0] = 1L;
        for (int i = 1; i <= MAX_N; i++) {
            FACTORIAL[i] = FACTORIAL[i - 1] * i % MOD;
        }
    }

    private static long combination(int n, int r) {
        long denominator = FACTORIAL[r] * FACTORIAL[n - r] % MOD;
        long denominatorInverse = modularPower(denominator, MOD - 2);

        return FACTORIAL[n] * denominatorInverse % MOD;
    }

    private static long modularPower(long base, long exponent) {
        long result = 1L;

        while (exponent > 0) {
            if ((exponent & 1L) == 1L) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }
}
*/
/*

20
1 0
1 1
2 1
4 2
5 0
5 5
5 1
5 4
10 3
10 7
20 10
100 2
100 98
1000 500
1000000 0
1000000 1
1000000 999999
1000000 1000000
1000000 500000
999999 499999

*/
import java.util.*;
import java.io.*;

public class Solution{
	static int N, R;
	static long[] Factorial = new long[1000001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		Factorial[0]=1L;
		for(int i=1; i<=1000000; i++) {
			Factorial[i]=Factorial[i-1]*i%1234567891L;
		}
		for(int test_case=1;test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			
			// Factorial[N]/(Factorial[R]*Factorial[N-R])%1234567891 => Factorial[N]*(Factorial[R]*Factorial[N-R])^(1234567891-2) % 1234567891
			long answer = Factorial[N]*modularPower(Factorial[N-R]*Factorial[R]%1234567891L, 1234567891L-2)%1234567891L;
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	static long modularPower(long base, long p) {
		long result = 1L;
		while(p>0) {
			if(p%2!=0) {
				result = result*base%1234567891L;
			}
			base = base*base%1234567891L;
			p/=2L;
		}
		return result;
	}
}