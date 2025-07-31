import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj7569 {
    public static int[][][] boxes;

    public static int[] dx = {-1, 1, 0, 0, 0, 0};
    public static int[] dy = {0, 0, -1, 1, 0, 0};
    public static int[] dz = {0, 0, 0, 0, -1, 1};

    public static int m, n, h;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();
        h = sc.nextInt();
        boxes = new int[h][n][m];
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < h; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < m; k++){
                    boxes[i][j][k] = sc.nextInt();
                    if(boxes[i][j][k] == 1) queue.offer(new int[]{i, j, k});
                }
            }
        }
        bfs(queue);

        int maxDays = 0;
        for(int i = 0; i < h; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < m; k++){
                    int days = boxes[i][j][k];
                    if(days == 0) {
                        System.out.println(-1);
                        return;
                    }
                    maxDays = Math.max(maxDays, days);
                }
            }
        }
        System.out.println(maxDays-1);
    }

    public static void bfs(Queue<int[]> queue) {
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int z = cur[2];

            for(int i = 0; i < 6; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if(nx >= 0 && nx < h && ny >= 0 && ny < n && nz >=0 && nz < m && boxes[nx][ny][nz] == 0) {
                    boxes[nx][ny][nz] = boxes[x][y][z] + 1;
                    queue.offer(new int[]{nx, ny, nz});
                }
            }
        }
    }
}
