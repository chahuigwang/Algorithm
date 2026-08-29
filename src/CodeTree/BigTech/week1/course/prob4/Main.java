package CodeTree.BigTech.week1.course.prob4;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int gridSize;
    static int time;

    static int row;
    static int col;
    static String directionString;
    static int direction;

    // U, R, D, L
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    static Map<String, Integer> directionMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();
        move();
        print();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        gridSize = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        directionString = st.nextToken();

        directionMap.put("U", 0);
        directionMap.put("R", 1);
        directionMap.put("D", 2);
        directionMap.put("L", 3);

        direction = directionMap.get(directionString);
    }

    static void move() {
        int newRow, newCol;
        while(time-- > 0) {
            newRow = row + dr[direction];
            newCol = col + dc[direction];
            if(isInRange(newRow, newCol)) {
                row = newRow;
                col = newCol;
            }
            else {
                direction = (direction + 2) % 4;
            }
        }
    }

    static boolean isInRange(int row, int col) {
        return 0 < row && row <= gridSize && 0 < col && col <= gridSize;
    }

    static void print() {
        sb.append(row).append(" ").append(col);
        System.out.println(sb);
    }
}
