package BOJ.boj1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int haveLanCount;
    static int needLanCount;

    static int[] haveLanLengthList;

    static int maxLen;

    public static void main(String[] args) throws IOException {
        init();
        binarySearch();
        System.out.println(maxLen);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        haveLanCount = Integer.parseInt(st.nextToken());
        needLanCount = Integer.parseInt(st.nextToken());

        haveLanLengthList = new int[haveLanCount];
        for(int haveLanIdx = 0; haveLanIdx < haveLanCount; haveLanIdx++) {
            haveLanLengthList[haveLanIdx] = Integer.parseInt(br.readLine().trim());
        }

        maxLen = 0;
    }

    static boolean canSplit(long len) {
        long count = 0;
        for(int haveLanLength : haveLanLengthList) {
            count += haveLanLength / len;
            if(count >= needLanCount) return true;
        }
        return false;
    }

    static void binarySearch() {
        long start = 1, end = 0;

        for(int haveLanLength : haveLanLengthList) {
            end = Math.max(end, haveLanLength);
        }

        long mid;
        while(start <= end) {
            mid = (start + end) / 2;

            if(canSplit(mid)) { // mid의 길이로 자를 수 있다면 -> 길이를 더 늘려보자.
                maxLen = (int) mid;
                start = mid + 1;
            }
            else end = mid - 1; // mid의 길이로 자를 수 없다면 -> 길이를 줄여보자
        }
    }
}
