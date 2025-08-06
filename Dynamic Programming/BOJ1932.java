import java.util.Scanner;

public class BOJ1932 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] triangle = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i+1; j++) {
                triangle[i][j] = sc.nextInt();
            }
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i+1; j++) {
                int leftUp = (j - 1) >= 0 ? triangle[i-1][j-1] : 0;
                int up = (j < i) ? triangle[i - 1][j] : 0;

                triangle[i][j] = Math.max(leftUp, up) + triangle[i][j];
            }
        }
        int[] bottom = triangle[n-1];
        int max = 0;
        for(int b : bottom) {
            max = Math.max(max, b);
        }
        System.out.println(max);
    }
}
