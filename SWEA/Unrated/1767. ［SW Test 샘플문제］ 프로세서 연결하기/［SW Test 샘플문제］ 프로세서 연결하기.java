import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
	
	static List<UnconnectedCore> unconnectedCores = new ArrayList<>();
	static int unconncetedCoreCount;
	
	static int maxConnectedCoreCount;
	static int minTotalWireLength;
	
	public static void main(String[] args) throws IOException {
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			connectMaxCores(0, 0, 0);
			sb.append("#").append(testCaseNo).append(" ").append(minTotalWireLength).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		mapSize = Integer.parseInt(br.readLine().trim());
		
		cellMap = new int[mapSize][mapSize];
		unconnectedCores.clear();
		int cellInfo;
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < mapSize; col++) {
				cellInfo = Integer.parseInt(st.nextToken());
				cellMap[row][col] = cellInfo;
				if(isNotOnBoundary(row, col) && cellInfo == 1) {
					unconnectedCores.add(new UnconnectedCore(row, col));
				}
			}
		}
		unconncetedCoreCount = unconnectedCores.size();
		
		maxConnectedCoreCount = Integer.MIN_VALUE;
		minTotalWireLength = Integer.MAX_VALUE;
	}
	
	static boolean isNotOnBoundary(int row, int col) {
		return 0 < row && row < mapSize - 1 && 0 < col && col < mapSize - 1;
	}
	
	static final int[] DR = {-1, 1, 0, 0};
	static final int[] DC = {0, 0, -1, 1};
	static final int DIRECTION_COUNT = 4;
	
	static void connectMaxCores(int coreIdx, int connectCount, int totalWireLength) {
		if(coreIdx == unconncetedCoreCount) {
			if(connectCount > maxConnectedCoreCount) {
				maxConnectedCoreCount = connectCount;
				minTotalWireLength = totalWireLength;
			}
			else if(connectCount == maxConnectedCoreCount) minTotalWireLength = Math.min(minTotalWireLength, totalWireLength);
			return;
		}
		
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
	
	static boolean canConnect(int row, int col, int direction) {
		int nextRow = row + DR[direction];
		int nextCol = col + DC[direction];
		while(isInRange(nextRow, nextCol)) {
			if(cellMap[nextRow][nextCol] != 0) return false;
			nextRow += DR[direction];
			nextCol += DC[direction];
		}
		return true;
	}
	
	static boolean isInRange(int row, int col) {
		return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
	}
	
	static int connectWire(int row, int col, int direction) {
		int wireLength = 0;
		int nextRow = row + DR[direction];
		int nextCol = col + DC[direction];
		while(isInRange(nextRow, nextCol)) {
			wireLength++;
			cellMap[nextRow][nextCol] = 2;
			nextRow += DR[direction];
			nextCol += DC[direction];
		}
		return wireLength;
	}
	
	static void removeWire(int row, int col, int direction) {
		int nextRow = row + DR[direction];
		int nextCol = col + DC[direction];
		while(isInRange(nextRow, nextCol)) {
			cellMap[nextRow][nextCol] = 0;
			nextRow += DR[direction];
			nextCol += DC[direction];
		}
	}
}
