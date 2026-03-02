package SWEA.swea16858;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 16858. 로봇 청소기
 * [전략]
 * - 오른쪽에 목표 먼지가 있다면 오른쪽으로 회전
 * - 벽을 만나면 오른쪽으로 회전
 * - 아니면 계속 직진
 * - 모든 먼지를 청소했다면 회전한 횟수 출력
 *
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스 마다,
 *
 *  @see #initTestCase()
 *  2-1. 맵의 한 변의 크기를 입력받는다.
 *  2-2. 먼지의 위치 정보를 저장할 2차원 배열을 생성하고, 정보를 입력받아 저장한다.
 *  2-3. 먼지의 개수를 센다.
 *  2-4. 회전 횟수를 저장할 변수를 0으로 초기화한다.
 *
 *  @see #cleanAllDust(int, int)
 *  2-5. 모든 먼지를 청소할 때까지,
 *
 *      @see #isTargetDustOnRight(int, int, int, int)
 *      2-5-1. 현재 방향 기준 오른쪽에 먼지가 있는지 검사한다.
 *
 *      @see #reachedWall(int, int, int)
 *      2-5-2. 벽에 도달했는지 검사한다.
 *
 *      @see #turnRight(int)
 *      2-5-3. 현재 방향 기준 오른쪽에 목표 먼지가 있거나, 벽에 도달했다면 오른쪽으로 회전한다.
 *
 *      2-5-4. 현재 위치에 현재 현재 목표 먼지가 있다면 먼지 청소 횟수를 1 증가, 목표 먼지 번호 1 증가한다.
 *      2-5-5. 현재 바라보고 있는 방향으로 한칸 이동한다.
 *
 *  2-6. 테스트 케이스 번호와 함께 회전 횟수를 출력한다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int mapSize;
    static int[][] dustMap;
    static int dustCount;
    static int turnCount;

    // 우하좌상 순
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final int DIRECTION_COUNT = 4;
    static final int RIGHT = 0;
    static final int LEFT = 1;
    static final int DOWN = 2;
    static final int UP = 3;

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수를 입력받는다.
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스 마다,
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            cleanAllDust(0, 0);
            // 2-6. 테스트 케이스 번호와 함께 회전 횟수를 출력한다.
            sb.append("#").append(testCaseNo).append(" ").append(turnCount).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        // 2-1. 맵의 한 변의 크기를 입력받는다.
        mapSize = Integer.parseInt(br.readLine().trim());

        // 2-2. 먼지의 위치 정보를 저장할 2차원 배열을 생성하고, 정보를 입력받아 저장한다.
        dustMap = new int[mapSize][mapSize];
        dustCount = 0;
        for(int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0; col < mapSize; col++) {
                dustMap[row][col] = Integer.parseInt(st.nextToken());
                if(dustMap[row][col] != 0) dustCount++;
            }
        }

        // 2-4. 회전 횟수를 저장할 변수를 0으로 초기화한다.
        turnCount = 0;
    }

    static void cleanAllDust(int startRow, int startCol) {
        int cleanDustCount = 0;
        int targetDust = 1;
        int row = startRow;
        int col = startCol;
        int direction = RIGHT;
        // 2-5. 모든 먼지를 청소할 때까지,
        while (cleanDustCount < dustCount) {
            // 2-5-1. 현재 방향 기준 오른쪽에 먼지가 있는지 검사한다.
            // 2-5-2. 벽에 도달했는지 검사한다.
            // 2-5-3. 현재 방향 기준 오른쪽에 목표 먼지가 있거나, 벽에 도달했다면 오른쪽으로 회전한다.
            if(isTargetDustOnRight(row, col, direction, targetDust) || reachedWall(row, col, direction)) {
                direction = turnRight(direction);
                turnCount++;
            }

            // 2-5-4. 현재 위치에 현재 현재 목표 먼지가 있다면 먼지 청소 횟수를 1 증가, 목표 먼지 번호 1 증가한다.
            if(dustMap[row][col] == targetDust) {
                targetDust += 1;
                cleanDustCount += 1;
            }

            // 2-5-5. 현재 바라보고 있는 방향으로 한칸 이동한다.
            row += DX[direction];
            col += DY[direction];
        }
    }

    static boolean isTargetDustOnRight(int row, int col, int direction, int targetDust) {
        int rightDirection = (direction + 1) % 4; // 현재 바라보고 있는 방향 기준 오른쪽 방향
        int nextRow, nextCol;
        for(int moveDist = 1; moveDist < mapSize; moveDist++) {
            nextRow = row + DX[rightDirection] * moveDist;
            nextCol = col + DY[rightDirection] * moveDist;
            if(0 > nextRow || nextRow >= mapSize || 0 > nextCol || nextCol >= mapSize) break;
            if(dustMap[nextRow][nextCol] == targetDust) return true;
        }
        return false;
    }

    static boolean reachedWall(int row, int col, int direction) {
        int nextRow = row + DX[direction];
        int nextCol = col + DY[direction];
        return (0 > nextRow || nextRow >= mapSize || 0 > nextCol || nextCol >= mapSize);
    }

    static int turnRight(int direction) {
        return (direction + 1) % 4;
    }
}
