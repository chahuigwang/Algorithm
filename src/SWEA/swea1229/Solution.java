package SWEA.swea1229;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Solution
{
    static List<Integer> code;

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int lengthCode = sc.nextInt();

            code = new LinkedList<>();
            for(int i = 0; i < lengthCode; i++) {
                code.add(sc.nextInt());
            }

            int numCommand = sc.nextInt();
            sc.nextLine();
            String command = sc.nextLine();

            LinkedList<String> cmd = new LinkedList<>();
            Arrays.stream(command.split(" "))
                    .forEach(str -> cmd.add(str));

            while(!cmd.isEmpty()) {
                String cur = cmd.pop();
                if(cur.equals("I")) {
                    int x = Integer.parseInt(cmd.pop());
                    int y = Integer.parseInt(cmd.pop());

                    List<Integer> sList = new ArrayList<>();
                    for(int i = 0; i < y; i++) {
                        sList.add(Integer.parseInt(cmd.pop()));
                    }

                    int[] s = sList.stream().mapToInt(Integer::intValue).toArray();
                    I(x, y, s);
                }
                else if(cur.equals("D")) {
                    int x = Integer.parseInt(cmd.pop());
                    int y = Integer.parseInt(cmd.pop());
                    D(x, y);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ");

            for(int idx = 0; idx < 10; idx++) {
                sb.append(code.get(idx)).append(" ");
            }

            System.out.println(sb);
        }
    }

    static void I(int x, int y, int[] s) {
        for(int idx = 0; idx < y; idx++) {
            code.add(x + idx, s[idx]);
        }
    }

    static void D(int x, int y) {
        for(int iter = 0; iter < y; iter++) {
            code.remove(x);
        }
    }
}
