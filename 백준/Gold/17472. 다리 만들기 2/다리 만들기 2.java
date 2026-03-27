import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 17472. 다리 만들기 2
 * 
 * [전략]
 * 1. 가능한 다리를 모두 만들어 리스트에 저장한다.
 * 2. 다리 리스트 오름차순 정렬
 * 3. 크루스칼
 * 
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	
 *	@see #init()
 *	1. 지도의 세로 크기와 가로 크기를 입력받는다.
 *	2. 지도의 정보를 저장할 2차원 배열을 생성하고, 정보를 입력받아 배열에 저장한다.
 *	3. 방문 여부를 저장할 2차원 배열을 생성한다.
 *
 *	@see #makeAllIslands()
 *	4. 땅을 연결하여 섬을 만들고, 섬 번호를 매긴다. (DFS)
 *
 *	@see #makeAllBridges()
 *	5. 연결 가능한 모든 다리를 만들고, 다리 리스트에 저장한다.
 *		5-1. 지도의 모든 칸을 순회하며,
 *			5-1-1. 바다 칸에서 상하좌우에 섬이 있는지 검사한다.
 *			5-1-2. 상하좌우에 섬이 있다면 반대 방향으로 이동한다.
 *			5-1-3. 섬을 만났다면,
 *				5-1-3-1. 출발한 섬과 다른 섬이고 길이가 1보다 크다면 다리 리스트에 추가한다.
 *
 *	6. 다리 리스트를 길이 기준으로 오름차순 정렬한다.
 *
 *	@see #calcMinTotalLength()
 *	7. 크루스칼 알고리즘으로 모든 섬을 연결하는 다리 길이의 최솟값을 구한다.
 *
 *	8. 정답을 출력한다.
 */

public class Main {
	
	static class Bridge implements Comparable<Bridge> {
		int from;
		int to;
		int length;
		
		Bridge(int from, int to, int length) {
			this.from = from;
			this.to = to;
			this.length = length;
		}

		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(this.length, o.length);
		}		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int height;
	static int width;
	static int[][] map;
	
	static final int SEA = 0;
	static final int LAND = 1;
	
	static boolean[][] visited;
	
	static int islandCount;
	
	static final int[] DR = {-1, 1, 0, 0};
	static final int[] DC = {0, 0, -1, 1};
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	
	static List<Bridge> bridges = new ArrayList<>();
	
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		init();
		makeAllIslands();
		makeAllBridges();
		// 8. 정답을 출력한다.
		System.out.println(calcMinTotalLength());
	}
	
	static void init() throws IOException {
		// 1. 지도의 세로 크기와 가로 크기를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		// 2. 지도의 정보를 저장할 2차원 배열을 생성하고, 정보를 입력받아 배열에 저장한다.
		map = new int[height][width];
		for(int row = 0; row < height; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < width; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 3. 방문 여부를 저장할 2차원 배열을 생성한다.
		visited = new boolean[height][width];
	}
	
	static void makeAllIslands() {
		// 4. 땅을 연결하여 섬을 만들고, 섬 번호를 매긴다. (DFS)
		islandCount = 0;
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(!visited[row][col] && map[row][col] == LAND) {
					makeIsland(row, col, ++islandCount);
				}
			}
		}
	}
	
	static void makeIsland(int row, int col, int islandNo) {
		visited[row][col] = true;
		map[row][col] = islandNo;
		
		int nextRow, nextCol;
		for(int direction = UP; direction <= RIGHT; direction++) {
			nextRow = row + DR[direction];
			nextCol = col + DC[direction];
			if(isInRange(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == LAND) {
				makeIsland(nextRow, nextCol, islandNo);
			}
		}
	}
	
	static boolean isInRange(int row, int col) {
		return 0 <= row && row < height && 0 <= col && col < width;
	}
	
	static void makeAllBridges() {
		// 5. 연결 가능한 모든 다리를 만들고, 다리 리스트에 저장한다.
		int from, to, length;
		int nextRow, nextCol;
		int reversedDirection;
		
		// 5-1. 지도의 모든 칸을 순회하며,
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				// 5-1-1. 바다 칸에서 상하좌우에 섬이 있는지 검사한다.
				if(map[row][col] == SEA) {
					for(int direction = UP; direction <= RIGHT; direction++) {
						nextRow = row + DR[direction];
						nextCol = col + DC[direction];
						if(!isInRange(nextRow, nextCol)) continue;
						
						// 5-1-2. 상하좌우에 섬이 있다면 반대 방향으로 이동한다.
						if(map[nextRow][nextCol] != SEA) {
							from = map[nextRow][nextCol];
							length = 0;
							reversedDirection = getReversedDirection(direction);
							nextRow = row + DR[reversedDirection];
							nextCol = col + DC[reversedDirection];
							while(isInRange(nextRow, nextCol)) {
								length++;
								// 5-1-3. 섬을 만났다면,
								if(map[nextRow][nextCol] != SEA) { // 섬을 만남
									to = map[nextRow][nextCol];
									// 5-1-3-1. 출발한 섬과 다른 섬이고 길이가 1보다 크다면 다리 리스트에 추가한다.
									if(from != to && length > 1) bridges.add(new Bridge(from, to, length));
									break;
								}
								nextRow += DR[reversedDirection];
								nextCol += DC[reversedDirection];
							}
						}
					}
				}
			}
		}
		
		// 6. 다리 리스트를 길이 기준으로 오름차순 정렬한다.
		Collections.sort(bridges);
	}
	
	static int getReversedDirection(int direction) {
		if(direction % 2 == 0) return direction + 1;
		else return direction - 1;
	}
	
	static void makeSets() {
		parents = new int[islandCount + 1];
		for(int islandNo = 1; islandNo <= islandCount; islandNo++) {
			parents[islandNo] = islandNo;
		}
	}
	
	static int find(int islandNo) {
		if(parents[islandNo] == islandNo) return islandNo;
		return parents[islandNo] = find(parents[islandNo]);
	}
	
	static boolean union(int islandNo1, int islandNo2) {
		int rootIsland1 = find(islandNo1);
		int rootIsland2 = find(islandNo2);
		
		if(rootIsland1 == rootIsland2) return false;
		
		parents[rootIsland2] = rootIsland1;
		return true;
	}
	
	static int calcMinTotalLength() {
		// 7. 크루스칼 알고리즘으로 모든 섬을 연결하는 다리 길이의 최솟값을 구한다.
		makeSets();
		int totalLength = 0;
		int connectCount = 0;
		for(Bridge bridge : bridges) {
			if(union(bridge.from, bridge.to)) {
				totalLength += bridge.length;
				if(++connectCount == islandCount - 1) break;
			}
		}
		return (connectCount < islandCount - 1) ? -1 : totalLength;
	}
}
