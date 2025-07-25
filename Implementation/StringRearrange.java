import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StringRearrange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        List<Character> list = new ArrayList<>();
        int sum = 0;

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                list.add(c);
            } else {
                sum += c - '0';
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            sb.append(c);
        }
        sb.append(sum);
        System.out.println(sb.toString());
    }
}