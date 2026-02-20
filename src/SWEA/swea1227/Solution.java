package SWEA.swea1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * SWEA 1227
 * @author chahuigwang
 *
 *  @see #main(String[])
 *  1. 10개의 각 테스트 케이스마다,
 *
 *  @see #initTestCase()
 *  1-1. 미로의 정보를 2차원 배열에 저장한다.
 *
 *  @see #canReach()
 *  1-2. 너비 우선 탐색으로 이동가능한 모든 경로를 탐색하며,
 *      a) 도착점에 도달할 수 있다면 1 반환
 *      b) 도착점에 도달할 수 없다면 0 반환
 *
 *  1-3. 테스트 케이스 번호와 함께 canReach()의 결과를 출력한다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final int NUM_TESTCASE = 10;

    static final int MAZE_SIZE = 100;
    static int[][] maze = new int[MAZE_SIZE][MAZE_SIZE];

    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static final int DIRECTION_COUNT = 4;

    static final int START_X = 1;
    static final int START_Y = 1;

    public static void main(String[] args) throws IOException {
        for(int testCaseNo = 1; testCaseNo <= NUM_TESTCASE; testCaseNo++) {
            initTestCase();
            sb.append("#").append(testCaseNo).append(" ").append(canReach()).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        br.readLine(); // 테스트 케이스 번호

        for(int rowIdx = 0; rowIdx < MAZE_SIZE; rowIdx++) {
            String row = br.readLine().trim();
            for(int colIdx = 0; colIdx < MAZE_SIZE; colIdx++) {
                maze[rowIdx][colIdx] = row.charAt(colIdx) - '0';
            }
        }
    }

    static int canReach() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{START_X, START_Y});
        maze[START_X][START_Y] = -1; // 방문 처리

        int[] curPosition;
        int curX, curY;
        int nextX, nextY;
        while (!queue.isEmpty()) {
            curPosition = queue.poll();
            curX = curPosition[0];
            curY = curPosition[1];

            for(int directionIdx = 0; directionIdx < DIRECTION_COUNT; directionIdx++) {
                nextX = curX + DX[directionIdx];
                nextY = curY + DY[directionIdx];
                if(maze[nextX][nextY] != 1 && maze[nextX][nextY] != -1) { // 벽이 아니고, 방문하지 않았다면
                    if(maze[nextX][nextY] == 3) return 1;
                    queue.offer(new int[]{nextX, nextY});
                    maze[nextX][nextY] = -1;
                }
            }
        }
        return 0;
    }
}
