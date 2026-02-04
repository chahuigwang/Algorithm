package BOJ.boj2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][3];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] maxDp = new int[n][3];
        maxDp[0] = Arrays.copyOf(board[0], 3);
        int[][] minDp = new int[n][3];
        minDp[0] = Arrays.copyOf(board[0], 3);

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < 3; j++) {
                int maxUp = maxDp[i-1][j];
                int maxLeftUp = (j - 1) >= 0 ? maxDp[i-1][j-1] : maxUp;
                int maxRightUp = (j + 1) < 3 ? maxDp[i-1][j+1] : maxUp;

                int minUp = minDp[i-1][j];
                int minLeftUp = (j - 1) >= 0 ? minDp[i-1][j-1] : minUp;
                int minRightUp = (j + 1) < 3 ? minDp[i-1][j+1] : minUp;

                maxDp[i][j] = Math.max(Math.max(maxLeftUp, maxUp), maxRightUp) + board[i][j];
                minDp[i][j] = Math.min(Math.min(minLeftUp, minUp), minRightUp) + board[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(Math.max(Math.max(maxDp[n-1][0], maxDp[n-1][1]), maxDp[n-1][2]));
        sb.append(" ");
        sb.append(Math.min(Math.min(minDp[n-1][0], minDp[n-1][1]), minDp[n-1][2]));

        System.out.println(sb.toString());
    }
}
