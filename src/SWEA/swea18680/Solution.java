package SWEA.swea18680;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 18680. 재선이의 연구실 네트워크 구성하기
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *
 *  @see #initTestCase()
 *  2-1. 연구실의 한 변의 크기를 입력받는다.
 *  2-2. AP의 범위를 입력받는다.
 *  2-3. 포트들의 위치를 저장할 리스트를 생성한다.
 *  2-4. 공유기들의 위치와 범위를 저장할 리스트를 생성한다.
 *  2-5. 연구실의 각 위치의 정보를 입력받아 포트와 공유기 정보를 추가한다 + 포트와 공유기의 개수를 센다.
 *  2-6. 모든 공유기를 연결할 수 있는 최소의 AP 개수를 정수의 최댓값으로 설정한다.
 *
 *  @see #calcMinApCount()
 *  2-7. AP를 1개부터 min(포트의 개수, 5) 개까지 설치해보며,
 *      2-7-1. 설치한 AP로 모든 공유기를 연결할 수 있는지 검사한다.
 *
 *          @see #canConnectAllRouterWithNAp(int, int, int) : 전체 포트 중에서 apCount 개의 AP를 설치했을 때 모든 공유기를 연결할 수 있는지 검사한다.
 *          2-7-1-1. 0번 포트부터 (portCount - 1)번 포트까지 현재 포트를 선택하는 경우와 선택하지 않는 경우를 고려하여 조합을 완성한다.
 *          2-7-1-2. 조합이 완성되면 설치된 AP로 모든 공유기를 연결할 수 있는지 검사한다.
 *
 *              @see #coverAllRouters(int)
 *              2-7-1-2-1. 모든 공유기를 순회하며,
 *                  2-7-1-2-1-1. 공유기가 설치된 AP와 범위가 겹치는지 검사한다.
 *                  2-7-1-2-1-2. 공유기의 범위가 모든 AP의 범위와 한번도 겹치지 않았다면 false 반환, 아니라면 true를 반환한다.
 *
 *          2-7-1-3. 모든 공유기를 연결할 수 있다면 -> 모든 공유기를 연결할 수 있는지 여부를 true로 저장하고 종료한다.
 *
 *      2-7-2. 모든 공유기를 연결할 수 있다면 설치한 AP의 개수가 정답(모든 공유기를 연결할 수 있는 최소의 AP 개수)이 된다.
 *
 *  2-8. 테스트 케이스 번호와 함께 모든 공유기를 연결할 수 있는 최소의 AP 개수를 출력한다.(모든 공유기를 연결할 수 없으면 -1을 출력한다.)
 *
 */

class Solution {

    static class Port {
        int row;
        int col;

        Port(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Router {
        int row;
        int col;
        int range;

        Router(int row, int col, int range) {
            this.row = row;
            this.col = col;
            this.range = range;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int mapSize;
    static int apRange;
    static int portCount;
    static int routerCount;
    static List<Port> portList = new ArrayList<>();
    static List<Router> routerList = new ArrayList<>();

    static int minApCount;
    static final int MAX_AP_COUNT = 5;

    static boolean canConnectAllRouter;

    static int[] selectedPortIndexes;

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수를 입력받는다.
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스마다,
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            calcMinApCount();
            sb.append("#").append(testCaseNo).append(" ").append(minApCount != Integer.MAX_VALUE ? minApCount : -1).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        // 2-1. 연구실의 한 변의 크기를 입력받는다.
        mapSize = Integer.parseInt(st.nextToken());
        // 2-2. AP의 범위를 입력받는다.
        apRange = Integer.parseInt(st.nextToken());
        // 2-3. 포트들의 위치를 저장할 리스트를 생성한다.
        portList.clear();
        // 2-4. 공유기들의 위치와 범위를 저장할 리스트를 생성한다.
        routerList.clear();
        // 2-5. 연구실의 각 위치의 정보를 입력받아 포트와 공유기 정보를 추가한다 + 포트와 공유기의 개수를 센다.
        int input;
        portCount = 0;
        routerCount = 0;
        for(int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0; col < mapSize; col++) {
                input = Integer.parseInt(st.nextToken());
                if(input == 9) {
                    portCount++;
                    portList.add(new Port(row, col));
                }
                if(input != 0 && input != 9) {
                    routerCount++;
                    routerList.add(new Router(row, col, input));
                }
            }
        }

        // 2-6. 모든 공유기를 연결할 수 있는 최소의 AP 개수를 정수의 최댓값으로 설정한다.
        minApCount = Integer.MAX_VALUE;
    }

    static void calcMinApCount() {
        // 2-7. AP를 1개부터 min(포트의 개수, 5) 개까지 설치해보며,
        for(int apCount = 1; apCount <= Math.min(portCount, MAX_AP_COUNT); apCount++) {
            canConnectAllRouter = false;
            selectedPortIndexes = new int[apCount];

            // 2-7-1. 설치한 AP로 모든 공유기를 연결할 수 있는지 검사한다.
            canConnectAllRouterWithNAp(0, 0, apCount);
            // 2-7-2. 모든 공유기를 연결할 수 있다면 설치한 AP의 개수가 정답(모든 공유기를 연결할 수 있는 최소의 AP 개수)이 된다.
            if(canConnectAllRouter) {
                minApCount = apCount;
                return;
            }
        }
    }

    // N개의 AP를 설치해서 모든 공유기를 연결할 수 있는지 검사
    static void canConnectAllRouterWithNAp(int installApCount, int portIdx, int apCount) {
        // 이미 모든 공유기를 연결할 수 있다면 종료
        if(canConnectAllRouter) return;

        if(installApCount == apCount) {
            // 2-7-1-2. 조합이 완성되면 설치된 AP로 모든 공유기를 연결할 수 있는지 검사한다.
            // 2-7-1-3. 모든 공유기를 연결할 수 있다면 -> 모든 공유기를 연결할 수 있는지 여부를 true로 저장하고 종료한다.
            if(coverAllRouters(apCount)) canConnectAllRouter = true;
            return;
        }

        if(portIdx == portCount) return;

        // 2-7-1-1. 0번 포트부터 (portCount - 1)번 포트까지 현재 포트를 선택하는 경우와 선택하지 않는 경우를 고려하여 조합을 완성한다.
        // 현재 포트에 AP 설치
        selectedPortIndexes[installApCount] = portIdx;
        canConnectAllRouterWithNAp(installApCount + 1, portIdx + 1, apCount);

        // 현재 포트에 AP 설치 x
        canConnectAllRouterWithNAp(installApCount, portIdx + 1, apCount);
    }

    static boolean coverAllRouters(int apCount) {
        boolean coverRouter;
        int distance;

        // 2-7-1-2-1. 모든 공유기를 순회하며,
        for(Router router : routerList) {
            coverRouter = false;
            for(int apIdx = 0; apIdx < apCount; apIdx++) {
                Port ap = portList.get(selectedPortIndexes[apIdx]);

                distance = Math.abs(ap.row - router.row) + Math.abs(ap.col - router.col);

                // 2-7-1-2-1-1. 공유기가 설치된 AP와 범위가 겹치는지 검사한다.
                if(distance <= apRange + router.range) { // 공유기의 범위와 AP의 범위가 겹친다면
                    coverRouter = true;
                    break;
                }
            }

            // 2-7-1-2-1-2. 공유기의 범위가 모든 AP의 범위와 한번도 겹치지 않았다면 false 반환
            if(!coverRouter) return false;
        }

        // 아니라면 true를 반환한다.
        return true;
    }
}
