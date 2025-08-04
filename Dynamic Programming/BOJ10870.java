import java.util.Scanner;

public class BOJ10870 {

    public static long[] d;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if(n == 0) {
            System.out.println(0);
            return;
        }
        else if(n == 1 || n == 2) {
            System.out.println(1);
            return;
        }

        d = new long[n+1];
        d[1] = 1;
        d[2] = 1;

        for(int i = 3; i <= n; i++) {
            d[i] = d[i-1] + d[i-2];
        }
        System.out.println(d[n]);
    }
}
