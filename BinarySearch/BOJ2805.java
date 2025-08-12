import java.util.Scanner;

public class BOJ2805 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] trees = new int[n];
        int start = 0;
        int end = 0;
        for(int i = 0; i < n; i++) {
            trees[i] = sc.nextInt();
            end = Math.max(end, trees[i]);
        }

        int result = 0;
        while(start <= end) {
            int mid = (start + end) / 2;

            long currentTrees = totalTrees(trees, mid);
            if(currentTrees >= m) {
                result = mid;
                start = mid + 1;
            }
            else end = mid - 1;
        }
        System.out.println(result);
    }

    public static long totalTrees(int[] trees, int height) {
        long total = 0;
        for(int tree : trees) {
            long len = tree - height;
            if(len > 0) total += len;
        }
        return total;
    }
}
