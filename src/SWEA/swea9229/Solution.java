package SWEA.swea9229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine().trim());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] weight = new int[n];
            st = new StringTokenizer(br.readLine().trim());
            for(int idx = 0; idx < n; idx++) {
                weight[idx] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(weight);

            int s = 0;
            int e = n-1;
            int sum = 0;
            int max = -1;
            while(s < e) {
                sum = weight[s] + weight[e];
                if(sum == m) {
                    max = sum;
                    break;
                }
                else if(sum < m) {
                    max = Math.max(sum, max);
                    s++;
                }
                else {
                    e--;
                }
            }

            sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ").append(max);
            System.out.println(sb.toString());
        }
    }

}
