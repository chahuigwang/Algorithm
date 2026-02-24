package SWEA.swea25756;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 25756. 진우의 데이터 분석
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *
 *  @see #initTestCase()
 *  2-1. 수익 데이터 개수와, 광고 기간을 입력받는다.
 *  2-2. 수익 데이터를 저장할 배열을 생성하고, 데이터를 입력받아 배열에 저장한다.
 *  2-3. 얻을 수 있는 최대 수익을 초기화한다.
 *  2-4. 각 일자부터 광고 기간(adDays)동안 광고했을 때 얻는 수익을 저장할 배열(windowSums)을 생성한다.
 *
 *  @see #calculateWindowSums()
 *  2-5. 슬라이딩 윈도우 기법을 활용해 각 일자부터 adDays만큼 광고했을 때 얻는 수익을 구하고 배열에 저장한다.
 *      2-5-1. 첫 번째 구간합(0 ~ adDays-1 까지 수익의 합)을 계산한다.
 *      2-5-2. 첫 번째 구간합을 windowSums[0]에 저장한다.
 *      2-5-3. 윈도우를 오른쪽으로 한 칸씩 이동시키며(profits의 오른쪽 한칸을 더하고, 왼쪽 한칸을 빼며) 구간합을 구한다.
 *      2-5-4. 구간합을 windowSums에 저장한다.
 *
 *  @see #getMaxProfit()
 *  2-6. 0 ~ (windowSums.length - adDays - 1) 중에서 첫번째 광고 시작일을 선택한다.
 *  2-7. (첫번째 광고 시작일 + adDays) ~ (windowSums.length - 1) 중에서 두번째 광고 시작일을 선택한다.
 *  2-8. 선택한 두개 구간 수익의 합과 maxProfit 중에 더 큰 값을 maxProfit에 저장한다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int dataCount; // 수익 데이터 개수
    static int[] profits; // 수익 데이터
    static int adDays; // 광고 기간(일)
    static int maxProfit; // 얻을 수 있는 최대 수익
    static int[] windowSums; // windowSums[i] : i일부터 adDays동안 광고했을 때 얻게 되는 수익(구간합)

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수를 입력받는다.
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스마다,
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            calculateWindowSums();
            getMaxProfit();
            sb.append("#").append(testCaseNo).append(" ").append(maxProfit).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        // 2-1. 수익 데이터 개수와, 광고 기간을 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        dataCount = Integer.parseInt(st.nextToken());
        adDays = Integer.parseInt(st.nextToken());

        // 2-2. 수익 데이터를 저장할 배열을 생성하고, 데이터를 입력받아 배열에 저장한다.
        profits = new int[dataCount];
        st = new StringTokenizer(br.readLine().trim());
        for (int dayIdx = 0; dayIdx < dataCount; dayIdx++) {
            profits[dayIdx] = Integer.parseInt(st.nextToken());
        }

        // 2-3. 얻을 수 있는 최대 수익을 초기화한다.
        maxProfit = Integer.MIN_VALUE;

        // 2-4. 각 일자부터 광고 기간(adDays)동안 광고했을 때 얻는 수익을 저장할 배열(windowSums)을 생성한다.
        windowSums = new int[dataCount - adDays + 1];
    }

    // 2-5. 슬라이딩 윈도우 기법을 활용해 각 일자부터 adDays만큼 광고했을 때 얻는 수익을 구하고 배열에 저장한다.
    static void calculateWindowSums() {
        // 2-5-1. 첫 번째 구간합(0 ~ adDays-1 까지 수익의 합)을 계산한다.
        int windowSum = 0;
        for(int dayIdx = 0; dayIdx < adDays; dayIdx++) {
            windowSum += profits[dayIdx];
        }

        // 2-5-2. 첫 번째 구간합을 windowSums[0]에 저장한다.
        windowSums[0] = windowSum;

        for(int dayIdx = adDays; dayIdx < dataCount; dayIdx++) {
            // 2-5-3. 윈도우를 오른쪽으로 한 칸씩 이동시키며(profits의 오른쪽 한칸을 더하고, 왼쪽 한칸을 빼며) 구간합을 구한다.
            windowSum += profits[dayIdx];
            windowSum -= profits[dayIdx - adDays];
            // 2-5-4. 구간합을 windowSums에 저장한다.
            windowSums[dayIdx - adDays + 1] = windowSum;
        }
    }

    static void getMaxProfit() {
        int totalProfit;
        // 2-6. 0 ~ (windowSums.length - adDays - 1) 중에서 첫번째 광고 시작일을 선택한다.
        for (int firstStartDay = 0; firstStartDay < windowSums.length - adDays; firstStartDay++) {
            // 2-7. (첫번째 광고 시작일 + adDays) ~ (windowSums.length - 1) 중에서 두번째 광고 시작일을 선택한다.
            for (int secondStartDay = firstStartDay + adDays; secondStartDay < windowSums.length; secondStartDay++) {
                // 2-8. 선택한 두개 구간 수익의 합과 maxProfit 중에 더 큰 값을 maxProfit에 저장한다.
                totalProfit = windowSums[firstStartDay] + windowSums[secondStartDay];
                maxProfit = Math.max(maxProfit, totalProfit);
            }
        }
    }
}
