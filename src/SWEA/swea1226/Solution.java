package SWEA.swea1226;

import java.util.Scanner;

class Solution
{
    static int[][] maze;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int t = sc.nextInt();

            maze = new int[16][16];
            visited = new boolean[16][16];

            for(int col = 0; col < 16; col++) {
                String cur = sc.next();
                String[] curArr = cur.split("");
                for(int idx = 0; idx < 16; idx++) {
                    maze[col][idx] = Integer.parseInt(curArr[idx]);
                }
            }

            int[] dest = findDestination();
            dfs(1, 1);
            int result = visited[dest[0]][dest[1]] ? 1 : 0;

            System.out.println("#" + test_case + " " + result);
        }
    }

    static void dfs(int x, int y) {
        if(!visited[x][y]) {
            visited[x][y] = true;
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && maze[nx][ny] != 1 && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    static int[] findDestination() {
        for(int x = 0; x < 16; x++) {
            for(int y = 0; y < 16; y++) {
                if(maze[x][y] == 3) {
                    return new int[] {x, y};
                }
            }
        }
        return null;
    }
}
