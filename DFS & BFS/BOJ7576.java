import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ7576 {
    public static int[][] box;

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();
        box = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                box[i][j] = sc.nextInt();
                if(box[i][j] == 1)
                    queue.offer(new int[]{i, j});
            }
        }
        bfs(queue);

        int maxDays = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int days = box[i][j];
                if(days == 0) {
                    System.out.println(-1);
                    return;
                }
                maxDays = Math.max(maxDays, days);
            }
        }
        System.out.println(maxDays-1);
    }

    public static void bfs(Queue<int[]> queue) {
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < m && box[nx][ny] == 0) {
                    box[nx][ny] = box[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
