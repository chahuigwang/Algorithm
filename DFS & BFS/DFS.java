import java.util.ArrayList;
import java.util.Arrays;

public class DFS {
    public static boolean[] visited = new boolean[9];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void dfs(int x) {
        visited[x] = true;
        System.out.print(x + " ");

        for(int neighbor : graph.get(x)) {
            if(!visited[neighbor])
                dfs(neighbor);
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

        dfs(1);
    }
}
