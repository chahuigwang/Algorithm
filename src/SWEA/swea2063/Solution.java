package SWEA.swea2063;

import java.util.*;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        String[] arr = sc.nextLine().split(" ");
        int[] nums = new int[N];

        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(nums);

        System.out.println(nums[N/2]);
    }
}
