package SWEA.swea1228;

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

            String[] commandArr = command.split("I");

            for(int idx = 1; idx <= numCommand; idx++) {
                int[] arr = Arrays.stream(commandArr[idx].trim().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                int x = arr[0];
                int y = arr[1];
                int[] s = Arrays.copyOfRange(arr, 2, arr.length);
                I(x, y, s);
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
}
