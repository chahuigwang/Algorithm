import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1247. 최적 경로
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 고객의 수를 입력받는다.
 *		2-2. 회사의 좌표를 입력받는다.
 *		2-3. 집의 좌표를 입력받는다.
 *		2-4. N개의 고객 좌표를 입력받는다.
 *		2-5. 최단 경로의 이동거리를 정수의 최댓값으로 초기화한다.
 *		2-6. 방문 순서를 저장할 배열을 생성한다.
 *		2-7. 방문 여부를 저장할 배열을 생성한다.
 *
 *		@see #findShortestPath(int)
 *		2-8. 가능한 모든 방문 순서를 만든다.
 *		2-9. 방문 순서가 완성되면 경로의 거리를 계산하고 최솟값을 갱신한다.
 *		
 */

class Solution {

	static class Position {
		int x;
		int y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int clientCount;
	
	static Position companyPos;
	static Position homePos;
	static Position[] clientPosList;
	
	static int shortestPathDist;
	static int[] visitOrder;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			findShortestPath(0);
			sb.append("#").append(testCaseNo).append(" ").append(shortestPathDist).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 고객의 수를 입력받는다.
		clientCount = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		
		// 2-2. 회사의 좌표를 입력받는다.
		int companyX = Integer.parseInt(st.nextToken());
		int companyY = Integer.parseInt(st.nextToken());
		companyPos = new Position(companyX, companyY);
		
		// 2-3. 집의 좌표를 입력받는다.
		int homeX = Integer.parseInt(st.nextToken());
		int homeY = Integer.parseInt(st.nextToken());
		homePos = new Position(homeX, homeY);
		
		// 2-4. N개의 고객 좌표를 입력받는다.
		clientPosList = new Position[clientCount];
		int clientX, clientY;
		for(int clientIdx = 0; clientIdx < clientCount; clientIdx++) {
			clientX = Integer.parseInt(st.nextToken());
			clientY = Integer.parseInt(st.nextToken());
			clientPosList[clientIdx] = new Position(clientX, clientY);
		}
		
		// 2-5. 최단 경로의 이동거리를 정수의 최댓값으로 초기화한다.
		shortestPathDist = Integer.MAX_VALUE;
		
		// 2-6. 방문 순서를 저장할 배열을 생성한다.
		visitOrder = new int[clientCount];
		
		// 2-7. 방문 여부를 저장할 배열을 생성한다.
		visited = new boolean[clientCount];
	}
	
	static void findShortestPath(int visitIdx) {
		// 2-9. 방문 순서가 완성되면 경로의 거리를 계산하고 최솟값을 갱신한다.
		if(visitIdx == clientCount) {
			shortestPathDist = Math.min(shortestPathDist, calcPathDistance());
			return;
		}
		
		// 2-8. 가능한 모든 방문 순서를 만든다.
		for(int clientIdx = 0; clientIdx < clientCount; clientIdx++) {
			if(visited[clientIdx]) continue;
			
			// 현재 고객 방문
			visited[clientIdx] = true;
			visitOrder[visitIdx] = clientIdx;
			findShortestPath(visitIdx + 1);
			
			// 현재 고객 방문 x
			visited[clientIdx] = false;
		}
	}
	
	static int calcPathDistance() {
		int companyX = companyPos.x;
		int companyY = companyPos.y;
		int homeX = homePos.x;
		int homeY= homePos.y;
		int clientX = clientPosList[visitOrder[0]].x;
		int clientY = clientPosList[visitOrder[0]].y;
		int distance = Math.abs(companyX - clientX) + Math.abs(companyY - clientY);
		int prevClientX, prevClientY;
		for(int clientIdx = 1; clientIdx < clientCount; clientIdx++) {
			prevClientX = clientPosList[visitOrder[clientIdx - 1]].x;
			prevClientY = clientPosList[visitOrder[clientIdx - 1]].y;
			clientX = clientPosList[visitOrder[clientIdx]].x;
			clientY = clientPosList[visitOrder[clientIdx]].y;
			distance += Math.abs(prevClientX - clientX) + Math.abs(prevClientY - clientY);
		}
		clientX = clientPosList[visitOrder[clientCount - 1]].x;
		clientY = clientPosList[visitOrder[clientCount - 1]].y;
		distance += Math.abs(clientX - homeX) + Math.abs(clientY - homeY);
		return distance;
	}
}
