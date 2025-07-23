import java.util.Scanner;

public class RoyalKnight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String location_str = sc.next();

        char[] location = location_str.toCharArray();
        int x = location[1] - '0';
        int y = location[0] - 'a' + 1;

        int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

        int count = 0;
        int nx, ny;
        for(int i = 0; i < 8; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if(nx >= 1 && nx <= 8 && ny >= 1 && ny <= 8)
                count++;
        }
        System.out.println(count);
    }
}
