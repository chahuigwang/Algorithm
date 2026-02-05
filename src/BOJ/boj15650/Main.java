package BOJ.boj15650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static int[] arr;
    static int maxDepth;
    static int[] comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for(int idx = 0; idx < n; idx++) arr[idx] = idx + 1;
        maxDepth = m;
        comb = new int[maxDepth];

        combination(0, 0);
        System.out.println(sb.toString());
    }

    static void combination(int depth, int start) {
        if(depth == maxDepth) {
            print(comb);
            return;
        }

        for(int idx = start; idx < arr.length; idx++) {
            comb[depth] = arr[idx];
            combination(depth + 1, idx + 1);
        }
    }

    static void print(int[] arr) {
        for(int idx = 0; idx < arr.length; idx++) {
            sb.append(arr[idx]);
            if(idx < arr.length - 1) {
                sb.append(" ");
            }
        }
        sb.append("\n");
    }
}
