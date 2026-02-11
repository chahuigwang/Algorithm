package SWEA.swea1486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *	@see #inputTestCase()
 *	2-1. 점원들의 수와 선반의 높이를 입력받는다.
 *	2-2. 각 점원의 키를 저장할 배열을 생성하고 키 정보를 입력받아 저장한다.
 *
 *	@see #getMinHigherHeight(int, int)
 *	2-3. 선반보다 높은 최소 탑의 높이를 구한다.
 *		2-3-1. 점원들의 키가 저장된 배열을 순회하며 탑의 높이에 각 점원의 더하는 경우와 더하지 않는 경우를 나누어 탑을 완성한다.
 *		2-3-2. 점원들의 키가 저장된 배열을 모두 확인했다면 완성된 탑의 높이를 확인한다.
 *			2-3-2-1. 완성된 탑의 높이가 선반의 높이보다 낮다면 -> 아무 일도 일어나지 않는다.
 *			2-3-2-2. 완성된 탑의 높이가 선반의 높이보다 높거나 같다면
 *					 -> 선반보다 높은 최소 탑의 높이와 비교하여 그보다 낮다면 최소 탑의 높이를 갱신한다.
 *
 *	2-4. 테스트 케이스의 번호와 함께 선반보다 높은 최소 탑의 높이와 선반의 높이의 차를 출력한다.
 */

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numStaff;
    static int shelveHeight;

    static int[] staffHeights;

    static int minHigherHeight; // 선반보다 높은 최소 탑의 높이

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int numTestCase = Integer.parseInt(br.readLine().trim());
        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            inputTestCase();
            minHigherHeight = Integer.MAX_VALUE;

            getMinHigherHeight(0, 0);

            sb.append("#").append(testCaseNo).append(" ").append(minHigherHeight - shelveHeight).append("\n");
        }
        System.out.println(sb);
    }

    static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        numStaff = Integer.parseInt(st.nextToken());
        shelveHeight = Integer.parseInt(st.nextToken());

        staffHeights = new int[numStaff];

        st = new StringTokenizer(br.readLine().trim());
        for(int staffIdx = 0; staffIdx < numStaff; staffIdx++) {
            staffHeights[staffIdx] = Integer.parseInt(st.nextToken());
        }
    }

    static void getMinHigherHeight(int staffHeightIdx, int totalHeight) {
        if(staffHeightIdx == numStaff) {
            if(totalHeight < shelveHeight) return;
            minHigherHeight = Math.min(minHigherHeight, totalHeight);
            return;
        }

        getMinHigherHeight(staffHeightIdx + 1, totalHeight + staffHeights[staffHeightIdx]);
        getMinHigherHeight(staffHeightIdx + 1, totalHeight);
    }
}

