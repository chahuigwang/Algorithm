package SWEA.swea1959;

import java.util.*;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();

            int len = n > m ? n : m;
            int[] a = new int[len];
            int[] b = new int[len];

            String[] sa = sc.nextLine().split(" ");
            String[] sb = sc.nextLine().split(" ");

            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(sa[i]);
            }

            for(int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(sb[i]);
            }

            int max = totalMultiply(a, b, len);
            if(n == m) {
                System.out.println("#" + test_case + " " + max);
                continue;
            }
            else if(n > m) {
                for(int i = 0; i < n-m; i++) {
                    pushRight(b, len);
                    int mul = totalMultiply(a, b, len);
                    max = max > mul ? max : mul;
                }
            }
            else {
                for(int i = 0; i < m-n; i++) {
                    pushRight(a, len);
                    int mul = totalMultiply(a, b, len);
                    max = max > mul ? max : mul;
                }
            }

            System.out.println("#" + test_case + " " + max);
        }
    }

    public static int totalMultiply(int[] arr1, int[] arr2, int len) {
        int result = 0;
        for(int i = 0; i < len; i++) {
            result += arr1[i]*arr2[i];
        }
        return result;
    }

    public static void pushRight(int[] arr, int len) {
        for(int i = len-2; i >= 0; i--) {
            arr[i+1] = arr[i];
        }
        arr[0] = 0;
    }
}
