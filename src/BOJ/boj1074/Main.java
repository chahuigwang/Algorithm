package BOJ.boj1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int mapSize;

    static int visitRow;
    static int visitCol;

    static long visitCount;

    public static void main(String[] args) throws IOException {
        init();
        visitMap(0, 0, mapSize, visitRow, visitCol);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        n = Integer.parseInt(st.nextToken());
        mapSize = (int) Math.pow(2, n);

        visitRow = Integer.parseInt(st.nextToken());
        visitCol = Integer.parseInt(st.nextToken());
    }

    static void visitMap(int row, int col, int size, int targetRow, int targetCol) {
        if(size == 1) {
            System.out.println(visitCount);
            return;
        }

        int halfSize = size / 2;
        if(row <= targetRow && targetRow < row + halfSize && col <= targetCol && targetCol < col + halfSize) { // 1사분면
            visitMap(row, col, halfSize, targetRow, targetCol);
        }
        else if(row <= targetRow && targetRow < row + halfSize && col + halfSize <= targetCol && targetCol < col + size) { // 2사분면
            visitCount += halfSize * halfSize;
            visitMap(row, col + halfSize, halfSize, targetRow, targetCol);
        }
        else if(row + halfSize <= targetRow && targetRow < row + size && col <= targetCol && targetCol < col + halfSize) { // 3사분면
            visitCount += halfSize * halfSize * 2;
            visitMap(row + halfSize, col, halfSize, targetRow, targetCol);
        }
        else { // 4사분면
            visitCount += halfSize * halfSize * 3;
            visitMap(row + halfSize, col + halfSize, halfSize, targetRow, targetCol);
        }
    }
}

