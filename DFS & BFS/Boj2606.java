import java.util.ArrayList;
import java.util.Scanner;

public class Boj2606 {
    public static ArrayList<ArrayList<Integer>> network = new ArrayList<>();
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        for(int i = 0; i <= n; i++) {
            network.add(new ArrayList<>());
        }
        visited = new boolean[n+1];

        for(int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            network.get(u).add(v);
            network.get(v).add(u);
        }
        System.out.println(dfs(1) - 1);
    }

    public static int dfs(int x) {
        int count = 1;

        visited[x] = true;
        for(int neighbor : network.get(x)) {
            if(!visited[neighbor]) {
                count += dfs(neighbor);
            }
        }
        return count;
    }
}
