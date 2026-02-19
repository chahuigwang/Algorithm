package SWEA.swea7733;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 7733
 * @author chahuigwang
 *
 *    @see #main(String[])
 *    1. 테스트 케이스 개수를 입력받는다.
 *    2. 각 테스트 케이스마다,
 *
 *    @see #initTestCase()
 *    2-1. 치즈 한 변의 길이를 입력받는다.
 *    2-2. 치즈의 맛있는 정도를 저장할 2차원 배열을 생성하고 맛있는 정도를 입력받아 저장한다.
 *    2-3. 각 칸의 방문 여부를 저장할 2차원 배열을 생성한다.
 *    2-4. 최대 덩어리 개수를 1로 초기화한다.
 *
 *    @see #getMaxChunkCount()
 *    2-5. 1일 차부터 100일 차까지,
 *
 *        @see #afterOneday(int)
 *        2-5-1. 각 일자의 요정이 갉아먹은 칸 방문처리
 *
 *        @see #dfs(int, int)
 *        2-5-2. 각 일자별 덩어리 개수를 구한 뒤 maxChunkCount 갱신
 */

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int mapSize;
    static int[][] cheeseMap;
    static boolean[][] visited;
    static int maxChunkCount;

    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static final int DIRECTION_COUNT = 4;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int numTestCase = Integer.parseInt(br.readLine().trim());
        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            initTestCase();
            getMaxChunkCount();
            sb.append("#").append(testCaseNo).append(" ").append(maxChunkCount).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());
        cheeseMap = new int[mapSize][mapSize];

        for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                cheeseMap[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[mapSize][mapSize];
        maxChunkCount = 0;
    }

    static void afterOneday(int day) {
        for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                if(cheeseMap[rowIdx][colIdx] == day) visited[rowIdx][colIdx] = true;
            }
        }
    }

    static void dfs(int row, int col) {
        visited[row][col] = true;
        for(int directionIdx = 0; directionIdx < DIRECTION_COUNT; directionIdx++) {
            int nx = row + DX[directionIdx];
            int ny = col + DY[directionIdx];
            if(0 <= nx && nx < mapSize && 0 <= ny && ny < mapSize && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    static void getMaxChunkCount() {
        int chunkCount;
        for(int day = 1; day <= 100; day++) {
            chunkCount = 0;
            afterOneday(day);
            boolean[][] tempVisited = copyVisited();
            for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
                for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                    if(!visited[rowIdx][colIdx]) {
                        dfs(rowIdx, colIdx);
                        chunkCount++;
                    }
                }
            }
            visited = tempVisited;
            maxChunkCount = Math.max(maxChunkCount, chunkCount);
        }
    }

    static boolean[][] copyVisited() {
        boolean[][] copied = new boolean[mapSize][mapSize];
        for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                copied[rowIdx][colIdx] = visited[rowIdx][colIdx];
            }
        }
        return copied;
    }
}
