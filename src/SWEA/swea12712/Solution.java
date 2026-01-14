package SWEA.swea12712;

import java.util.*;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] arr = new int[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1}; // + 형태 & x 형태
            int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};
            int maxDead = 0;
            int curDead;

            for(int x = 0; x < n; x++) {
                for(int y = 0; y < n; y++) {
                    curDead = arr[x][y];
                    for(int i = 0; i < 4; i++) { // + 형태
                        for(int j = 1; j < m; j++) {
                            int nx = x + dx[i] * j;
                            int ny = y + dy[i] * j;
                            if(nx >= 0 && nx < n && ny >= 0 && ny < n) { // 인덱스 범위 안에 있는지 검사
                                curDead += arr[nx][ny];
                            }
                        }
                    }
                    maxDead = Math.max(maxDead, curDead);

                    curDead = arr[x][y];
                    for(int i = 4; i < 8; i++) { // x 형태
                        for(int j = 1; j < m; j++) {
                            int nx = x + dx[i] * j;
                            int ny = y + dy[i] * j;
                            if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
                                curDead += arr[nx][ny];
                            }
                        }
                    }
                    maxDead = Math.max(maxDead, curDead);
                }
            }

            System.out.println("#" + test_case + " " + maxDead);
        }
    }
}
