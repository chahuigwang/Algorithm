import java.util.Scanner;

public class FreezeDrink {
    public static int n, m;
    public static int frame[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        frame = new int[n][m];
        for(int i = 0; i < n; i++) {
            String row = sc.nextLine();
            for(int j = 0; j < m; j++) {
                frame[i][j] = row.charAt(j) - '0';
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(dfs(i, j)) count++;
            }
        }

        System.out.println(count);
    }

    public static boolean dfs(int x, int y) {
        if(x <= -1 || x >= n || y <= -1 || y >= m) return false;

        if(frame[x][y] == 0) {
            frame[x][y] = 1;

            dfs(x - 1, y);
            dfs(x, y - 1);
            dfs(x + 1, y);
            dfs(x, y + 1);
            return true;
        }
        return false;
    }
}