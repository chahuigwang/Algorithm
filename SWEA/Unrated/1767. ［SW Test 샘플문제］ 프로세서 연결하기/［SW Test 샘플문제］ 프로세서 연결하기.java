import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 1767. [SW Test 샘플문제] 프로세서 연결하기
 * @author chahuigwang
 * 
 * 	@see #main(String[])
 * 	1. 테스트 케이스 개수를 입력받는다.
 * 	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 프로세서의 한 변의 cell 개수를 입력받는다.
 *		2-2. 프로세서의 초기상태를 저장할 2차원 배열을 생성한다.
 *		2-3. 연결되지 않은 코어의 위치를 저장할 리스트를 초기화한다.
 *		2-4. 프로세서의 초기 상태를 입력받아 2차원 배열과 리스트에 저장한다.
 *		2-5. 최대 연결 코어 개수와 최소 전선 길이의 합을 초기화한다. 
 *
 *		@see #calcMinTotalWireLength(int, int, int)
 *		2-6. 연결되지 않은 모든 코어에서
 *			2-6-1. 코어를 연결하지 않고 다음 코어로 넘어간다.
 *			2-6-2. 상하좌우 방향에 연결 가능한 방향이 있다면
 *				2-6-2-1. 전선을 연결하고 다음 코어로 넘어간다.
 *				2-6-2-2. 연결한 전선을 제거하고 다음 방향으로 넘어간다.
 *		2-7. 모든 코어를 확인했다면 연결된 코어 개수를 비교한다.
 *			2-7-1. 연결된 코어 개수가 최대 연결 코어 개수보다 많다면, 최대 연결 코어 개수와 최소 전선 길이의 합을 갱신한다.
 *			2-7-2. 연결된 코어 개수가 최대 연결 코어 개수와 같다면, 최소 전선 길이의 합을 갱신한다.
 *
 *		2-8. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {

	static class UnConnectedCore {
		int row;
		int col;
		
		UnConnectedCore(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int mapSize;
	static int[][] processorMap;
	static final int CORE = 1;
	
	static List<UnConnectedCore> unConnectedCores = new ArrayList<>();
	static int unConnectedCoreCount;
	
	static int maxConnectedCoreCount;
	static int minTotalWireLength;
	
	static final int[] DR = {-1, 1, 0, 0};
	static final int[] DC = {0, 0, -1, 1};
	static final int UP = 0;
	static final int RIGHT = 3;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			calcMinTotalWireLength(0, 0, 0);
			// 2-8. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(minTotalWireLength).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 프로세서의 한 변의 cell 개수를 입력받는다.
		mapSize = Integer.parseInt(br.readLine().trim());
		
		// 2-2. 프로세서의 초기상태를 저장할 2차원 배열을 생성한다.
		processorMap = new int[mapSize][mapSize];
		
		// 2-3. 연결되지 않은 코어의 위치를 저장할 리스트를 초기화한다.
		unConnectedCores.clear();
		
		// 2-4. 프로세서의 초기 상태를 입력받아 2차원 배열과 리스트에 저장한다.
		int cellInfo;
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < mapSize; col++) {
				cellInfo = Integer.parseInt(st.nextToken());
				processorMap[row][col] = cellInfo;
				if(cellInfo == CORE && 1 <= row && row < mapSize - 1 && 1 <= col && col < mapSize - 1)
					unConnectedCores.add(new UnConnectedCore(row, col));
			}
		}
		unConnectedCoreCount = unConnectedCores.size();
		
		// 2-5. 최대 연결 코어 개수와 최소 전선 길이의 합을 초기화한다. 
		maxConnectedCoreCount = Integer.MIN_VALUE;
		minTotalWireLength = Integer.MAX_VALUE;
	}
	
	static void calcMinTotalWireLength(int coreIdx, int connectedCoreCount, int totalWireLength) {
		// 2-7. 모든 코어를 확인했다면 연결된 코어 개수를 비교한다.
		if(coreIdx == unConnectedCoreCount) {
			// 2-7-1. 연결된 코어 개수가 최대 연결 코어 개수보다 많다면, 최대 연결 코어 개수와 최소 전선 길이의 합을 갱신한다.
			if(connectedCoreCount > maxConnectedCoreCount) {
				maxConnectedCoreCount = connectedCoreCount;
				minTotalWireLength = totalWireLength;
			}
			// 2-7-2. 연결된 코어 개수가 최대 연결 코어 개수와 같다면, 최소 전선 길이의 합을 갱신한다.
			else if(connectedCoreCount == maxConnectedCoreCount) {
				minTotalWireLength = Math.min(minTotalWireLength, totalWireLength);
			}
			return;
		}
		
		// 2-6. 연결되지 않은 모든 코어에서
		UnConnectedCore curCore = unConnectedCores.get(coreIdx);
		int row = curCore.row;
		int col = curCore.col;
		int wireLength;
		
		// 2-6-1. 코어를 연결하지 않고 다음 코어로 넘어간다.
		calcMinTotalWireLength(coreIdx + 1, connectedCoreCount, totalWireLength);
		
		// 2-6-2. 상하좌우 방향에
		for(int direction = UP; direction <= RIGHT; direction++) {
			// 연결 가능한 방향이 있다면
			if(canConnect(row, col, direction)) {
				// 2-6-2-1. 전선을 연결하고 다음 코어로 넘어간다.
				wireLength = connectWire(row, col, direction);
				calcMinTotalWireLength(coreIdx + 1,
						connectedCoreCount + 1,
						totalWireLength + wireLength);
				// 2-6-2-2. 연결한 전선을 제거하고 다음 방향으로 넘어간다.
				removeWire(row, col, direction);
			}
		}
	}
	
	static boolean canConnect(int row, int col, int direction) {
		int nextRow = row + DR[direction];
		int nextCol = col + DC[direction];
		while(isInRange(nextRow, nextCol)) {
			if(processorMap[nextRow][nextCol] != 0) return false;
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
			processorMap[nextRow][nextCol] = 2;
			wireLength++;
			nextRow += DR[direction];
			nextCol += DC[direction];
		}
		return wireLength;
	}
	
	static void removeWire(int row, int col, int direction) {
		int nextRow = row + DR[direction];
		int nextCol = col + DC[direction];
		while(isInRange(nextRow, nextCol)) {
			processorMap[nextRow][nextCol] = 0;
			nextRow += DR[direction];
			nextCol += DC[direction];
		}
	}
}
