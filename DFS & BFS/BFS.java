import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static boolean[] visited = new boolean[9];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

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

    public static void main(String[] args) {
        for(int i = 0; i < 9; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(1).addAll(Arrays.asList(2, 3, 8));
        graph.get(2).addAll(Arrays.asList(1, 7));
        graph.get(3).addAll(Arrays.asList(1, 4, 5));
        graph.get(4).addAll(Arrays.asList(3, 5));
        graph.get(5).addAll(Arrays.asList(3, 4));
        graph.get(6).add(7);
        graph.get(7).addAll(Arrays.asList(2, 6, 8));
        graph.get(8).addAll(Arrays.asList(1, 7));

        bfs(1);
    }
}
