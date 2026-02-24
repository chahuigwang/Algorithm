package BOJ.boj3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sequence);

        st = new StringTokenizer(br.readLine().trim());
        int target = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = n-1;
        int sum = 0;
        int count = 0;

        while(start < end) {
            sum = sequence[start] + sequence[end];
            if(sum == target) {
                count++;
                start++;
                end--;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(count);
    }
}
