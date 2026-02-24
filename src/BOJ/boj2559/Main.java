package BOJ.boj2559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int[] temperatures;
    static int dayCount;
    static int windowSize;

    static int maxSum;

    public static void main(String[] args) throws IOException {
        init();
        slidingWindow();
        System.out.println(maxSum);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        dayCount = Integer.parseInt(st.nextToken());
        windowSize = Integer.parseInt(st.nextToken());

        temperatures = new int[dayCount];
        st = new StringTokenizer(br.readLine().trim());
        for(int dayIdx = 0; dayIdx < dayCount; dayIdx++) {
            temperatures[dayIdx] = Integer.parseInt(st.nextToken());
        }

        maxSum = 0;
    }

    static void slidingWindow() {
        int windowSum = 0;
        for(int dayIdx = 0; dayIdx < windowSize; dayIdx++) {
            windowSum += temperatures[dayIdx];
        }

        maxSum = windowSum;
        for(int dayIdx = windowSize; dayIdx < dayCount; dayIdx++) {
            windowSum += temperatures[dayIdx];
            windowSum -= temperatures[dayIdx - windowSize];
            maxSum = Math.max(maxSum, windowSum);
        }
    }
}
