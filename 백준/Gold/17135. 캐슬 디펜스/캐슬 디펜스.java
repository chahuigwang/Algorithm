import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize;
    static int colSize;
    static int attackDistLimit;
    static int[][] originalMap;
    static int[][] gameMap;
    static int maxKillCount;

    static final int ARCHER_COUNT = 3;
    static int[] archerPositions = new int[ARCHER_COUNT];

    public static void main(String[] args) throws IOException {
        init();
        placeArchers(0, 0);
        System.out.println(maxKillCount);
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        attackDistLimit = Integer.parseInt(st.nextToken());

        originalMap = new int[rowSize][colSize];
        gameMap = new int[rowSize][colSize];
        for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int colIdx = 0; colIdx < colSize; colIdx++) {
                originalMap[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
        maxKillCount = 0;
    }

    static void copyGameMap() {
        for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for(int colIdx = 0; colIdx < colSize; colIdx++) {
                gameMap[rowIdx][colIdx] = originalMap[rowIdx][colIdx];
            }
        }
    }

    static int attackEnemies() {
        Set<List<Integer>> targetPositions = new HashSet<>();
        for(int archerPosition : archerPositions) {
            if(findTargetPosition(archerPosition) != null) targetPositions.add(findTargetPosition(archerPosition));
        }

        for(List<Integer> targetPosition : targetPositions) {
            int targetRow = targetPosition.get(0);
            int targetCol = targetPosition.get(1);
            gameMap[targetRow][targetCol] = 0;
        }

        return targetPositions.size();
    }

    static List<Integer> findTargetPosition(int archerPosition) {
        int targetRow = rowSize, targetCol = colSize;
        int closestDist = Integer.MAX_VALUE;
        int distance;
        for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for(int colIdx = 0; colIdx < colSize; colIdx++) {
                if(gameMap[rowIdx][colIdx] == 1) { // 적이 있는 칸
                    distance = (rowSize - rowIdx) + Math.abs(archerPosition - colIdx); // 적과의 거리 계산
                    if(distance <= attackDistLimit) { // 사정 거리 안에 들어왔다면
                        if(distance < closestDist) {
                            closestDist = distance;
                            targetRow = rowIdx;
                            targetCol = colIdx;
                        }
                        else if(distance == closestDist) {
                            if(colIdx < targetCol) {
                                targetCol = colIdx;
                                targetRow = rowIdx;
                            }
                        }
                    }
                }
            }
        }
        if(targetRow == rowSize && targetCol == colSize) return null;

        return Arrays.asList(targetRow, targetCol);
    }

    static void moveAfterOneTurn() {
        for(int rowIdx = rowSize - 1; rowIdx >= 0; rowIdx--) {
            for(int colIdx = 0; colIdx < colSize; colIdx++) {
                if(gameMap[rowIdx][colIdx] == 1) { // 적이 있는 칸
                    gameMap[rowIdx][colIdx] = 0;
                    if(rowIdx < rowSize - 1) gameMap[rowIdx + 1][colIdx] = 1;
                }
            }
        }
    }

    static boolean isGameOver() {
        for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for(int colIdx = 0; colIdx < colSize; colIdx++) {
                if(gameMap[rowIdx][colIdx] == 1) return false;
            }
        }
        return true;
    }

    static int castleDefense() {
        int totalKillCount = 0;
        while (!isGameOver()) {
            totalKillCount += attackEnemies();
            moveAfterOneTurn();
        }
        return totalKillCount;
    }

    static void placeArchers(int archerIdx, int positionIdx) {
        if(archerIdx == ARCHER_COUNT) {
            copyGameMap();
            maxKillCount = Math.max(maxKillCount, castleDefense());
            return;
        }

        if(positionIdx == colSize) return;

        archerPositions[archerIdx] = positionIdx;
        placeArchers(archerIdx + 1, positionIdx + 1);

        placeArchers(archerIdx, positionIdx + 1);
    }
}
