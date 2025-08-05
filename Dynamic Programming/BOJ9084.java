import java.util.Scanner;

public class BOJ9084 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[] coins = new int[n];
            for(int j = 0; j < n; j++) {
                coins[j] = sc.nextInt();
            }

            int m = sc.nextInt();

            int[] dp = new int[m+1];
            dp[0] = 1;

            for(int coin : coins) {
                for(int j = coin; j <= m; j++) {
                    dp[j] = dp[j] + dp[j-coin];
                }
            }
            System.out.println(dp[m]);
        }
    }
}
