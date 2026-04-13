import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp; // dp[i][j][방향] 0:가로 1:세로 2:대각선

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N + 1][N + 1][3];

        // 초기 상태: (1,2)에 가로 방향으로 파이프 끝
        dp[1][2][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 && j == 2) continue; // 시작점 스킵

                // 가로로 올 수 있는 경우: 왼쪽에서 가로 or 대각선
                if (j - 1 >= 1 && map[i][j] == 0) {
                    dp[i][j][0] += dp[i][j-1][0]; // 가로 -> 가로
                    dp[i][j][0] += dp[i][j-1][2]; // 대각선 -> 가로
                }

                // 세로로 올 수 있는 경우: 위에서 세로 or 대각선
                if (i - 1 >= 1 && map[i][j] == 0) {
                    dp[i][j][1] += dp[i-1][j][1]; // 세로 -> 세로
                    dp[i][j][1] += dp[i-1][j][2]; // 대각선 -> 세로
                }

                // 대각선으로 올 수 있는 경우: 왼쪽 위에서 가로, 세로, 대각선
                // 대각선은 3칸 모두 빈 칸이어야 함
                if (i - 1 >= 1 && j - 1 >= 1
                        && map[i][j] == 0
                        && map[i-1][j] == 0
                        && map[i][j-1] == 0) {
                    dp[i][j][2] += dp[i-1][j-1][0]; // 가로 -> 대각선
                    dp[i][j][2] += dp[i-1][j-1][1]; // 세로 -> 대각선
                    dp[i][j][2] += dp[i-1][j-1][2]; // 대각선 -> 대각선
                }
            }
        }

        int ans = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
        System.out.println(ans);
    }
}