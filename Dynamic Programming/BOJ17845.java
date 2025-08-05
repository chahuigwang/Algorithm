import java.util.Scanner;

public class BOJ17845 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 최대 공부시간
        int k = sc.nextInt(); // 과목 수

        int[] value = new int[k];
        int[] time = new int[k];
        for(int i = 0; i < k; i++) {
            value[i] = sc.nextInt();
            time[i] = sc.nextInt();
        }

        int[] dp = new int[n+1];
        for(int i = 0; i < k; i++) {
            for(int j = n; j >= time[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-time[i]] + value[i]);
            }
        }
        System.out.println(dp[n]);
    }
}
