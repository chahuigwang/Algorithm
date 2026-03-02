package SWEA.swea22921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * SWEA 22921. 재난 구조 AI 로봇
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *
 *  @see #initTestCase()
 *  2-1. 건물의 범위를 입력받는다.
 *  2-2. 각 위치에 쌓인 잔해의 높이를 저장할 2차원 배열을 생성하고, 정보를 입력받아 저장한다.
 *  2-3. (0, 0)에서 각 위치까지 가는 데 필요한 최소 연료를 저장할 배열을 생성한다.
 *  2-4. 연료 배열의 모든 위치에 모두 정수의 최댓값을 저장한다.
 *
 *  @see #rescueWithMinFuel() : Dijkstra 방식으로 (0, 0)에서 모든 위치로 가는 데 필요한 최소 연료를 계산한다.
 *  2-5. 시작 위치 (0, 0)을 우선순위 큐에 삽입한다.
 *  2-6. (0, 0)까지 가는 데 필요한 연료를 0으로 초기화한다.
 *  2-7. 우선순위 큐가 빌 때까지,
 *      2-7-1. 가장 연료가 적게 드는 노드를 꺼낸다.
 *      2-7-2. 현재 노드가 이미 처리된 적이 있는 노드라면 무시한다.
 *      2-7-3. 인접 노드(상하좌우) 중에서 이동 가능한(Map의 범위 안에 있는) 노드를 확인한다.
 *      2-7-4. (0, 0)에서 현재 노드를 거쳐 다음 노드까지 가는 최소연료를 계산한다.
 *      2-7-5. 현재 노드를 거쳐서 다음 노드로 가는 경우가 기존 다음 노드까지 가는 경우보다 연료가 적게드는 경우, 다음 노드까지 가는 데 필요한 최소 연료를 현재 노드를 거쳐 다음 노드로 가는 데 필요한 연료로 갱신한다.
 *      2-7-6. 다음 노드를 우선순위 큐에 삽입한다.
 *
 *  2-8. 테스트 케이스 번호와 함께 조난자를 만나기까지((0, 0)에서 (N-1, N-1) 까지 가는 데) 필요한 최소한의 연료를 출력한다.
 *
 */

class Solution {

    static class Node implements Comparable<Node> {
        int row;
        int col;
        int fuel;

        Node(int row, int col, int fuel) {
            this.row = row;
            this.col = col;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(Node o) {
            return this.fuel - o.fuel;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int mapSize;
    static int[][] buildingMap;
    static boolean[][] visited;
    static int[][] fuel; // (0, 0)에서 해당 위치까지 가는 데 필요한 최소 연료
    static final int INF = Integer.MAX_VALUE;

    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static final int DIRECTION_COUNT = 4;

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수를 입력받는다.
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스마다,
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            rescueWithMinFuel();
            // 2-8. 테스트 케이스 번호와 함께 조난자를 만나기까지((0, 0)에서 (N-1, N-1) 까지 가는 데) 필요한 최소한의 연료를 출력한다.
            sb.append("#").append(testCaseNo).append(" ").append(fuel[mapSize - 1][mapSize - 1]).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        // 2-1. 건물의 범위를 입력받는다.
        mapSize = Integer.parseInt(br.readLine().trim());
        // 2-2. 각 위치에 쌓인 잔해의 높이를 저장할 2차원 배열을 생성하고, 정보를 입력받아 저장한다.
        buildingMap = new int[mapSize][mapSize];
        for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                buildingMap[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }

        // 2-3. (0, 0)에서 각 위치까지 가는 데 필요한 최소 연료를 저장할 배열을 생성한다.
        fuel = new int[mapSize][mapSize];

        // 2-4. 연료 배열의 모든 위치에 모두 정수의 최댓값을 저장한다.
        for(int[] fuelRow : fuel) {
            Arrays.fill(fuelRow, INF);
        }
    }

    // Dijkstra 방식으로 (0, 0)에서 모든 위치로 가는 데 필요한 최소 연료를 계산한다.
    static void rescueWithMinFuel() {
        // 2-5. 시작 위치 (0, 0)을 우선순위 큐에 삽입한다.
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        // 2-6. (0, 0)까지 가는 데 필요한 연료를 0으로 초기화한다.
        fuel[0][0] = 0;

        Node curNode;
        int curX, curY, curFuel;
        int nextX, nextY;
        int curHeight, nextHeight;
        int nextViaCurFuel;
        // 2-7. 우선순위 큐가 빌 때까지,
        while (!pq.isEmpty()) {
            // 2-7-1. 가장 연료가 적게 드는 노드를 꺼낸다.
            curNode = pq.poll();
            curX = curNode.row;
            curY = curNode.col;
            curFuel = curNode.fuel;
            curHeight = buildingMap[curX][curY];
            // 2-7-2. 현재 노드가 이미 처리된 적이 있는 노드라면 무시한다.
            if(fuel[curX][curY] < curFuel) continue;
            // 2-7-3. 인접 노드(상하좌우) 중에서
            for(int directionIdx = 0; directionIdx < DIRECTION_COUNT; directionIdx++) {
                nextX = curX + DX[directionIdx];
                nextY = curY + DY[directionIdx];
                // 이동 가능한(Map의 범위 안에 있는) 노드를 확인한다.
                if(0 <= nextX && nextX < mapSize && 0 <= nextY && nextY < mapSize) {
                    nextHeight = buildingMap[nextX][nextY];
                    // 2-7-4. (0, 0)에서 현재 노드를 거쳐 다음 노드까지 가는 최소연료를 계산한다.
                    nextViaCurFuel = fuel[curX][curY] + calculateCurToNextFuel(curHeight, nextHeight); // (0, 0)에서 현재 노드를 거쳐 다음 노드까지 가는 최소연료
                    // 2-7-5. 현재 노드를 거쳐서 다음 노드로 가는 경우가 기존 다음 노드까지 가는 경우보다 연료가 적게드는 경우
                    if(nextViaCurFuel < fuel[nextX][nextY]) {
                        // 다음 노드까지 가는 데 필요한 최소 연료를 현재 노드를 거쳐 다음 노드로 가는 데 필요한 연료로 갱신한다.
                        fuel[nextX][nextY] = nextViaCurFuel;
                        // 2-7-6. 다음 노드를 우선순위 큐에 삽입한다.
                        pq.offer(new Node(nextX, nextY, nextViaCurFuel));
                    }
                }
            }
        }
    }

    static int calculateCurToNextFuel(int curHeight, int nextHeight) {
        int curToNextFuel;
        // 1) 현재 위치의 높이 < 다음 위치의 높이 : |다음 위치의 높이 - 현재 위치의 높이| * 2
        if(curHeight < nextHeight) curToNextFuel = (nextHeight - curHeight) * 2;
        // 2) 현재 위치의 높이 > 다음 위치의 높이: 0
        else if(curHeight > nextHeight) curToNextFuel = 0;
        // 3) 현재 위치의 높이 == 다음 위치의 높이: 1
        else curToNextFuel = 1;

        return curToNextFuel;
    }
}

