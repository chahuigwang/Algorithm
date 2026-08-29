package CodeTree.BigTech.week1.course.prob1;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int moveCount;
    static int x;
    static int y;

    static Map<String, Integer> directionMap = new HashMap<>();
    // 서, 남, 북, 동
    static final int[] DX = {-1, 0, 0, 1};
    static final int[] DY = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        init();
        move();
        print();
    }

    static void init() throws IOException {
        moveCount = Integer.parseInt(br.readLine().trim());
        x = 0;
        y = 0;

        directionMap.put("W", 0);
        directionMap.put("S", 1);
        directionMap.put("N", 2);
        directionMap.put("E", 3);
    }

    static void move() throws IOException {
        String direction;
        int directionIdx;
        int distance;
        while(moveCount-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            direction = st.nextToken();
            distance = Integer.parseInt(st.nextToken());
            directionIdx = directionMap.get(direction);
            x += DX[directionIdx] * distance;
            y += DY[directionIdx] * distance;
        }
    }

    static void print() {
        sb.append(x).append(" ").append(y);
        System.out.println(sb);
    }
}
