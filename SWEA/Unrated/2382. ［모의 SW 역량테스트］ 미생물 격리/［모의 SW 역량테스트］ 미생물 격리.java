import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 2382. 미생물 격리
 * 
 * @author SSAFY
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 한 변에 있는 셀의 개수 , 격리 시간 , 미생물 군집의 개수를 입력받는다.
 *		2-2. 미생물 군집의 정보를 저장할 리스트를 초기화하고, 정보를 입력받아 리스트에 저장한다.
 *
 *		@see #countMicrobeAfterMHours()
 *		2-3. afterOneHour를 M번 반복한다.
 *
 *			@see #afterOneHour()
 *			
 *			@see #clearMicrobeMap()
 *			2-3-1. 위치 별 미생물 군집 정보를 저장할 2차원 배열을 초기화한다.
 *
 *			@see #moveForOneHour()
 *			2-3-2. 미생물을 한시간 뒤의 위치로 이동시킨다.
 *			2-3-3. 사라지는 미생물 군집을 제외하고 위치 별 미생물 군집 정보에 추가한다.
 *
 *			@see #processMerge()
 *			2-3-4. 모든 위치를 순회하며, 병합될 미생물 군집이 있다면 병합하고 다음 미생물 군집 정보로 저장한다.
 *
 *		2-4. 남아있는 미생물 수의 총합을 센다.
 *
 *		2-5. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {

	static class MicrobeGroup {
		int row;
		int col;
		int microbeCount;
		int direction;
		
		MicrobeGroup(int row, int col, int microbeCount, int direction) {
			this.row = row;
			this.col = col;
			this.microbeCount = microbeCount;
			this.direction = direction;
		}
	}
	
	static final int[] DR = {0, -1, 1, 0, 0};
	static final int[] DC = {0, 0, 0, -1, 1};
	
	static final int UP = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int RIGHT = 4;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int mapSize;
	static int hours;
	static int microbeGroupCount;
	
	static List<MicrobeGroup> microbeGroups = new ArrayList<>();
	static List<MicrobeGroup>[][] microbeMap;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			// 2-5. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(countMicrobeAfterMHours()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 한 변에 있는 셀의 개수 , 격리 시간 , 미생물 군집의 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		hours = Integer.parseInt(st.nextToken());
		microbeGroupCount = Integer.parseInt(st.nextToken());
		
		// 2-2. 미생물 군집의 정보를 저장할 리스트를 초기화하고, 정보를 입력받아 리스트에 저장한다.
		microbeGroups.clear();
		int row, col, microbeCount, direction;
		for(int microbeGroupIdx = 0; microbeGroupIdx < microbeGroupCount; microbeGroupIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			microbeCount = Integer.parseInt(st.nextToken());
			direction = Integer.parseInt(st.nextToken());
			microbeGroups.add(new MicrobeGroup(row, col, microbeCount, direction));
		}
	}
	
	static int countMicrobeAfterMHours() {
		for(int hour = 0; hour < hours; hour++) {
			afterOneHour();
		}
		return countMicrobe();
	}
	
	static void afterOneHour() {
		clearMicrobeMap();
		moveForOneHour();
		processMerge();
	}
	
	static void clearMicrobeMap() {
		// 2-3-1. 위치 별 미생물 군집 정보를 저장할 2차원 배열을 초기화한다.
		microbeMap = new ArrayList[mapSize][mapSize];
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				microbeMap[row][col] = new ArrayList<>();
			}
		}
	}
	
	static void moveForOneHour() {
		int nextRow, nextCol;
		int nextMicrobeCount;
		int nextDirection;
		
		// 2-3-2. 미생물을 한시간 뒤의 위치로 이동시킨다.
		for(MicrobeGroup microbeGroup : microbeGroups) {
			nextRow = microbeGroup.row + DR[microbeGroup.direction];
			nextCol = microbeGroup.col + DC[microbeGroup.direction];
			nextMicrobeCount = microbeGroup.microbeCount;
			nextDirection = microbeGroup.direction;
			
			// 가장자리 셀
			if(isOnBorder(nextRow, nextCol)) {
				nextMicrobeCount /= 2;
				nextDirection = reverseDirection(nextDirection);
			}
			
			if(nextMicrobeCount == 0) continue;
			
			// 2-3-3. 사라지는 미생물 군집을 제외하고 위치 별 미생물 군집 정보에 추가한다.
			microbeMap[nextRow][nextCol].add(new MicrobeGroup(nextRow, nextCol, nextMicrobeCount, nextDirection));
		}
	}
	
	static boolean isOnBorder(int row, int col) {
		return row == 0 || row == mapSize - 1 || col == 0 || col == mapSize - 1; 
	}
	
	static int reverseDirection(int direction) {
		if(direction == UP) return DOWN;
		else if(direction == DOWN) return UP;
		else if(direction == LEFT) return RIGHT;
		return LEFT;
	}
	
	static void processMerge() {
		List<MicrobeGroup> nextMicrobeGroups = new ArrayList<>();
		List<MicrobeGroup> curGroups;
		
		int maxMicrobeCount;
		int maxDirection;
		int totalMicrobeCount;
		// 2-3-4. 모든 위치를 순회하며, 병합될 미생물 군집이 있다면 병합하고 다음 미생물 군집 정보로 저장한다.
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				curGroups = microbeMap[row][col];

				if(curGroups.isEmpty()) continue;
				
				if(curGroups.size() == 1) {
					nextMicrobeGroups.add(curGroups.get(0));
					continue;
				}
				
				maxMicrobeCount = 0;
				maxDirection = 0;
				totalMicrobeCount = 0;
				
				for(MicrobeGroup microbeGroup : curGroups) {
					totalMicrobeCount += microbeGroup.microbeCount;
					
					if(microbeGroup.microbeCount > maxMicrobeCount) {
						maxMicrobeCount = microbeGroup.microbeCount;
						maxDirection = microbeGroup.direction;
					}
				}
				
				nextMicrobeGroups.add(new MicrobeGroup(row, col, totalMicrobeCount, maxDirection));
			}
		}
		
		microbeGroups = nextMicrobeGroups;
	}
	
	static int countMicrobe() {
		// 2-4. 남아있는 미생물 수의 총합을 센다.
		int totalCount = 0;
		for(MicrobeGroup microbeGroup : microbeGroups) {
			totalCount += microbeGroup.microbeCount;
		}
		return totalCount;
	}
}
