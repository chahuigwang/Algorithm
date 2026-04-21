import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author chahuigwang 
 * 1. 연결되지 않은 코어들의 좌표를 리스트에 저장한다.
 * 2. 각 코어를 연결하지 않는 경우 + 상하좌우 중 연결 가능한 방향에 연결하는 경우를 고려해 모든 경우의 수를 만든다.
 * 3. 백트래킹(연결한 전선 제거)
 * 4. 경우의 수가 완성되면 연결된 코어의 수와 최대 연결 코어 수를 비교하여 최대 연결 코어 수, 최소 전선의 길이를 갱신한다.
 */

class Solution {

    static class UnconnectedCore {
        int row;
        int col;

        UnconnectedCore(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int mapSize;
    static int[][] cellMap;

    static int maxConnectCount;
    static int minWireLength;

    static List<UnconnectedCore> unconnectedCores = new ArrayList<>();
    static int unconnectedCoreCount;

    public static void main(String[] args) throws IOException {
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            init();
            connectMaxCores(0, 0, 0);
            sb.append("#").append(testCaseNo).append(" ").append(minWireLength).append("\n");
        }
        System.out.println(sb);
    }

    static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine());
        cellMap = new int[mapSize][mapSize];

        unconnectedCores.clear();
        int cellInfo;
        for(int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0; col < mapSize; col++) {
                cellInfo = Integer.parseInt(st.nextToken());
                cellMap[row][col] = cellInfo;
                if (cellInfo == 1 && 0 < row && row < mapSize - 1 && 0 < col && col < mapSize - 1) {
                    unconnectedCores.add(new UnconnectedCore(row, col));
                }
            }
        }
        unconnectedCoreCount = unconnectedCores.size();

        maxConnectCount = Integer.MIN_VALUE;
        minWireLength = Integer.MAX_VALUE;
    }

    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};
    static final int DIRECTION_COUNT = 4;

    static void connectMaxCores(int coreIdx, int connectCount, int totalWireLength) {
        if(coreIdx == unconnectedCoreCount) {
            if(connectCount > maxConnectCount) {
                maxConnectCount = connectCount;
                minWireLength = totalWireLength;
            }
            else if(connectCount == maxConnectCount) {
                minWireLength = Math.min(minWireLength, totalWireLength);
            }
            return;
        }

        // 전선 연결 x
        connectMaxCores(coreIdx + 1, connectCount, totalWireLength);

        UnconnectedCore curCore = unconnectedCores.get(coreIdx);
        int row = curCore.row;
        int col = curCore.col;
        int wireLength;
        for(int direction = 0; direction < DIRECTION_COUNT; direction++) {
            if(canConnect(row, col, direction)) {
                wireLength = connectWire(row, col, direction);
                connectMaxCores(coreIdx + 1, connectCount + 1, totalWireLength + wireLength);
                removeWire(row, col, direction);
            }
        }
    }

    static boolean isInRange(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    static boolean canConnect(int row, int col, int direction) {
        int nextRow = row + DR[direction];
        int nextCol = col + DC[direction];
        while (isInRange(nextRow, nextCol)) {
            if(cellMap[nextRow][nextCol] != 0) return false;
            nextRow += DR[direction];
            nextCol += DC[direction];
        }
        return true;
    }

    static int connectWire(int row, int col, int direction) {
        int nextRow = row + DR[direction];
        int nextCol = col + DC[direction];
        int wireLength = 0;
        while (isInRange(nextRow, nextCol)) {
            cellMap[nextRow][nextCol] = 2;
            wireLength++;
            nextRow += DR[direction];
            nextCol += DC[direction];
        }
        return wireLength;
    }

    static void removeWire(int row, int col, int direction) {
        int nextRow = row + DR[direction];
        int nextCol = col + DC[direction];
        while (isInRange(nextRow, nextCol)) {
            cellMap[nextRow][nextCol] = 0;
            nextRow += DR[direction];
            nextCol += DC[direction];
        }
    }
}
