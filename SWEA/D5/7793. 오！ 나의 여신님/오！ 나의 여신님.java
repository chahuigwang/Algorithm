import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 7793. 오! 나의 여신님
 * [전략]
 * 1. 수연이의 현재 위치 저장
 * 2. 악마의 손아귀 사용 - 악마 전파
 * 3. 수연이 이동
 * @author SSAFY
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 맵의 높이와 너비를 입력받는다.
 *		2-2. 맵의 정보를 저장할 2차원 배열을 생성한다.
 *		2-3. 수연이의 위치와 악마의 위치를 저장할 큐를 초기화한다.
 *		2-4. 맵의 정보를 입력받아 저장하고, 수연이와 악마의 위치를 큐에 넣는다.
 *		2-5. 방문 여부를 저장할 배열을 생성한다.
 *
 *		@see #findGod()
 *		2-6. 수연이의 위치가 담긴 큐가 빌 때까지,
 *			2-6-1. 1초 증가한다.
 *			2-6-2. 악마의 손아귀를 사용하여 악마를 확산시킨다.
 *			2-6-3. 수연이를 이동시킨다.
 *			2-6-4. 여신을 만나면 종료한다.
 *			2-6-5. 여신을 만났다면 소요된 시간을 정답으로 저장한다.
 *
 */

class Solution {

	static class Pos {
		int row;
		int col;
		
		Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int height;
	static int width;
	static char[][] devilMap;
	static final char SUYEON = 'S';
	static final char GOD = 'D';
	static final char ROCK = 'X';
	static final char DEVIL = '*';
	static final char GROUND = '.';
	
	static Queue<Pos> suyeonQueue = new ArrayDeque<>();
	static Queue<Pos> devilQueue = new ArrayDeque<>();
	
	static boolean[][] visited;
	
	static int answer;
	
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
			findGod();
			sb.append("#").append(testCaseNo).append(" ").append(answer != -1 ? answer : "GAME OVER").append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 맵의 높이와 너비를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		// 2-2. 맵의 정보를 저장할 2차원 배열을 생성한다.
		devilMap = new char[height][width];
		String rowInput;
		// 2-3. 수연이의 위치와 악마의 위치를 저장할 큐를 초기화한다.
		suyeonQueue.clear();
		devilQueue.clear();
		for(int row = 0; row < height; row++) {
			rowInput = br.readLine().trim();
			for(int col = 0; col < width; col++) {
				// 2-4. 맵의 정보를 입력받아 저장하고, 수연이와 악마의 위치를 큐에 넣는다.
				devilMap[row][col] = rowInput.charAt(col);
				if(devilMap[row][col] == SUYEON) suyeonQueue.offer(new Pos(row, col));
				if(devilMap[row][col] == DEVIL) devilQueue.offer(new Pos(row, col));
			}
		}
		// 2-5. 방문 여부를 저장할 배열을 생성한다.
		visited = new boolean[height][width];
	}
	
	static void findGod() {
		answer = -1;
		int second = 0;
		boolean foundGod = false;
		
		int devilQueueSize, suyeonQueueSize;
		Pos curDevilPos, curSuyeonPos;
		int curDevilRow, curDevilCol, curSuyeonRow, curSuyeonCol;
		int nextDevilRow, nextDevilCol, nextSuyeonRow, nextSuyeonCol;
		// 2-6. 수연이의 위치가 담긴 큐가 빌 때까지,
		while(!suyeonQueue.isEmpty()) {
			// 2-6-1. 1초 증가한다.
			second++;
			
			// 2-6-2. 악마의 손아귀를 사용하여 악마를 확산시킨다.
			devilQueueSize = devilQueue.size();
			while(devilQueueSize-- > 0) {
				curDevilPos = devilQueue.poll();
				curDevilRow = curDevilPos.row;
				curDevilCol = curDevilPos.col;
				for(int direction = UP; direction <= RIGHT; direction++) {
					nextDevilRow = curDevilRow + DR[direction];
					nextDevilCol = curDevilCol + DC[direction];
					if(!isInRange(nextDevilRow, nextDevilCol)) continue;
					if(devilMap[nextDevilRow][nextDevilCol] == GROUND) {
						devilMap[nextDevilRow][nextDevilCol] = DEVIL;
						devilQueue.offer(new Pos(nextDevilRow, nextDevilCol));
					}
				}
			}
			
			// 2-6-3. 수연이를 이동시킨다.
			suyeonQueueSize = suyeonQueue.size();
			while(suyeonQueueSize-- > 0) {
				curSuyeonPos = suyeonQueue.poll();
				curSuyeonRow = curSuyeonPos.row;
				curSuyeonCol = curSuyeonPos.col;
				for(int direction = UP; direction <= RIGHT; direction++) {
					nextSuyeonRow = curSuyeonRow + DR[direction];
					nextSuyeonCol = curSuyeonCol + DC[direction];
					if(!isInRange(nextSuyeonRow, nextSuyeonCol) || visited[nextSuyeonRow][nextSuyeonCol]) continue;
					// 2-6-4. 여신을 만나면 종료한다.
					if(devilMap[nextSuyeonRow][nextSuyeonCol] == GOD) {
						foundGod = true;
						break;
					}
					else if(devilMap[nextSuyeonRow][nextSuyeonCol] == GROUND) {
						devilMap[curSuyeonRow][curSuyeonCol] = GROUND;
						devilMap[nextSuyeonRow][nextSuyeonCol] = SUYEON;
						suyeonQueue.offer(new Pos(nextSuyeonRow, nextSuyeonCol));
						visited[nextSuyeonRow][nextSuyeonCol] = true;
					}
				}
				if(foundGod) break;
			}
			// 2-6-5. 여신을 만났다면 소요된 시간을 정답으로 저장한다.
			if(foundGod) {
				answer = second;
				break;
			}
		}
	}
	
	static boolean isInRange(int row, int col) {
		return 0 <= row && row < height && 0 <= col && col < width;
	}
}
