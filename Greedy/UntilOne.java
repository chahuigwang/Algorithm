import java.util.Scanner;

public class UntilOne {
    public static void main(String[] args) {
        int n;
        int k;

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        int count = 0;
        while(n != 1) {
            if(n%k == 0) {
                n = n/k;
            }
            else {
                n--;
            }
            count++;
        }

        System.out.println(count);
    }
}
