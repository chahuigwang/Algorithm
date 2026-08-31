package CodeTree.BigTech.week1.course.prob6;

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int height;
    static int width;
    static int[][] board;

    // 우 하 좌 상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        init();
        fillBoard();
        printBoard();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        board = new int[height][width];
    }

    static void fillBoard() {
        int direction_num = 0;

        int row = 0, col = 0;
        int newRow, newCol;
        for(int num = 1; num <= height*width; num++) {
            board[row][col] = num;
            newRow = row + dr[direction_num];
            newCol = col + dc[direction_num];
            if(!isInRange(newRow, newCol) || board[newRow][newCol] != 0) {
                direction_num = (direction_num + 1) % 4;
            }
            row += dr[direction_num];
            col += dc[direction_num];
        }
    }

    static boolean isInRange(int row, int col) {
        return 0 <= row && row < height && 0 <= col && col < width;
    }

    static void printBoard() {
        for(int[] boardRow : board) {
            for(int col = 0; col < width; col++) {
                sb.append(boardRow[col]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
