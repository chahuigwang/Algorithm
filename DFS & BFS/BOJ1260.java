import java.util.*;

public class BOJ1260 {
    public static boolean[] visited = new boolean[1001];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int v = Integer.parseInt(input[2]);

        for(int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            String[] edge = sc.nextLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(ArrayList<Integer> list : graph) {
            Collections.sort(list);
        }

        dfs(v);
        System.out.println();
        visited = new boolean[1001];
        bfs(v);
    }

    public static void dfs(int x) {
        visited[x] = true;
        System.out.print(x + " ");

        for(int neighbor : graph.get(x)) {
            if(!visited[neighbor])
                dfs(neighbor);
        }
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for(int neighbor : graph.get(node)) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }
}
