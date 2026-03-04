package SWEA.swea20505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * SWEA 20505. 산악 행군
 * @author SSAFY
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스 마다,
 *
 *     @see #initTestCase()
 *     2-1. 산의 높이와 너비를 입력받는다.
 *     2-2. 산의 정보를 저장할 배열을 생성하고, 정보를 입력받아 배열에 저장한다.
 *     2-3. 출발 지점(막사)과 도착 지점을 저정한다.
 *     2-4. 최소 난이도를 정수의 최댓값으로 초기화한다.
 *
 *     @see #mountainMarch()
 *     2-6. 다익스트라 방식으로 막사의 위치에서 도착 지점까지 이동할 때 최소 난이도를 구한다.
 *
 *     2-7. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {

    static class Node implements Comparable<Node> {
        int row;
        int col;
        int maxDifficulty;

        Node(int row, int col, int maxDifficulty) {
            this.row = row;
            this.col = col;
            this.maxDifficulty = maxDifficulty;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.maxDifficulty, o.maxDifficulty);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int height;
    static int width;
    static int[][] mountainMap;

    static int startRow;
    static int startCol;
    static int endRow;
    static int endCol;

    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;

    static int[][] difficulty;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수를 입력받는다.
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스 마다,
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            mountainMarch();
            sb.append("#").append(testCaseNo).append(" ").append(difficulty[endRow][endCol]).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        // 2-1. 산의 높이와 너비를 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        // 2-3. 산의 정보를 저장할 배열을 생성하고, 정보를 입력받아 배열에 저장한다.
        mountainMap = new int[height][width];
        for(int row = 0; row < height; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0; col < width; col++) {
                mountainMap[row][col] = Integer.parseInt(st.nextToken());
                if(mountainMap[row][col] == 2) {
                    startRow = row;
                    startCol = col;
                }
                else if(mountainMap[row][col] == 3) {
                    endRow = row;
                    endCol = col;
                }
            }
        }
    }

    // Dijkstra
    static void mountainMarch() {
        // 출발 노드 우선순위 큐에 삽입
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startRow, startCol, 0));

        // difficulty : “경로에서 등장한 최대 난이도”의 최소값
        difficulty = new int[height][width];
        for(int[] difficultyRow : difficulty) {
            Arrays.fill(difficultyRow, INF);
        }
        difficulty[startRow][startCol] = 0;

        int curRow, curCol;
        int curMaxDifficulty;
        int nextRow, nextCol;
        int nextMaxDifficulty;
        int moveDifficulty;
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            curRow = curNode.row;
            curCol = curNode.col;
            curMaxDifficulty = curNode.maxDifficulty;

            // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
            if(difficulty[curRow][curCol] < curMaxDifficulty) continue;
            // 도착 지점에 도착했다면 종료
            if(curRow == endRow && curCol == endCol) break;

            for(int direction = UP; direction <= RIGHT; direction++) {
                // 옆으로 이동
                if(direction == LEFT || direction == RIGHT) {
                    nextCol = curCol + DC[direction];
                    // 범위 체크
                    if(0 <= nextCol && nextCol < width) {
                        if(mountainMap[curRow][nextCol] != 0) { // 도로가 있다면
                            nextMaxDifficulty = curMaxDifficulty;
                            if(nextMaxDifficulty < difficulty[curRow][nextCol]) {
                                difficulty[curRow][nextCol] = nextMaxDifficulty;
                                pq.offer(new Node(curRow, nextCol, nextMaxDifficulty));
                            }
                        }
                    }
                }
                else { // 위아래로 이동 = else if(direction == UP || direction == DOWN);
                    nextRow = curRow + DR[direction];
                    moveDifficulty = 1;

                    while(0 <= nextRow && nextRow < height && mountainMap[nextRow][curCol] == 0) {
                        nextRow += DR[direction];
                        moveDifficulty += 1;
                    }

                    if(0 <= nextRow && nextRow < height) {
                        nextMaxDifficulty = Math.max(curMaxDifficulty, moveDifficulty);
                        if(nextMaxDifficulty < difficulty[nextRow][curCol]) {
                            difficulty[nextRow][curCol] = nextMaxDifficulty;
                            pq.offer(new Node(nextRow, curCol, nextMaxDifficulty));
                        }
                    }
                }
            }
        }
    }
}
