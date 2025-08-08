import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2536 {

    static class Bus {
        int x1, y1, x2, y2;
        Bus(int x1, int y1, int x2, int y2) {
            this.x1 = Math.min(x1, x2);
            this.y1 = Math.min(y1, y2);
            this.x2 = Math.max(x1, x2);
            this.y2 = Math.max(y1, y2);
        }

        boolean contains(int x, int y) {
            return x1 <= x && x <= x2 && y1 <= y && y <= y2;
        }

        boolean isConnected(Bus bus) {
            int sx = Math.max(this.x1, bus.x1);
            int ex = Math.min(this.x2, bus.x2);
            int sy = Math.max(this.y1, bus.y1);
            int ey = Math.min(this.y2, bus.y2);
            return sx <= ex && sy <= ey;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();

        Bus[] buses = new Bus[k+1];
        for(int i = 0; i < k; i++) {
            int busNum = sc.nextInt() - 1;
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            buses[busNum] = new Bus(x1, y1, x2, y2);
        }

        int sx = sc.nextInt();
        int sy = sc.nextInt();
        int dx = sc.nextInt();
        int dy = sc.nextInt();

        Queue<Integer> queue = new LinkedList<>();
        int[] numBuses = new int[k+1];

        for(int i = 0; i < k; i++) {
            if(buses[i].contains(sx, sy)) {
                queue.offer(i);
                numBuses[i] = 1;
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            if(buses[cur].contains(dx, dy)) {
                System.out.println(numBuses[cur]);
                return;
            }

            for(int i = 0; i < k; i++) {
                if(numBuses[i] == 0 && buses[cur].isConnected(buses[i])) {
                    numBuses[i] = numBuses[cur] + 1;
                    queue.offer(i);
                }
            }
        }
    }
}
