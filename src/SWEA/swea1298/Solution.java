package SWEA.swea1298;

import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String target = sc.next();

            int cnt = 0;
            char cmp = '0';
            for(int idx = 0; idx < target.length(); idx++) {
                char cur = target.charAt(idx);
                if(cur != cmp) {
                    cnt++;
                    cmp = cur;
                }
            }

            System.out.println("#" + test_case + " " + cnt);
        }
    }
}
