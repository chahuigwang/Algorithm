import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj2178 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[][] maze = new int[n][m];
        for(int i = 0; i < n; i++){
            String row = sc.nextLine();
            for(int j = 0; j < m; j++) {
                maze[i][j] = row.charAt(j) - '0';
            }
        }

        System.out.println(bfs(maze, 0, 0));
    }
    public static int bfs(int[][] graph, int start_x, int start_y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int n = graph.length;
        int m = graph[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start_x, start_y});

        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < m && graph[nx][ny] == 1) {
                    graph[nx][ny] = graph[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return graph[n-1][m-1];
    }
}
