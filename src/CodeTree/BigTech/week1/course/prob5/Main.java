package CodeTree.BigTech.week1.course.prob5;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        String commands = sc.next();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        int row = (int) Math.ceil(n / 2);
        int col = row;
        int direction = 0; // 북
        int result = board[row][col];

        // 북 동 남 서
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int commandCount = commands.length();
        char command;
        int newRow, newCol;
        for(int commandIdx = 0; commandIdx < commandCount; commandIdx++) {
            command = commands.charAt(commandIdx);
            if(command == 'L') {
                direction = (direction + 3) % 4;
            }
            else if(command == 'R') {
                direction = (direction + 1) % 4;
            }
            else {
                newRow = row + dr[direction];
                newCol = col + dc[direction];
                if(isInRange(newRow, newCol, n)) {
                    row = newRow;
                    col = newCol;
                    result += board[row][col];
                }
            }
        }

        System.out.println(result);
    }

    static boolean isInRange(int row, int col, int n) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
}
