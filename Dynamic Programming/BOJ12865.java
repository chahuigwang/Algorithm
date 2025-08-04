import java.util.Scanner;

public class BOJ12865 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] weight = new int[n];
        int[] value = new int[n];
        for(int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        int[] dp = new int[k+1];
        for(int i = 0; i < n; i++) {
            for(int w = k; w >= weight[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weight[i]] + value[i]);
            }
        }
        System.out.println(dp[k]);
    }
}
