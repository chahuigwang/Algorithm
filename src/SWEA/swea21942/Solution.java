package SWEA.swea21942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 21942. 석주의 이상한 자석
 * [전략]
 * - 보드의 아래에 초강력 자석을 두고 시뮬레이션
 * - 보드의 오른쪽에 초강력 자석을 두고 시뮬레이션 (입력 보드를 오른쪽으로 돌리고 동일하게 시뮬레이션)
 *
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *
 *  @see #initTestCase()
 *  2-1. 보드의 한 변의 크기를 입력받는다.
 *  2-2. 자석 보드의 정보를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
 *
 *  @see #countBottomMagnetsAfterMove(int[][])
 *  2-3. 보드의 배치된 자석들의 인력에 따라 자석들을 이동시킨다.
 *
 *      @see #moveMagnets(int[][])
 *      2-3-1. 첫번째 열부터 마지막 열까지 순회하며,
 *          2-3-1-1. 첫번째 행에 자석이 없다면 다음 열로 넘어간다.
 *          2-3-1-2. 다음 행에 자석이 없다면 -> 자석의 인력을 1.9배 증가시키고 다음 행으로 이동시킨다.
 *          2-3-1-3. 다음 행에 자석이 있다면,
 *              2-3-1-3-1. 다음 행에 있는 자석의 길이를 계산한다.
 *              2-3-1-3-2. 자석의 인력이 다음 행에 있는 자석의 길이(= 인력)보다 작다면 종료
 *              2-3-1-3-3. 자석의 인력과 자석의 길이를 다음 자석의 길이만큼 증가시킨다.
 *
 *  2-4. 자석들이 모두 이동한 후 마지막 행의 있는 자석의 수를 계산하여 반환한다.
 *
 *      @see #countBottomMagnets(int[][])
 *      2-4-1. 마지막 행에 있는 모든 열을 검사하며,
 *          2-4-1-1. 자석이 있는 칸(!= 0)이라면 자석의 개수 +1
 *          2-4-1-2. 자석의 개수를 반환한다.
 *
 *  @see #rotateRight()
 *  2-5. 자석 보드를 오른쪽으로 회전한다.
 *
 *  2-6. countBottomMagnetsAfterMove를 다시 호출한다.
 *
 *  2-7. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int boardSize;
    static int[][] magentBoard;

    public static void main(String[] args) throws IOException {
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            // 2-7. 테스트 케이스 번호와 함께 정답을 출력한다.
            sb.append("#").append(testCaseNo).append(" ").append(countBottomMagnetsAfterMove(magentBoard)).append(" ").append(countBottomMagnetsAfterMove(rotateRight())).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        // 2-1. 보드의 한 변의 크기를 입력받는다.
        boardSize = Integer.parseInt(br.readLine().trim());

        // 2-2. 자석 보드의 정보를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
        magentBoard = new int[boardSize][boardSize];
        for(int row = 0; row < boardSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0; col < boardSize; col++) {
                magentBoard[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int[][] rotateRight() {
        int[][] rotateRightBoard = new int[boardSize][boardSize];
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                rotateRightBoard[row][col] = magentBoard[boardSize - col - 1][row];
            }
        }
        return rotateRightBoard;
    }

    static int countBottomMagnetsAfterMove(int[][] magentBoard) {
        // 2-3. 보드의 배치된 자석들의 인력에 따라 자석들을 이동시킨다.
        moveMagnets(magentBoard);
        // 2-4. 자석들이 모두 이동한 후 마지막 행의 있는 자석의 수를 계산하여 반환한다.
        return countBottomMagnets(magentBoard);
    }

    static void moveMagnets(int[][] magentBoard) {
        // 2-3-1. 첫번째 열부터 마지막 열까지 순회하며,
        for(int col = 0; col < boardSize; col++) {
            // 2-3-1-1. 첫번째 행에 자석이 없다면 다음 열로 넘어간다.
            if(magentBoard[0][col] == 0) continue;

            int nextRow = 1; // 다음 행
            double attraction = 1; // 자석의 인력
            int magnetLength = 1; // 자석의 길이
            int nextMagentLength; // 다음 자석(혹은 자석 덩어리)의 길이
            while(nextRow < boardSize) {
                // 2-3-1-2. 다음 행에 자석이 없다면 -> 자석의 인력을 1.9배 증가시키고 다음 행으로 이동시킨다.
                if(magentBoard[nextRow][col] == 0) {
                    attraction *= 1.9;
                    magentBoard[nextRow - magnetLength][col] = 0; // 기존 자석의 시작점
                    magentBoard[nextRow][col] = 1; // 이동 후 자석의 끝점
                    nextRow++;
                }
                else { // 2-3-1-3. 다음 행에 자석이 있다면,
                    nextMagentLength = 0;
                    // 2-3-1-3-1. 다음 행에 있는 자석의 길이를 계산한다.
                    while(nextRow < boardSize && magentBoard[nextRow][col] == 1) { // 다음 행에 있는 자석의 개수만큼
                        nextMagentLength++; // 다음 행에 있는 자석(혹은 자석 덩어리)의 길이 +1
                        nextRow++;
                    }
                    // 2-3-1-3-2. 자석의 인력이 다음 행에 있는 자석의 길이(= 인력)보다 작다면 종료
                    if(attraction <= nextMagentLength) break;
                    // 2-3-1-3-3. 자석의 인력과 자석의 길이를 다음 자석의 길이만큼 증가시킨다.
                    attraction += nextMagentLength;
                    magnetLength += nextMagentLength;
                }
            }
        }
    }

    // magentBoard의 맨 마지막 행에 있는 자석의 수를 반환한다.
    static int countBottomMagnets(int[][] magentBoard) {
        int magnetCount = 0;
        // 2-4-1. 마지막 행에 있는 모든 열을 검사하며,
        for(int col = 0; col < boardSize; col++) {
            // 2-4-1-1. 자석이 있는 칸(!= 0)이라면 자석의 개수 +1
            if(magentBoard[boardSize - 1][col] != 0) magnetCount++;
        }
        // 2-4-1-2. 자석의 개수를 반환한다.
        return magnetCount;
    }
}
