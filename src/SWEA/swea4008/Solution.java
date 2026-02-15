package SWEA.swea4008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numCount;
    static int[] nums;

    static int[] operatorCountList = new int[4];

    static final int PLUS = 0;
    static final int MINUS = 1;
    static final int TIMES = 2;
    static final int DIVIDE = 3;

    static int maxResult;
    static int minResult;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int numTestCase = Integer.parseInt(br.readLine().trim());
        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            initTestCase();
            makeResult(1, nums[0]);
            sb.append("#").append(testCaseNo).append(" ").append(maxResult - minResult).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        numCount = Integer.parseInt(br.readLine().trim());
        nums = new int[numCount];

        st = new StringTokenizer(br.readLine().trim());
        for(int operatorIdx = PLUS; operatorIdx <= DIVIDE; operatorIdx++) {
            operatorCountList[operatorIdx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());
        for(int numIdx = 0; numIdx < numCount; numIdx++) {
            nums[numIdx] = Integer.parseInt(st.nextToken());
        }

        maxResult = Integer.MIN_VALUE;
        minResult = Integer.MAX_VALUE;
    }

    static void makeResult(int numIdx, int result) {
        if(numIdx == numCount) {
            maxResult = Math.max(maxResult, result);
            minResult = Math.min(minResult, result);
            return;
        }

        for(int operatorIdx = PLUS; operatorIdx <= DIVIDE; operatorIdx++) {
            if(operatorCountList[operatorIdx] == 0) continue;

            operatorCountList[operatorIdx]--;
            if(operatorIdx == PLUS) {
                makeResult(numIdx + 1, result + nums[numIdx]);
            }
            else if(operatorIdx == MINUS) {
                makeResult(numIdx + 1, result - nums[numIdx]);
            }
            else if(operatorIdx == TIMES) {
                makeResult(numIdx + 1, result * nums[numIdx]);
            }
            else { // if(operatorIdx == DIVIDE)
                makeResult(numIdx + 1, result / nums[numIdx]);
            }
            operatorCountList[operatorIdx]++;
        }
    }
}
