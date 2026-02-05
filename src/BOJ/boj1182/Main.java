package BOJ.boj1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int numCount;
    static int target;

    static int[] arr;
    static boolean[] selected;

    static int count = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        numCount = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        arr = new int[numCount];
        st = new StringTokenizer(br.readLine().trim());
        for(int idx = 0; idx < numCount; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        selected = new boolean[numCount];

        subset(0, 0, 0);
        System.out.println(count);
    }

    static void subset(int idx, int sum, int selectedCount) {
        if(idx == numCount) {
            if(selectedCount > 0 && sum == target) count++;
            return;
        }

        subset(idx + 1, sum + arr[idx], selectedCount + 1);
        subset(idx + 1, sum, selectedCount);
    }
}
