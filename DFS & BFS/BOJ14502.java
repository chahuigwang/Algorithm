import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14502 {

    public static int n, m;
    public static int[][] map;
    public static ArrayList<int[]> zeroPos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == 0) zeroPos.add(new int[]{i, j});
                if(map[i][j] == 2) queue.offer(new int[]{i, j});
            }
        }

        int[][] tempMap;
        Queue<int[]> tempQueue;
        int maxCount = 0;

        for(int i = 0; i < zeroPos.size()-2; i++) {
            for(int j = i+1; j < zeroPos.size()-1; j++) {
                for(int k = j+1; k < zeroPos.size(); k++) {
                    int x1 = zeroPos.get(i)[0], y1 = zeroPos.get(i)[1];
                    int x2 = zeroPos.get(j)[0], y2 = zeroPos.get(j)[1];
                    int x3 = zeroPos.get(k)[0], y3 = zeroPos.get(k)[1];
                    tempMap = copyMap(map);
                    tempMap[x1][y1] = 1;
                    tempMap[x2][y2] = 1;
                    tempMap[x3][y3] = 1;
                    tempQueue = copyQueue(queue);
                    bfs(tempMap, tempQueue);
                    maxCount = Math.max(maxCount, countZeros(tempMap));
                }
            }
        }
        System.out.println(maxCount);
    }

    public static void bfs(int[][] map, Queue<int[]> queue) {
        int[] dx = {-1, 1, 0 ,0};
        int[] dy = {0, 0, -1, 1};
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                    map[nx][ny] = 2;
                }
            }
        }
    }

    public static int[][] copyMap(int[][] arr) {
        int[][] copiedArr = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                copiedArr[i][j] = arr[i][j];
            }
        }
        return copiedArr;
    }

    public static int countZeros(int[][] map) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0) count++;
            }
        }
        return count;
    }

    public static Queue<int[]> copyQueue(Queue<int[]> queue) {
        Queue<int[]> copiedQueue = new LinkedList<>();
        for(int[] arr : queue) {
            copiedQueue.offer(new int[]{arr[0], arr[1]});
        }
        return copiedQueue;
    }
}
