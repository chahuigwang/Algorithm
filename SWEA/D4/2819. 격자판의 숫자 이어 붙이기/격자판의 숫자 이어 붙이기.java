import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * SWEA 2819. 격자판의 숫자 이어 붙이기
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스 마다,
 *		
 *		@see #initTestCase()
 *		2-1. 격자판의 정보를 입력받아 2차원 배열에 저장한다.
 *		2-2. 만들 수 있는 서로 다른 일곱 자리 수들을 저장할 Set을 초기화한다.
 *
 *		@see #calcAllPossibleNumCount()
 *		2-3. 모든 위치를 시작 위치로 탐색을 시작한다.
 *			
 *			@see #makePossibleNums(int, int, int, String)
 *			2-3-1. 상하좌우 네 방향을 검사하며 이동 가능한 격자의 숫자를 이어붙인다.
 *			2-3-2. 이동 횟수가 6번이 되면 완성된 숫자를 Set에 넣고 종료한다.
 *
 *		2-4. Set의 크기를 반환한다.
 *
 *		2-5. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAP_SIZE = 4;
	static String[][] numMap = new String[MAP_SIZE][MAP_SIZE];
	static Set<String> possibleNums = new HashSet<>();
	
	static final int[] DR = {-1, 1, 0, 0};
	static final int[] DC = {0, 0, -1, 1};
	static final int UP = 0;
	static final int RIGHT = 3;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스 마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			// 2-5. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(calcAllPossibleNumCount()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 격자판의 정보를 입력받아 2차원 배열에 저장한다.
		for(int row = 0; row < MAP_SIZE; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < MAP_SIZE; col++) {
				numMap[row][col] = st.nextToken();
			}
		}
		// 2-2. 만들 수 있는 서로 다른 일곱 자리 수들을 저장할 Set을 초기화한다.
		possibleNums.clear();
	}
	
	static int calcAllPossibleNumCount() {
		// 2-3. 모든 위치를 시작 위치로 탐색을 시작한다.
		for(int row = 0; row < MAP_SIZE; row++) {
			for(int col = 0; col < MAP_SIZE; col++) {
				makePossibleNums(row, col, 0, numMap[row][col]);
			}
		}
		// 2-4. Set의 크기를 반환한다.
		return possibleNums.size();
	}
	
	static void makePossibleNums(int row, int col, int moveCount, String str) {
		// 2-3-2. 이동 횟수가 6번이 되면 완성된 숫자를 Set에 넣는다.
		if(moveCount == 6) {
			possibleNums.add(str);
			return;
		}
		
		int nextRow, nextCol;
		// 2-3-1. 상하좌우 네 방향을 검사하며 이동 가능한 격자의 숫자를 이어붙인다.
		for(int direction = UP; direction <= RIGHT; direction++) {
			nextRow = row + DR[direction];
			nextCol = col + DC[direction];
			if(isInRange(nextRow, nextCol))
				makePossibleNums(nextRow, nextCol, moveCount + 1, str + numMap[nextRow][nextCol]);
		}
	}
	
	static boolean isInRange(int row, int col) {
		return 0 <= row && row < MAP_SIZE && 0 <= col && col < MAP_SIZE;
	}
}
