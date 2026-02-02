package SWEA.swea2001;

import java.util.Scanner;

/**
 * SW Expert Academy
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *      2-1. 2차원 배열의 높이이자 너비인 n을 입력받는다.
 *      2-2. 파리채의 높이이자 너비인 m을 입력받는다.
 *      2-3. 누적합을 저장할 2차원 배열을 생성하고 각 칸에 (0, 0) ~ (x, y)의 합을 저장한다.
 *      2-4. 2차원 배열의 m*m 크기의 구간합의 최대값을 갱신한다.
 *      2-5. 테스트 케이스 번호와 구간합의 최대값을 출력한다.
 */
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] accSum = new int[n+1][n+1];
            for(int x = 1; x <= n; x++) {
                for(int y = 1; y <= n; y++) {
                    accSum[x][y] = sc.nextInt() + accSum[x-1][y] + accSum[x][y-1] - accSum[x-1][y-1];
                }
            }

            int maxSum = 0;
            for(int x = m; x <= n; x++) {
                for(int y = m; y <= n; y++) {
                    int curSum = accSum[x][y] - accSum[x-m][y] - accSum[x][y-m] + accSum[x-m][y-m];
                    maxSum = Math.max(maxSum, curSum);
                }
            }

            System.out.println("#" + test_case + " " + maxSum);
        }
    }
}
