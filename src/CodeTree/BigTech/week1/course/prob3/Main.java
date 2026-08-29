package CodeTree.BigTech.week1.course.prob3;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[][] grid;
    static int gridSize;

    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int count;

    public static void main(String[] args) throws IOException {
        init();
        countHasMoreThanThree();
        System.out.println(count);
    }

    static void init() throws IOException {
        gridSize = Integer.parseInt(br.readLine().trim());
        grid = new int[gridSize][gridSize];

        for(int row = 0; row < gridSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0; col < gridSize; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0;
    }

    static void countHasMoreThanThree() {
        int newRow, newCol;
        int cnt1;
        for(int row = 0; row < gridSize; row++) {
            for(int col = 0; col < gridSize; col++) {
                cnt1 = 0;
                for(int direction = 0; direction < 4; direction++) {
                    newRow = row + dr[direction];
                    newCol = col + dc[direction];
                    if(inRange(newRow, newCol) && grid[newRow][newCol] == 1) cnt1++;
                }
                if(cnt1 >= 3) count++;
            }
        }
    }

    static boolean inRange(int row, int col) {
        return 0 <= row && row < gridSize && 0 <= col && col < gridSize;
    }
}
