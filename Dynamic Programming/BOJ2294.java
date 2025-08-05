import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ2294 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        Set<Integer> coins = new HashSet<>();
        for(int i = 0; i < n; i++) {
            coins.add(sc.nextInt());
        }

        int[] dp = new int[k+1];
        for(int i = 1; i <= k; i++) {
            dp[i] = 10001;
        }

        for(int coin : coins) {
            for(int i = coin; i <= k; i++) {
                if(dp[i-coin] != 10001)
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }
        System.out.println(dp[k] != 10001 ? dp[k] : -1);
    }
}
