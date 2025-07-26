import java.util.Scanner;

public class FreezeDrink {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        int[][] frame = new int[n][m];
        for(int i = 0; i < n; i++) {
            String row = sc.nextLine();
            for(int j = 0; j < m; j++) {
                frame[i][j] = row.charAt(j) - '0';
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(dfs(frame, i, j))
                    count++;
            }
        }
        System.out.println(count);
    }
    public static boolean dfs(int[][] graph, int x, int y) {
        int n = graph.length;
        int m = graph[0].length;

        if(x < 0 || x >= n || y < 0 || y >= m)
            return false;

        if(graph[x][y] == 0) {
            graph[x][y] = 1;

            dfs(graph, x-1, y);
            dfs(graph, x+1, y);
            dfs(graph, x, y-1);
            dfs(graph, x, y+1);

            return true;
        }
        return false;
    }
}
