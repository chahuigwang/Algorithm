package BOJ.boj1260;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int v = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        for(int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        for(int i = 0; i < n+1; i++) {
            Collections.sort(graph.get(i));
        }

        dfs(graph, v, visited);
        System.out.println();
        visited = new boolean[n+1];
        bfs(graph, v, visited);
    }

    public static void dfs(List<List<Integer>> graph, int v, boolean[] visited) {
        System.out.print(v + " ");
        visited[v] = true;

        for(int neighbor : graph.get(v)) {
            if(!visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }
    }

    public static void bfs(List<List<Integer>> graph, int start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        System.out.print(start + " ");
        visited[start] = true;

        while(!queue.isEmpty()) {
            int v = queue.poll();

            for(int neighbor : graph.get(v)) {
                if(!visited[neighbor]) {
                    queue.offer(neighbor);
                    System.out.print(neighbor + " ");
                    visited[neighbor] = true;
                }
            }
        }
    }
}
