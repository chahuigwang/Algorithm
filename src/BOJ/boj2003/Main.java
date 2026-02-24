package BOJ.boj2003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0; // inclusive
        int end = 0; // exclusive
        int sum = 0; // start ~ end-1 까지의 합
        int count = 0;

        while(true) {
            if(sum >= target) {
                if(sum == target) count++;
                sum -= sequence[start++]; // start를 오른쪽으로 한칸 이동
            } else if (end == n) { // sum < target 이므로 end를 오른쪽으로 한칸 이동해야 하지만, end가 이미 오른쪽 끝인 경우
                break;
            } else {
                sum += sequence[end++]; // end를 오른쪽으로 한칸 이동
            }
        }

        System.out.println(count);
    }
}
