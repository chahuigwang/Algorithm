package SWEA.swea3499;

import java.util.ArrayList;
import java.util.List;
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
            int n = sc.nextInt();
            sc.nextLine();
            String input = sc.nextLine();
            String[] cards = input.split(" ");

            List<String> firstHalf = new ArrayList<>();
            List<String> secondHalf = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                if(i < (n+1) / 2) {
                    firstHalf.add(cards[i]);
                }
                else {
                    secondHalf.add(cards[i]);
                }
            }

            int minSize = Math.min(firstHalf.size(), secondHalf.size());

            List<String> shuffled = new ArrayList<>();
            for(int i = 0; i < minSize; i++) {
                shuffled.add(firstHalf.get(i));
                shuffled.add(secondHalf.get(i));
            }

            if(firstHalf.size() > secondHalf.size()) {
                shuffled.add(firstHalf.get(firstHalf.size() - 1));
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ");

            shuffled.forEach(str -> sb.append(str).append(" "));

            System.out.println(sb.toString().trim());
        }
    }
}
