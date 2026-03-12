import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 5656. 벽돌 깨기
 * [전략]
 * - 구슬 n개를 놓을 위치와 순서를 고려하여 중복 순열을 완성한다.
 * - 순열이 완성될 때마다 완성된 순서대로 벽돌을 깨고 남은 벽돌의 최솟값을 갱신한다.
 * 
 * @author chahuigwang
 *	
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 구슬의 개수, 맵의 너비, 맵의 높이를 입력받는다.
 *		2-2. 벽돌들의 정보를 입력 받을 2차원 배열을 생성하고, 정보를 입력받아 저장한다.
 *		2-3. 구슬을 쏠 위치의 순서를 저장할 배열을 생성한다.
 *		2-4. 최소 남은 벽돌의 수를 저장할 변수를 정수의 최댓값으로 초기화한다.
 *
 *		@see #calcLeftBrickCount()
 *		2-5. 구슬을 쏠 위치의 순서 중복 순열을 만든다.
 *		2-6. 중복 순열이 완성되면 남은 벽돌의 수를 계산한다.
 *
 *			@see #calcLeftBrickCount(int)
 *			2-6-1. 원본 맵을 복사해 게임 맵으로 사용한다.
 *			2-6-2. 완성된 중복 순열 순서대로 구슬을 쏜다.
 *
 *				@see #shootBall
 *				2-6-2-1. 맨 위에 있는 벽돌을 찾는다.
 *				2-6-2-2. 맨 위에 있는 벽돌을 제거한다.
 *				2-6-2-3. 공중에 떠있는 벽돌들을 밑으로 떨어뜨린다.
 *
 *			2-6-3. 남아있는 벽돌의 수를 반환한다.
 *
 *		2-7. 최소 남은 벽돌의 수와 현재 완성한 중복 순열에서의 남은 벽돌의 수 중 작은 값을 최솟값으로 저장한다.
 *		
 *		2-8. 테스트 케이스와 함께 정답을 출력한다.
 */

class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int ballCount;
	static int width;
	static int height;
	static int[][] brickMap;
	
	static int[] ballPositions; // ballPosition[i] : i번째에 구슬을 쏠 위치
	static int minLeftBrickCount;
	
	static int[][] gameMap;
	
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
			calcMinLeftBrickCount(0);
			// 2-8. 테스트 케이스와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(minLeftBrickCount).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 구슬의 개수, 맵의 너비, 맵의 높이를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		ballCount = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		// 2-2. 벽돌들의 정보를 입력 받을 2차원 배열을 생성하고, 정보를 입력받아 저장한다.
		brickMap = new int[height][width];
		for(int row = 0; row < height; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < width; col++) {
				brickMap[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		// 2-3. 구슬을 쏠 위치의 순서를 저장할 배열을 생성한다.
		ballPositions = new int[ballCount];
		// 2-4. 최소 남은 벽돌의 수를 저장할 변수를 정수의 최댓값으로 초기화한다.
		minLeftBrickCount = Integer.MAX_VALUE;
	}
	
	static void calcMinLeftBrickCount(int selectIdx) {
		// 2-6. 중복 순열이 완성되면 남은 벽돌의 수를 계산한다.
		if(selectIdx == ballCount) {
			// 2-7. 최소 남은 벽돌의 수와 현재 완성한 중복 순열에서의 남은 벽돌의 수 중 작은 값을 최솟값으로 저장한다.
			minLeftBrickCount = Math.min(minLeftBrickCount, calcLeftBrickCount());
			return;
		}
		
		// 2-5. 구슬을 쏠 위치의 순서 중복 순열을 만든다.
		for(int ballPosition = 0; ballPosition < width; ballPosition++) {
			ballPositions[selectIdx] = ballPosition;
			calcMinLeftBrickCount(selectIdx + 1);
		}
	}
	
	static void copyMap() {
		gameMap = new int[height][width];
		for(int row = 0; row < height; row++) {
			gameMap[row] = brickMap[row].clone();
		}
	}
	
	static int calcLeftBrickCount() {
		// 2-6-1. 원본 맵을 복사해 게임 맵으로 사용한다.
		copyMap();
		// 2-6-2. 완성된 중복 순열 순서대로 구슬을 쏜다.
		for(int position : ballPositions) {
			shootBall(position);
		}
		// 2-6-3. 남아있는 벽돌의 수를 반환한다.
		return countLeftBrick();
	}
	
	static void shootBall(int col) {
		for(int row = 0; row < height; row++) {
			// 2-6-2-1. 맨 위에 있는 벽돌을 찾는다.
			if(gameMap[row][col] != 0) { // 벽돌이 있는 위치
				// 2-6-2-2. 맨 위에 있는 벽돌을 제거한다.
				removeBrick(row, col);
				// 2-6-2-3. 공중에 떠있는 벽돌들을 밑으로 떨어뜨린다.
				downBrick();
				break;
			}
		}
	}
	
	static void removeBrick(int row, int col) {
		int range = gameMap[row][col] - 1;
		gameMap[row][col] = 0;
		if(range == 0) return;
		int nextRow, nextCol;
		for(int direction = UP; direction <= RIGHT; direction++) {
			for(int dist = 1; dist <= range; dist++) {
				nextRow = row + DR[direction]*dist;
				nextCol = col + DC[direction]*dist;
				if(isInRange(nextRow, nextCol) && gameMap[nextRow][nextCol] != 0)
					removeBrick(nextRow, nextCol);
			}
		}
	}
	
	static void downBrick() {
		int brickNum; // 벽돌에 적혀있는 숫자
		int dropRow; // 벽돌을 떨어뜨릴 행
		for(int col = 0; col < width; col++) {
			if(isRowAllEmpty(col)) continue;
			dropRow = height - 1;
			for(int row = height - 1; row >= 0; row--) {
				if(gameMap[row][col] != 0) {
					brickNum = gameMap[row][col];
					gameMap[row][col] = 0;
					gameMap[dropRow][col] = brickNum;
					dropRow--;
				}
			}
		}
	}
	
	static boolean isRowAllEmpty(int col) {
		for(int row = 0; row < height; row++) {
			if(gameMap[row][col] != 0) return false;
		}
		return true;
	}
	
	static boolean isInRange(int row, int col) {
		return 0 <= row && row < height && 0 <= col && col < width;
	}
	
	static int countLeftBrick() {
		int leftBrickCount = 0;
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(gameMap[row][col] != 0) leftBrickCount++;
			}
		}
		return leftBrickCount;
	}
}
