package SWEA.swea1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int[][] ladderMap;
    static final int MAP_SIZE = 100;

    // 좌, 우, 아래
    static final int[] dx = {0, 0, 1};
    static final int[] dy = {-1, 1, 0};

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        for(int testCaseNo = 1; testCaseNo <= 10; testCaseNo++) {
            ladderMap = new int[MAP_SIZE][MAP_SIZE];

            br.readLine();

            for(int rowIdx = 0; rowIdx < MAP_SIZE; rowIdx++) {
                st = new StringTokenizer(br.readLine().trim());
                for(int colIdx = 0; colIdx < MAP_SIZE; colIdx++) {
                    ladderMap[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("#").append(testCaseNo).append(" ");
            for(int startColIdx = 0; startColIdx < MAP_SIZE; startColIdx++) {
                if(ladderMap[0][startColIdx] == 1) {
                    visited = new boolean[MAP_SIZE][MAP_SIZE];
                    if(reachX(new int[]{0, startColIdx})) {
                        sb.append(startColIdx).append("\n"); // X 표시에 도달한 출발점의 y좌표 출력
                        break;
                    }
                }
            }
        }
        System.out.println(sb);
    }

    static boolean reachX(int[] start) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(start);

        int startX = start[0];
        int startY = start[1];
        visited[startX][startY] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int deltaIdx = 0; deltaIdx < 3; deltaIdx++) {
                int nx = curX + dx[deltaIdx];
                int ny = curY + dy[deltaIdx];

                if((nx >= 0 && nx < MAP_SIZE && ny >= 0 && ny < MAP_SIZE) && ladderMap[nx][ny] != 0 && !visited[nx][ny]) { // 범위 검사 && 사다리인지 검사 && 방문 여부 검사
                    if(ladderMap[nx][ny] == 2) {
                        return true; // X 표시에 도달하면 true 반환
                    }

                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    break;
                }
            }
        }
        return false;
    }
}
