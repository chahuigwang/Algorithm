import java.util.Scanner;

public class BOJ1932 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] tringle = new int[n][n];
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i+1; j++) {
                tringle[i][j] = sc.nextInt();
                dp[i][j] = tringle[i][j];
            }
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i+1; j++) {
                int leftUp;
                if(j-1 >= 0) leftUp = dp[i-1][j-1];
                else leftUp = 0;

                int up = dp[i-1][j];

                dp[i][j] = Math.max(leftUp, up) + tringle[i][j];
            }
        }
        int[] bottom = dp[n-1];
        int max = 0;
        for(int b : bottom) {
            max = Math.max(max, b);
        }
        System.out.println(max);
    }
}
