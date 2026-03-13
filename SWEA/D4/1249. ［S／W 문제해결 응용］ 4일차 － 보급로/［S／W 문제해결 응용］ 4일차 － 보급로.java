import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * SWEA 1249. 보급로
 * @author SSAFY
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 지도의 한변의 크기를 입력받는다.
 *		2-2. 지도의 정보를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
 *
 *		@see #calcMinTime()
 *		2-3. 다익스트라 기법으로 최소 시간을 계산한다.
 *
 *		2-4. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {
	
	static class Node implements Comparable<Node> {
		int row;
		int col;
		int time;
		
		Node(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int mapSize;
	static int[][] map;
	
	static final int INF = (int)1e9;
	
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
			sb.append("#").append(testCaseNo).append(" ").append(calcMinTime()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 지도의 한변의 크기를 입력받는다.
		mapSize = Integer.parseInt(br.readLine().trim());
		// 2-2. 지도의 정보를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
		map = new int[mapSize][mapSize];
		String rowInput;
		for(int row = 0; row < mapSize; row++) {
			rowInput = br.readLine().trim();
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = rowInput.charAt(col) - '0';
			}
		}
	}
	
	static int calcMinTime() {
		int[][] minTime = new int[mapSize][mapSize];
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				minTime[row][col] = INF;
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));
		minTime[0][0] = 0;
		
		Node curNode;
		int curRow, curCol;
		int curTime;
		int nextRow, nextCol;
		int nextViaCurTime;
		while(!pq.isEmpty()) {
			curNode = pq.poll();
			curRow = curNode.row;
			curCol = curNode.col;
			curTime = curNode.time;
			
			if(minTime[curRow][curCol] < curTime) continue;
			
			for(int direction = UP; direction <= RIGHT; direction++) {
				nextRow = curRow + DR[direction];
				nextCol = curCol + DC[direction];
				if(!isInRange(nextRow, nextCol)) continue;
				nextViaCurTime = minTime[curRow][curCol] + map[nextRow][nextCol];
				if(nextViaCurTime < minTime[nextRow][nextCol]) {
					minTime[nextRow][nextCol] = nextViaCurTime;
					pq.offer(new Node(nextRow, nextCol, nextViaCurTime));
				}
			}
		}
		
		return minTime[mapSize - 1][mapSize - 1];
	}
	
	static boolean isInRange(int row, int col) {
		return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
	}
}
