import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int height;
	static int width;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(calcMinTotalBridgeLength());
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		map = new int[height][width];
		for(int row = 0; row < height; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < width; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static boolean[][] visited;
	static int islandNo;
	static int islandCount;
	
	static void makeAllIslands() {
		visited = new boolean[height][width];
		islandNo = 1;
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(!visited[row][col] && map[row][col] == 1) {
					makeIsland(row, col, islandNo);
					islandNo++;
				}
			}
		}
		islandCount = islandNo - 1;
	}
	
	static final int[] DR = {-1, 1, 0, 0};
	static final int[] DC = {0, 0, -1, 1};
	static final int DIRECTION_COUNT = 4;
	
	static void makeIsland(int row, int col, int islandNo) {
		visited[row][col] = true;
		map[row][col] = islandNo;
		
		int nextRow, nextCol;
		for(int direction = 0; direction < DIRECTION_COUNT; direction++) {
			nextRow = row + DR[direction];
			nextCol = col + DC[direction];
			if(isInRange(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == 1)
				makeIsland(nextRow, nextCol, islandNo);
		}
	}
	
	static boolean isInRange(int row, int col) {
		return 0 <= row && row < height && 0 <= col && col < width;
	}
	
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
	
	static List<Bridge> bridges = new ArrayList<>();
	
	static void makeAllBridges() {
		int nextRow, nextCol;
		int length;
		int from, to;
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(map[row][col] != 0) {
					for(int direction = 0; direction < DIRECTION_COUNT; direction++) {
						nextRow = row + DR[direction];
						nextCol = col + DC[direction];
						if(isInRange(nextRow, nextCol) && map[nextRow][nextCol] == 0) {
							length = 0;
							while(isInRange(nextRow, nextCol)) {
								if(map[nextRow][nextCol] != 0) {
									from = map[row][col];
									to = map[nextRow][nextCol];
									if(from != to && length > 1) bridges.add(new Bridge(from, to, length));
									break;
								}
								nextRow += DR[direction];
								nextCol += DC[direction];
								length++;
							}
						}
					}
				}
			}
		}
		
		Collections.sort(bridges);
	}
	
	static int[] parents;
	
	static void makeSets() {
		parents = new int[islandCount + 1];
		for(int islandIdx = 1; islandIdx <= islandCount; islandIdx++) {
			parents[islandIdx] = islandIdx;
		}
	}
	
	static int find(int islandNo) {
		if(parents[islandNo] == islandNo) return islandNo;
		return parents[islandNo] = find(parents[islandNo]);
	}
	
	static boolean union(int island1, int island2) {
		int root1 = find(island1);
		int root2 = find(island2);
		
		if(root1 == root2) return false;
		
		parents[root2] = root1;
		return true;
	}
	
	static int calcMinTotalBridgeLength() {
		makeAllIslands();
		makeAllBridges();
		makeSets();
		
		int totalBridgeLength = 0;
		int connectCount = 0;
		for(Bridge bridge : bridges) {
			if(union(bridge.from, bridge.to)) {
				totalBridgeLength += bridge.length;
				if(++connectCount == islandCount - 1) return totalBridgeLength;
			}
		}
		return -1;
	}
}