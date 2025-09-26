import java.util.Scanner;

public class BOJ2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] dp = new int[n+1];
        for(int i = 0; i < n+1; i++) {
            dp[i] = -1;
        }
        dp[0] = 0;

        int[] sugarBags = {3, 5};

        for(int sugarBag : sugarBags) {
            for(int i = sugarBag; i < n+1; i++) {
                if(dp[i-sugarBag] != -1)
                    dp[i] = dp[i-sugarBag] + 1;
            }
        }
        System.out.println(dp[n]);
    }
}
