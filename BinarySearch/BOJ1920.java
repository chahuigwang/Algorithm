import java.util.Arrays;
import java.util.Scanner;

public class BOJ1920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int m = sc.nextInt();
        int[] targets = new int[m];
        for(int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        for(int t : targets) {
            System.out.println(isInArray(arr, t));
        }
    }

    public static int isInArray(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while(start <= end) {
            int mid = (start + end) / 2;

            if(arr[mid] == target) return 1;
            else if(arr[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return 0;
    }
}
