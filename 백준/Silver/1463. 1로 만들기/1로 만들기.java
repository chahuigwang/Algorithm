import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(makeOne(N));
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
    }

    static int makeOne(int num) {
        if(num == 1) return 0;

        int[] dp = new int[num + 1];
        for(int n = 2; n <= num; n++) {
            dp[n] = dp[n - 1] + 1;

            if(n % 2 == 0) dp[n] = Math.min(dp[n], dp[n / 2] + 1);
            if(n % 3 == 0) dp[n] = Math.min(dp[n], dp[n / 3] + 1);
        }
        return dp[num];
    }
}
