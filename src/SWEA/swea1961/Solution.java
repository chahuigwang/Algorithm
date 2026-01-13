package SWEA.swea1961;

import java.util.*;

class Solution
{
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();

            int[][] arr = new int[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append("\n");

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    sb.append(arr[N-j-1][i]);
                }
                sb.append(" ");

                for(int j = 0; j < N; j++) {
                    sb.append(arr[N-i-1][N-j-1]);
                }
                sb.append(" ");

                for(int j = 0; j < N; j++) {
                    sb.append(arr[j][N-i-1]);
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }
}
