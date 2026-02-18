package BOJ.boj1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numCount;
    static int[] nums;

    static int targetCount;
    static int[] targets;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        printAnswer();
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        numCount = Integer.parseInt(br.readLine().trim());
        nums = new int[numCount];

        st = new StringTokenizer(br.readLine().trim());
        for(int numIdx = 0; numIdx < numCount; numIdx++) {
            nums[numIdx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        targetCount = Integer.parseInt(br.readLine().trim());
        targets = new int[targetCount];

        st = new StringTokenizer(br.readLine().trim());
        for(int targetIdx = 0; targetIdx < targetCount; targetIdx++) {
            targets[targetIdx] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for(int target : targets) {
            binarySearch(target);
        }
    }

    static void binarySearch(int target) {
        int start = 0;
        int end = numCount - 1;

        int mid;
        while(start <= end) {
            mid = (start + end) / 2;

            if(nums[mid] == target) {
                sb.append(1).append("\n");
                return;
            }
            else if(nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }

        sb.append(0).append("\n");
    }

    static void printAnswer() {
        System.out.println(sb);
    }
}
