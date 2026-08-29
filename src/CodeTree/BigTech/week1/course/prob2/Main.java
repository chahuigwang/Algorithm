package CodeTree.BigTech.week1.course.prob2;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    // 북, 동, 남, 서
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static int x;
    static int y;
    static int direction;
    static String commands;

    public static void main(String[] args) throws IOException {
        init();
        move();
        print();
    }

    static void init() throws IOException {
        x = 0;
        y = 0;
        direction = 0;
        commands = br.readLine().trim();
    }

    static void move() {
        char cmd;
        for(int cmdIdx = 0; cmdIdx < commands.length(); cmdIdx++) {
            cmd = commands.charAt(cmdIdx);
            if(cmd == 'L') {
                direction = (direction + 3) % 4;
            }
            else if(cmd == 'R') {
                direction = (direction + 1) % 4;
            }
            else {
                x += dx[direction];
                y += dy[direction];
            }
        }
    }

    static void print() {
        sb.append(x).append(" ").append(y);
        System.out.println(sb);
    }
}
