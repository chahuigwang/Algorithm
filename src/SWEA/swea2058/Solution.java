package SWEA.swea2058;

import java.util.*;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        String N = sc.next();
        String[] nums = N.split("");

        int sum = 0;
        for(String num : nums) {
            sum += Integer.parseInt(num);
        }

        System.out.println(sum);
    }
}
