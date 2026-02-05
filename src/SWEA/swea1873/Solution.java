package SWEA.swea1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SW Expert Academy 1873
 * 맵의 구성 요소
 * -----------
 * 문자	의미
 * .	평지(전차가 들어갈 수 있다.)
 * *	벽돌로 만들어진 벽
 * #	강철로 만들어진 벽
 * -	물(전차는 들어갈 수 없다.)
 * ^	위쪽을 바라보는 전차(아래는 평지이다.)
 * v	아래쪽을 바라보는 전차(아래는 평지이다.)
 * <	왼쪽을 바라보는 전차(아래는 평지이다.)
 * >	오른쪽을 바라보는 전차(아래는 평지이다.)
 * 동작의 종류
 * ---------
 * 문자	동작
 * U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
 * D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
 * L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
 * R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
 * S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 *
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *      2-1. 게임 맵의 높이와 너비를 입력받는다.
 *      2-2. 높이 x 너비의 크기를 가지는 게임 맵 2차원 배열을 생성한다.
 *      2-3. 초기 맵 상태에 해당하는 입력을 게임 맵에 저장한다.
 *      2-4. 동작의 개수를 입력받는다.
 *      2-5. 동작 문자열을 입력 받는다.
 *      2-6. 초기 상태의 전차 위치를 찾는다.
 *      2-7. 동작의 개수만큼 다음을 반복한다.
 *
 *          @see #oneAction(char)
 *          2-7-1. 인자로 들어온 동작이
 *              1) U 라면 -> 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동
 *              2) D 라면 -> 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동
 *              3) L 이라면 -> 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동
 *              4) R 이라면 -> 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동
 *              5) S 라면 -> 전차가 현재 바라보고 있는 방향으로 포탄을 발사
 *                  @see #shoot(char, int, int)
 *                  5-1) 전차가 바라보고 있는 방향이
 *                      1) 위쪽이라면 -> 전차의 현재 위치의 한 칸 위부터 게임 맵의 맨 윗칸까지 검사하며 벽돌이 있다면 평지로 변경 / 강철벽이 있다면 -> 폭탄 소멸(메서드 종료)
 *                      2) 아래쪽이라면 -> 전차의 현재 위치의 한 칸 아래부터 게임 맵의 맨 아랫칸까지 검사하며 벽돌이 있다면 평지로 변경 / 강철벽이 있다면 -> 폭탄 소멸(메서드 종료)
 *                      3) 왼쪽이라면 -> 전차의 현재 위치의 한 칸 왼칸부터 게임 맵의 맨 왼쪽칸까지 검사하며 벽돌이 있다면 평지로 변경 / 강철벽이 있다면 -> 폭탄 소멸(메서드 종료)
 *                      4) 오른쪽이라면 -> 전차의 현재 위치의 한 칸 오른칸부터 게임 맵의 맨 오른쪽칸까지 검사하며 벽돌이 있다면 평지로 변경 / 강철벽이 있다면 -> 폭탄 소멸(메서드 종료)
 *
 *      2-8. 테스트 케이스의 번호와 함께 최종 결과를 출력한다.
 *
 */

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int height;
    static int width;

    static char[][] gameMap;

    static final int[] currentLocation = new int[2]; // 전차의 현재 위치

    static final int[] U = {-1, 0};
    static final int[] D = {1, 0};
    static final int[] L = {0, -1};
    static final int[] R = {0, 1};

    public static void main(String[] args) throws IOException {
        // 1.
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        int numTestCase = Integer.parseInt(st.nextToken());

        // 2.
        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            // 2-1.
            st = new StringTokenizer(br.readLine().trim());

            height = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());

            // 2-2.
            gameMap = new char[height][width];
            // 2-3.
            for(int x = 0; x < height; x++) {
                String line = br.readLine().trim();
                for(int y = 0; y < width; y++) {
                    gameMap[x][y] = line.charAt(y);
                }
            }

            // 2-4.
            st = new StringTokenizer(br.readLine().trim());
            int lenInput = Integer.parseInt(st.nextToken());

            // 2-5.
            String actions = br.readLine().trim();

            // 2-6.
            for(int x = 0; x < height; x++) {
                for(int y = 0; y < width; y++) {
                    if(gameMap[x][y] == '^' || gameMap[x][y] == 'v' || gameMap[x][y] == '<' || gameMap[x][y] == '>') {
                        currentLocation[0] = x;
                        currentLocation[1] = y;
                    }
                }
            }

            // 2-7.
            for(int idx = 0; idx < lenInput; idx++) {
                oneAction(actions.charAt(idx));
            }

            sb = new StringBuilder();
            sb.append("#").append(testCaseNo).append(" ");

            for(int x = 0; x < height; x++) {
                for(int y = 0; y < width; y++) {
                    sb.append(gameMap[x][y]);
                }
                sb.append("\n");
            }

            System.out.print(sb.toString());
        }
    }

    static void oneAction(char action) { // 2-7-1.
        int curX = currentLocation[0];
        int curY = currentLocation[1];

        if(action == 'U') {
            int nx = curX + U[0];
            int ny = curY + U[1];
            if(nx >= 0 && gameMap[nx][ny] == '.') { // 한 칸 위의 칸이 게임 맵 범위 안에 있고 && 평지라면
                gameMap[nx][ny] = '^'; // 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 이동
                gameMap[curX][curY] = '.'; // 기존에 전차가 있던 칸은 평지

                // 전차의 현재 위치 업데이트
                currentLocation[0] = nx;
                currentLocation[1] = ny;
            }
            else gameMap[curX][curY] = '^'; // 전차가 바라보는 방향만 위쪽으로 바꿈
        }
        else if(action == 'D') {
            int nx = curX + D[0];
            int ny = curY + D[1];
            if(nx < height && gameMap[nx][ny] == '.') { // 한 칸 아래의 칸이 게임 맵 범위 안에 있고 && 평지라면
                gameMap[nx][ny] = 'v'; // 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 이동
                gameMap[curX][curY] = '.'; // 기존에 전차가 있던 칸은 평지

                // 전차의 현재 위치 업데이트
                currentLocation[0] = nx;
                currentLocation[1] = ny;
            }
            else gameMap[curX][curY] = 'v'; // 전차가 바라보는 방향만 아래쪽으로 바꿈
        }
        else if(action == 'L') {
            int nx = curX + L[0];
            int ny = curY + L[1];
            if(ny >= 0 && gameMap[nx][ny] == '.') { // 한 칸 왼쪽의 칸이 게임 맵 범위 안에 있고 && 평지라면
                gameMap[nx][ny] = '<'; // 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 이동
                gameMap[curX][curY] = '.'; // 기존에 전차가 있던 칸은 평지

                // 전차의 현재 위치 업데이트
                currentLocation[0] = nx;
                currentLocation[1] = ny;
            }
            else gameMap[curX][curY] = '<'; // 전차가 바라보는 방향만 왼쪽으로 바꿈
        }
        else if(action == 'R') {
            int nx = curX + R[0];
            int ny = curY + R[1];
            if(ny < width && gameMap[nx][ny] == '.') { // 한 칸 오른쪽의 칸이 게임 맵 범위 안에 있고 && 평지라면
                gameMap[nx][ny] = '>'; // 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 이동
                gameMap[curX][curY] = '.'; // 기존에 전차가 있던 칸은 평지

                // 전차의 현재 위치 업데이트
                currentLocation[0] = nx;
                currentLocation[1] = ny;
            }
            else gameMap[curX][curY] = '>'; // 전차가 바라보는 방향만 오른쪽으로 바꿈
        }
        else { // action == 'S'
            char curTankDirection = gameMap[curX][curY];
            shoot(curTankDirection, curX, curY);
        }
    }

    // 전차가 바라보고 있는 방향에 벽돌이 있다면 벽돌 -> 평지로 변경 / 강철벽이 있다면 -> 폭탄 소멸(메서드 종료)
    static void shoot(char direction, int curX, int curY) {
        if(direction == '^') { // 전차가 위쪽을 바라보고 있다면
            for(int x = curX - 1; x >= 0; x--) {
                if(gameMap[x][curY] == '#') return;
                if(gameMap[x][curY] == '*') {
                    gameMap[x][curY] = '.';
                    return;
                }
            }
        }
        else if(direction == 'v') { // 전차가 아래쪽을 바라보고 있다면
            for(int x = curX + 1; x < height; x++) {
                if(gameMap[x][curY] == '#') return;
                if(gameMap[x][curY] == '*') {
                    gameMap[x][curY] = '.';
                    return;
                }
            }
        }
        else if(direction == '<') { // 전차가 왼쪽을 바라보고 있다면
            for(int y = curY - 1; y >= 0; y--) {
                if(gameMap[curX][y] == '#') return;
                if(gameMap[curX][y] == '*') {
                    gameMap[curX][y] = '.';
                    return;
                }
            }
        }
        else { // 전차가 오른쪽을 바라보고 있다면
            for(int y = curY + 1; y < width; y++) {
                if(gameMap[curX][y] == '#') return;
                if(gameMap[curX][y] == '*') {
                    gameMap[curX][y] = '.';
                    return;
                }
            }
        }
    }
}
