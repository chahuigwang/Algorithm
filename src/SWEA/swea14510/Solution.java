package SWEA.swea14510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA14510
 * @author chahuigwang
 *
 *  @see #main(String[])
 *  1. 테스트 케이스 개수를 입력받는다.
 *  2. 각 테스트 케이스마다,
 *
 *      @see #inputTestCase()
 *      2-1. highest를 0으로 초기화한다.
 *      2-2. 나무의 개수를 입력받는다.
 *      2-3. 각 나무의 높이를 저장할 배열을 생성하고, 각 나무의 높이를 입력받아 배열에 저장한다.
 *      2-4. 가장 키가 큰 나무의 높이를 highest에 저장한다.
 *
 *      2-5. +1 성장이 필요한 횟수와 +2 성장이 필요한 횟수를 계산한다.
 *      2-6. growTwoCount가 growOneCount보다 2 이상 크다면
 *          2-6-1. growTwoCount를 growOneCount 2개로 만든다.
 *
 *      2-7. 필요한 날짜(days)를 계산한다.
 *      2-8. 테스트 케이스 번호와 함께 days를 출력한다.
 */

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numTrees;
    static int[] treeHeights;

    static int highest;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int numTestCase = Integer.parseInt(br.readLine().trim());
        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            inputTestCase();

            int growOneCount = 0, growTwoCount = 0;

            int diff;
            for(int height : treeHeights) {
                diff = highest - height;
                growOneCount += diff % 2;
                growTwoCount += diff / 2;
            }

            while(growTwoCount > growOneCount + 1) {
                growTwoCount--;
                growOneCount += 2;
            }

            int days = (growOneCount > growTwoCount) ? 2 * growOneCount - 1 : 2 * growTwoCount;

            sb.append("#").append(testCaseNo).append(" ").append(days).append("\n");
        }
        System.out.println(sb);
    }

    static void inputTestCase() throws IOException {
        highest = 0;
        numTrees = Integer.parseInt(br.readLine().trim());

        treeHeights = new int[numTrees];
        st = new StringTokenizer(br.readLine().trim());
        for(int treeIdx = 0; treeIdx < numTrees; treeIdx++) {
            int curHeight = Integer.parseInt(st.nextToken());
            highest = Math.max(highest, curHeight);
            treeHeights[treeIdx] = curHeight;
        }
    }
}
