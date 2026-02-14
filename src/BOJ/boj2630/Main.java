package BOJ.boj2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int mapSize;
    static int[][] paperMap;

    static int whitePaperCount;
    static int bluePaperCount;

    public static void main(String[] args) throws IOException {
        init();
        cutPaper(0, 0, mapSize);
        printAnswer();
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        mapSize = Integer.parseInt(br.readLine().trim());
        paperMap = new int[mapSize][mapSize];

        for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                paperMap[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void cutPaper(int row, int col, int size) {
        int blueCellCount = 0;
        for(int rowIdx = row; rowIdx < row + size; rowIdx++) {
            for(int colIdx = col; colIdx < col + size; colIdx++) {
                blueCellCount += paperMap[rowIdx][colIdx];
            }
        }

        if(blueCellCount == size * size) bluePaperCount++;
        else if(blueCellCount == 0) whitePaperCount++;
        else {
            int halfSize = size / 2;
            cutPaper(row, col, halfSize);
            cutPaper(row, col + halfSize, halfSize);
            cutPaper(row + halfSize, col, halfSize);
            cutPaper(row + halfSize, col + halfSize, halfSize);
        }
    }

    static void printAnswer() {
        sb.append(whitePaperCount).append("\n").append(bluePaperCount);
        System.out.println(sb);
    }
}
