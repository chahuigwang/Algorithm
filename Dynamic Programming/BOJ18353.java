import java.util.Scanner;

public class BOJ18353 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arrPower = new int[n];
        int[] dp = new int[n];

        for(int i = 0; i < n; i++) {
            arrPower[i] = sc.nextInt();
            dp[i] = 1;
        }

        reverse(arrPower);

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(arrPower[j] < arrPower[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 0;
        for(int len : dp) {
            max = Math.max(max, len);
        }
        System.out.println(n - max);
    }

    public static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;

        while(left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
