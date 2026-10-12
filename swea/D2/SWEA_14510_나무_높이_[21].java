public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("T입력"); //testcase
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int need_2 = 0;
			int need_1 = 0;
			
			int max = 0;
			for(int i:arr) {
				if(i>max) {max=i;}
			}
			for(int i=0;i<N; i++) {
				need_2 += (max-arr[i])/2;
				need_1 += (max-arr[i])%2;
			}
			while(need_2>need_1+1) {
				need_2 -= 1;
				need_1 += 2;
			}
			int answer = 0;
			if(need_1>need_2) {
				answer = need_1*2-1;
			} else{
				answer = need_2*2;
			}
			System.out.println("#"+test_case+" "+answer);
		}
	}