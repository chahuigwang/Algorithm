package SWEA.swea24374;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * SWEA 24374. 상찬이의 간식 나눠주기
 *
 * [접근]
 * - 사람들이 원하는 칼로리 오름차순 정렬
 * - 간식들의 칼로리 오름차순 정렬
 * - 가장 낮은 칼로리를 원하는 사람이 가장 낮은 칼로리의 간식을 가져갈 수 있는지 검사
 * 	- 가져갈 수 있다면 -> 가져감(나누어 줄 수 있는 인원 +1)
 * 	- 다음 사람으로 넘어감
 *
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스 마다,
 *
 *	@see #initTestCase()
 *	2-1. 사람의 수이자 간식의 수를 입력받는다.
 *	2-2. 사람들이 선호하는 칼로리를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
 *	2-3. 오름차순 정렬한다.
 *	2-4. 간식들의 칼로리를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
 *	2-5. 오름차순 정렬한다.
 *	2-6. 최대로 나누어 줄 수 있는 인원을 0으로 초기화한다.
 *
 *	@see #initTestCase()
 *	2-7. 가장 낮은 칼로리를 희망하는 사람부터 시작해서 가장 높은 칼로리를 희망하는 사람까지,
 *
 *		@see #canTake(int, int)
 *		2-7-1. 가장 칼로리가 낮은 간식을 가져갈 수 있는지 검사한다.
 *
 *		2-7-2. 가져갈 수 있다면 가져가고(takeCount + 1), 가장 칼로리가 낮은 간식을 다음 간식으로 설정한다.
 *
 *	2-8. 테스트 케이스 번호와 함께 최대로 나누어 줄 수 있는 인원을 출력한다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int peopleSnackCount;
    static int[] peopleWishCal;
    static int[] snackCal;
    static int takeCount;

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수를 입력받는다.
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스 마다,
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            sb.append("#").append(testCaseNo).append(" ").append(takeSnacks()).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        // 2-1. 사람의 수이자 간식의 수를 입력받는다.
        peopleSnackCount = Integer.parseInt(br.readLine().trim());

        // 2-2. 사람들이 선호하는 칼로리를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
        peopleWishCal = new int[peopleSnackCount];
        st = new StringTokenizer(br.readLine().trim());
        for(int peopleIdx = 0; peopleIdx < peopleSnackCount; peopleIdx++) {
            peopleWishCal[peopleIdx] = Integer.parseInt(st.nextToken());
        }
        // 2-3. 오름차순 정렬한다.
        Arrays.sort(peopleWishCal);

        // 2-4. 간식들의 칼로리를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
        snackCal = new int[peopleSnackCount];
        st = new StringTokenizer(br.readLine().trim());
        for(int snackIdx = 0; snackIdx < peopleSnackCount; snackIdx++) {
            snackCal[snackIdx] = Integer.parseInt(st.nextToken());
        }
        // 2-5. 오름차순 정렬한다.
        Arrays.sort(snackCal);

        // 2-6. 최대로 나누어 줄 수 있는 인원을 0으로 초기화한다.
        takeCount = 0;
    }

    static int takeSnacks() {
        int leastCalSnackIdx = 0;
        // 2-7. 가장 낮은 칼로리를 희망하는 사람부터 시작해서 가장 높은 칼로리를 희망하는 사람까지,
        for(int peopleIdx = 0; peopleIdx < peopleSnackCount; peopleIdx++) {
            int curCheckSnackIdx = leastCalSnackIdx; // 현재 사람이 가져갈 수 있는지 검사하는 간식
            while(curCheckSnackIdx < peopleSnackCount) {
                if(canTake(peopleWishCal[peopleIdx], snackCal[curCheckSnackIdx])) { // 2-7-1. 가장 칼로리가 낮은 간식을 가져갈 수 있는지 검사한다.
                    // 2-7-2. 가져갈 수 있다면 가져가고(takeCount + 1), 가장 칼로리가 낮은 간식을 다음 간식으로 설정한다.
                    takeCount++;
                    leastCalSnackIdx = curCheckSnackIdx + 1;
                    break;
                }

                if(curCheckSnackIdx == peopleSnackCount - 1) break;
                curCheckSnackIdx++;
            }
        }
        return takeCount;
    }

    static boolean canTake(int wishCal, int snackCal) {
        return Math.abs(wishCal - snackCal) <= 3;
    }
}
