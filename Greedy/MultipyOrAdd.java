import java.util.Scanner;

public class MultipyOrAdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        int result = s.charAt(0) - '0';
        for(int i = 1; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            if(result < 2 || num < 2)
                result += num;
            else
                result *= num;
        }
        System.out.println(result);
    }
}
