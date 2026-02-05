package BOJ.boj15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int[] arr;
    static boolean[] visited;
    static int[] perm;

    static int maxDepth;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = i+1;
        }

        visited = new boolean[n];
        perm = new int[m];
        maxDepth = m;

        permutation(0);
        System.out.println(sb.toString());
    }

    static void permutation(int depth) {
        if(depth == maxDepth) {
            print(perm);
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            perm[depth] = arr[i];

            permutation(depth + 1);

            visited[i] = false;
        }
    }

    static void print(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if(i < arr.length - 1) {
                sb.append(" ");
            }
        }
        sb.append("\n");
    }
}
