package BOJ.boj10972;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for(int idx = 0; idx < n; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        if(!nextPermutation()) {
            System.out.println(-1);
            return;
        }
        else {
            for(int e : arr) {
                sb.append(e).append(" ");
            }
        }
        System.out.println(sb);
    }

    static boolean nextPermutation() {
        int i = n - 1;
        while(i > 0 && arr[i-1] >= arr[i]) i--;
        if(i == 0) return false;

        int j = n-1;
        while(arr[i-1] >= arr[j]) j--;

        swap(i-1, j);
        reverse(i, n-1);

        return true;
    }

    static void swap(int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    static void reverse(int start, int end) {
        while(start < end) {
            swap(start++, end--);
        }
    }
}
