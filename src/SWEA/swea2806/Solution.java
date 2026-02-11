package SWEA.swea2806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy 2086
 * @author SSAFY
 *
 *	@see #main(String[])
 *	1. 테스트 케이스의 개수를 입력받는다.
 *	2. 각 테스트 케이스 마다,
 *
 *		@see #inputTestCase()
 *		2-1. 체스보드의 사이즈이자 퀸의 개수인 N을 입력받는다.
 *
 *		2-2. 퀸을 놓을 수 있는지 여부를 저장하는 blocked 배열을 생성한다.
 *
 *		@see #putQueen(int)
 *		2-3. 모든 행에 퀸을 놓았다면 completeCount를 1 증가시킨다.
 *		2-4. 아직 모든 행에 퀸을 놓지 않았다면 모든 행에 퀸을 놓을 때까지 다음을 반복한다.
 *			2-4-1. 해당 위치에 퀸을 놓을 수 없다면 건너뛴다.
 *
 *			@see #markBlocked(int, int)
 *			2-4-2. 해당 위치에 퀸을 놓음으로써 막히는 위치들을 blocked 배열에 표시한다.
 *
 *			2-4-3. 다음 행으로 넘어간다.
 *
 *			@see #unmarkBlocked(int, int)
 *			2-4-4. 다음 선택을 위해, 표시했던 blocked 배열을 되돌린다.
 *
 *		2-5. 테스트 케이스 번호와 함께 모든 퀸을 놓을 수 있는 경우의 수를 출력한다.
 */

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;

    static int[][] blocked; // 0: 퀸을 놓을 수  있음, 1이상: 퀸을 놓을 수 없음

    static int completeCount;

    // 상하좌우 + 대각선 방향
    static final int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static final int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};
    static final int DIRECTION_COUNT = 8;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 1. 테스트 케이스의 개수를 입력받는다.
        int numTestCase = Integer.parseInt(br.readLine().trim());

        // 2. 각 테스트 케이스 마다,
        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            inputTestCase();
            // 2-2. 퀸을 놓을 수 있는지 여부를 저장하는 blocked 배열을 생성한다.
            blocked = new int[N][N];
            completeCount = 0;

            putQueen(0);

            sb.append("#").append(testCaseNo).append(" ").append(completeCount).append("\n");
        }
        System.out.println(sb);
    }

    static void inputTestCase() throws IOException {
        // 2-1. 체스보드의 사이즈이자 퀸의 개수인 N을 입력받는다.
        N = Integer.parseInt(br.readLine().trim());
    }

    static void putQueen(int row) {
        // 2-3. 모든 퀸을 놓았다면 completeCount를 1 증가시킨다.
        if(row == N) {
            completeCount++;
            return;
        }

        // 2-4. 아직 모든 퀸을 놓지 않았다면 모든 퀸을 놓을 때까지 다음을 반복한다.
        for(int col = 0; col < N; col++) {
            // 2-4-1. 해당 위치에 퀸을 놓을 수 없다면 건너뛴다.
            if(blocked[row][col] >= 1) continue;
            markBlocked(row, col);
            // 2-4-3. 다음 행으로 넘어간다.
            putQueen(row + 1);
            // 2-4-4. 다음 선택을 위해, 표시했던 blocked 배열을 되돌린다.
            unmarkBlocked(row, col);
        }
    }

    static void markBlocked(int row, int col) {
        blocked[row][col] += 1;
        for(int directionIdx = 0; directionIdx < DIRECTION_COUNT; directionIdx++) {
            for(int moveDistance = 1; moveDistance < N; moveDistance++) {
                int nx = row + dx[directionIdx] * moveDistance;
                int ny = col + dy[directionIdx] * moveDistance;
                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    blocked[nx][ny] += 1;
                }
            }
        }
    }

    static void unmarkBlocked(int row, int col) {
        blocked[row][col] -= 1;
        for(int directionIdx = 0; directionIdx < DIRECTION_COUNT; directionIdx++) {
            for(int moveDistance = 1; moveDistance < N; moveDistance++) {
                int nx = row + dx[directionIdx] * moveDistance;
                int ny = col + dy[directionIdx] * moveDistance;
                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    blocked[nx][ny] -= 1;
                }
            }
        }
    }
}
