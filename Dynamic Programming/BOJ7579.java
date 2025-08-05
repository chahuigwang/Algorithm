import java.util.Scanner;

public class BOJ7579 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 앱의 개수
        int m = sc.nextInt(); // 필요한 메모리

        int[] memory = new int[n];
        int[] cost = new int[n];
        int sumCost = 0;

        for(int i = 0; i < n; i++) {
            memory[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++) {
            cost[i] = sc.nextInt();
            sumCost += cost[i];
        }

        int[] dp = new int[sumCost+1];
        for(int i = 0; i < n; i++) {
            for(int j = sumCost; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-cost[i]] + memory[i]);
            }
        }

        for(int i = 0; i <= sumCost; i++){
            if(dp[i] >= m) {
                System.out.println(i);
                return;
            }
        }
    }
}
