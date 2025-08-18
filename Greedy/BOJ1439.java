import java.util.Scanner;

public class BOJ1439 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        int count0 = 0, count1 = 0;
        char prev = s.charAt(0);
        if(prev == '0') count0++;
        else count1++;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != prev) {
                if(s.charAt(i) == '0') count0++;
                else count1++;
                prev = s.charAt(i);
            }
        }
        System.out.println(Math.min(count0, count1));
    }
}
