package SWEA.swea1767;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SW Expert Academy 1767
 * @author chahuigwang
 *
 *	@see #main(String[])
 * 	1. 테스트 케이스 개수를 입력받는다.
 * 	2. 각 테스트 케이스 마다,
 *
 * 		@see #inputTestCase()
 * 		2-1. 맵 크기를 입력받는다.
 * 		2-2. 멕시노스 배열을 생성한다.
 * 		2-3. 멕시노스 배열의 정보를 입력받아 배열을 초기화한다.
 * 		2-4. 전원이 연결되지 않은 코어를 unconnectedCoreList에 추가한다.
 *
 * 		@see #addWires()
 * 		2-5. 전원이 연결되지 않은 코어들의 상하좌우를 보며 해당 행 또는 열에 코어가 없다면 전선을 WireList에 추가한다.
 *
 * 		@see #selectedWireList
 * 		2-6. WireList에서 전선끼리 교차하거나 코어 하나에 전선이 두개 연결되는 경우를 제외하고 최대한 많은 코어에 전원을 연결하며 전선 길이의 합을 갱신한다.
 */

class Solution {

    static class UnconnectedCore {
        int x;
        int y;

        UnconnectedCore(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Wire {
        UnconnectedCore core;
        int endX;
        int endY;
        int length;

        Wire(UnconnectedCore core, int endX, int endY) {
            this.core = core;
            this.endX = endX;
            this.endY = endY;
            length = Math.abs(core.x - endX) + Math.abs(core.y - endY);
        }
    }

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int mapSize;
    static int[][] cellMap;

    static List<UnconnectedCore> unconnectedCoreList;

    static List<Wire> wireList; // 연결 가능한 모든 전선
    static List<Wire> selectedWireList;
    static int minTotalWireLenght;
    static int maxCoreCount;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 1. 테스트 케이스 개수를 입력받는다.
        int numTestCase = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스 마다,
        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            inputTestCase();
            wireList = new ArrayList<>();
            selectedWireList = new ArrayList<>();
            minTotalWireLenght = Integer.MAX_VALUE;
            maxCoreCount = 0;

            addWires();
            selectWires(0, 0);

            sb.append("#").append(testCaseNo).append(" ").append(minTotalWireLenght).append("\n");
        }
        System.out.println(sb);
    }

    static void inputTestCase() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim()); // 2-1. 맵 크기를 입력받는다.
        cellMap = new int[mapSize][mapSize]; // 2-2. 멕시노스 배열을 생성한다.

        unconnectedCoreList = new ArrayList<>();

        // 2-3. 멕시노스 배열의 정보를 입력받아 배열을 초기화한다.
        for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                int curPoint = Integer.parseInt(st.nextToken());
                cellMap[rowIdx][colIdx] = curPoint;
                if(curPoint == 1 && !(rowIdx == 0 || colIdx == 0 || rowIdx == mapSize - 1 || colIdx == mapSize - 1)) {
                    unconnectedCoreList.add(new UnconnectedCore(rowIdx, colIdx)); // 2-4. 전원이 연결되지 않은 코어를 unconnectedCoreList에 추가한다.
                }
            }
        }
    }

    static void addWires() {
        for(UnconnectedCore core : unconnectedCoreList) {
            int curX = core.x;
            int curY = core.y;
            for(int dIdx = 0; dIdx < 4; dIdx++) {
                for(int move = 1; move < mapSize - 1; move++) {
                    int nx = curX + dx[dIdx]*move;
                    int ny = curY + dy[dIdx]*move;
                    if(0 <= nx && nx < mapSize && 0 <= ny && ny < mapSize && cellMap[nx][ny] == 1) break; // core가 있다면 패스
                    if(nx == 0 || nx == mapSize - 1 || ny == 0 || ny == mapSize - 1) { // 가장자리까지 도달했다면
                        wireList.add(new Wire(core, nx, ny));
                    }
                }
            }
        }
    }

    // powerSet
    static void selectWires(int elementIdx, int totalWireLength) {
        if(elementIdx == wireList.size()) {
            int connectedCoreCount = selectedWireList.size();

            if (connectedCoreCount < maxCoreCount) return;
            else if (connectedCoreCount > maxCoreCount) {
                maxCoreCount = connectedCoreCount;
                minTotalWireLenght = totalWireLength;
            }
            else {
                minTotalWireLenght = Math.min(minTotalWireLenght, totalWireLength);
            }
            return;
        }

        Wire curWire = wireList.get(elementIdx);
        if(isIntersect(curWire) || hasSameCore(curWire)) { // 교차되거나 이미 코어에 전원이 연결되어있다면
            selectWires(elementIdx + 1, totalWireLength);
        }
        else {
            // 전선 선택
            selectedWireList.add(curWire);
            selectWires(elementIdx + 1, totalWireLength + curWire.length);
            // 전선 선택 x
            selectedWireList.remove(curWire);
            selectWires(elementIdx + 1, totalWireLength);
        }
    }

    // 교차 검사
    static boolean isIntersect(Wire wire) {
        int thisMinX = Math.min(wire.core.x, wire.endX);
        int thisMinY = Math.min(wire.core.y, wire.endY);
        int thisMaxX = Math.max(wire.core.x, wire.endX);
        int thisMaxY = Math.max(wire.core.y, wire.endY);
        boolean isThisVertical = thisMinX != thisMaxX;

        for(Wire curWire : selectedWireList) {
            int curMinX = Math.min(curWire.core.x, curWire.endX);
            int curMinY = Math.min(curWire.core.y, curWire.endY);
            int curMaxX = Math.max(curWire.core.x, curWire.endX);
            int curMaxY = Math.max(curWire.core.y, curWire.endY);
            boolean isCurVertical = curMinX != curMaxX;

            if(isCurVertical == isThisVertical) continue;

            if(isCurVertical && !isThisVertical) { // curWire는 세로, wire는 가로
                int verticalY = curMinY;
                int horizonX = thisMinX;
                if(thisMinY <= verticalY && verticalY <= thisMaxY && curMinX <= horizonX && horizonX <= curMaxX) return true;
            }
            if(!isCurVertical && isThisVertical) { // curWire는 가로, wire는 세로
                int verticalY = thisMinY;
                int horizonX = curMinX;
                if(curMinY <= verticalY && verticalY <= curMaxY && thisMinX <= horizonX && horizonX <= thisMaxX) return true;
            }
        }
        return false;
    }

    // 코어에 전원이 연결되어있는지 여부 검사
    static boolean hasSameCore(Wire wire) {
        for(Wire curWire : selectedWireList) {
            if(curWire.core.equals(wire.core)) return true;
        }
        return false;
    }
}

