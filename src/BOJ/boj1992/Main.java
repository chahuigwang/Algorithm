package BOJ.boj1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;

    static int mapSize;
    static int[][] imageMap;

    public static void main(String[] args) throws IOException {
        init();
        compressImage(0, 0, mapSize);
        System.out.println(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        mapSize = Integer.parseInt(br.readLine().trim());
        imageMap = new int[mapSize][mapSize];
        for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            String inputLine = br.readLine().trim();
            for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                imageMap[rowIdx][colIdx] = inputLine.charAt(colIdx) - '0';
            }
        }
    }

    static void compressImage(int row, int col, int size) {
        int blackCount = 0;
        for(int rowIdx = row; rowIdx < row + size; rowIdx++) {
            for(int colIdx = col; colIdx < col + size; colIdx++) {
                blackCount += imageMap[rowIdx][colIdx];
            }
        }

        if(blackCount == size * size) sb.append("1");
        else if(blackCount == 0) sb.append("0");
        else {
            sb.append("(");
            int halfSize = size / 2;
            compressImage(row, col, halfSize);
            compressImage(row, col + halfSize, halfSize);
            compressImage(row + halfSize, col, halfSize);
            compressImage(row + halfSize, col + halfSize, halfSize);
            sb.append(")");
        }
    }
}
