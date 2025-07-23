import java.util.Scanner;

public class UpDownLeftRight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        int x = 1, y = 1;
        String[] moves = {"L", "R", "U", "D"};
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        String[] plans = sc.nextLine().split(" ");
        for(String plan : plans) {
            int nx = x, ny = y;
            for(int i = 0; i < moves.length; i++) {
                if(plan.equals(moves[i])) {
                    nx = x + dx[i];
                    ny = y + dy[i];
                }
            }
            if(nx < 1 || nx > n || ny < 1 || ny > n)
                continue;
            x = nx;
            y = ny;
        }
        System.out.println(x + " " + y);
    }
}
