import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ2667 {
    public static int[][] map;
    public static ArrayList<Integer> countList = new ArrayList<>();
    public static int n;

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = Integer.parseInt(sc.nextLine());
        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 1) {
                    countList.add(dfs(i, j));
                }
            }
        }

        Collections.sort(countList);
        System.out.println(countList.size());
        for(int count : countList)
            System.out.println(count);
    }

    public static int dfs(int x, int y) {
        map[x][y] = 0;
        int count = 1;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 1) {
                count += dfs(nx, ny);
            }
        }
        return count;
    }
}
