package BOJ.boj2559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] degreeArr = new int[n];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < n; i++) {
            degreeArr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            sum += degreeArr[i];

            if(i >= k-1) {
                maxSum = Math.max(maxSum, sum);
                sum -= degreeArr[i - (k-1)];
            }
        }

        System.out.println(maxSum);
    }
}
