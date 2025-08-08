import java.util.Scanner;

public class CuttingRiceCake {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] heightArr = new int[n];
        int start = 0;
        int end = 0;
        for(int i = 0; i < n; i++){
            heightArr[i] = sc.nextInt();
            end = Math.max(end, heightArr[i]);
        }

        int result = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            int total = amountRiceCake(heightArr, mid);

            if(total < m) end = mid - 1;
            else {
                result = mid;
                start = mid + 1;
            }
        }

        System.out.println(result);
    }

    public static int amountRiceCake(int[] heightArr, int machineHeight) {
        int sum = 0;
        for(int height : heightArr) {
            if(height > machineHeight) sum += height - machineHeight;
        }
        return sum;
    }
}
