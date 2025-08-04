import java.util.Scanner;

public class BOJ4963 {
    public static int w, h;
    public static int[][] map;

    public static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            w = sc.nextInt();
            h = sc.nextInt();
            if(w == 0 && h == 0)
                break;

            map = new int[h][w];
            for(int i = 0; i < h; i++)
                for(int j = 0; j < w; j++)
                    map[i][j] = sc.nextInt();

            int count = 0;
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(map[i][j] == 1) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static void dfs(int x, int y) {
        map[x][y] = 0;

        for(int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < h && ny >=0 && ny < w && map[nx][ny] == 1)
                dfs(nx, ny);
        }
    }
}