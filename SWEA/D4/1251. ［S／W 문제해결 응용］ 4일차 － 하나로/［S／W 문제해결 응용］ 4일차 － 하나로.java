import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 1251. 하나로
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 섬의 개수를 입력받는다.
 *		2-2. 섬들의 x좌표와 y좌표를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
 *		2-3. 환경 부담 세율 실수를 입력받는다.
 *		2-4. MST 포함 여부를 저장할 배열을 생성한다.
 *		2-5. MST에서 정점으로 가는 최소 가중치를 저장할 배열을 생성하고 INF로 초기화한다.
 *
 *		@see #prim()
 *		2-6. 0번 섬을 시작점으로 선택하고, minEdge를 0으로 설정한다.
 *		2-7. 모든 정점이 MST에 포함될 때까지,
 *			2-7-1. 아직 MST에 포함되지 않은 섬 중 MST에 들어오는 비용이 가장 작은 섬을 선택한다.
 *			2-7-2. 선택한 섬을 MST에 포함한다.
 *			2-7-3. 새로 포함한 섬을 기준으로 아직 방문하지 않은 다른 섬들의 최소 연결 거리를 갱신한다.
 *		2-8. MST의 전체 거리의 제곱의 합을 반환한다.
 *
 *		@see #calcMstCost()
 *		2-9. 환경 부담 세율과 MST의 전체 거리의 제곱의 합을 곱한 값을 계산한다.
 *
 *		2-10. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int islandCount;
	static int[] xList;
	static int[] yList;
	static double taxRate;
	
	static boolean[] visited;
	static long[] minEdge; 
	static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			sb.append("#").append(testCaseNo).append(" ").append(calcMstCost()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 섬의 개수를 입력받는다.
		islandCount = Integer.parseInt(br.readLine().trim());
		// 2-2. 섬들의 x좌표와 y좌표를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
		xList = new int[islandCount];
		yList = new int[islandCount];
		
		st = new StringTokenizer(br.readLine().trim());
		for(int islandIdx = 0; islandIdx < islandCount; islandIdx++) {
			xList[islandIdx] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine().trim());
		for(int islandIdx = 0; islandIdx < islandCount; islandIdx++) {
			yList[islandIdx] = Integer.parseInt(st.nextToken());
		}
		// 2-3. 환경 부담 세율 실수를 입력받는다.
		taxRate = Double.parseDouble(br.readLine().trim());
		// 2-4. MST 포함 여부를 저장할 배열을 생성한다.
		visited = new boolean[islandCount];
		// 2-5. MST에서 정점으로 가는 최소 가중치를 저장할 배열을 생성하고 INF로 초기화한다.
		minEdge = new long[islandCount];
		Arrays.fill(minEdge, INF);
	}
	
	static long prim() {
		// 2-6. 0번 섬을 시작점으로 선택하고, minEdge를 0으로 설정한다.
		minEdge[0] = 0;
		long totalDistanceSqured = 0;
		
		// 2-7. 모든 정점이 MST에 포함될 때까지,
		for(int count = 0; count < islandCount; count++) {
			// 2-7-1. 아직 MST에 포함되지 않은 섬 중 MST에 들어오는 비용이 가장 작은 섬을 선택한다.
			long min = Long.MAX_VALUE;
			int minIslandIdx = -1;
			
			for(int islandIdx = 0; islandIdx < islandCount; islandIdx++) {
				if(!visited[islandIdx] && min > minEdge[islandIdx]) {
					min = minEdge[islandIdx];
					minIslandIdx = islandIdx;
				}
			}
			
			// 2-7-2. 선택한 섬을 MST에 포함한다.
			visited[minIslandIdx] = true;
			totalDistanceSqured += min;
			
			// 2-7-3. 새로 포함한 섬을 기준으로 아직 방문하지 않은 다른 섬들의 최소 연결 거리를 갱신한다.
			long distanceSquared;
			for(int nextIslandIdx = 0; nextIslandIdx < islandCount; nextIslandIdx++) {
				if(visited[nextIslandIdx]) continue;
				distanceSquared = calcDistanceSqaured(minIslandIdx, nextIslandIdx);
				if(distanceSquared < minEdge[nextIslandIdx]) {
					minEdge[nextIslandIdx] = distanceSquared;
				}
			}
		}
		// 2-8. MST의 전체 거리의 제곱의 합을 반환한다.
		return totalDistanceSqured;
	}
	
	static long calcDistanceSqaured(int islandIdx1, int islandIdx2) {
		long dx = xList[islandIdx1] - xList[islandIdx2];
		long dy = yList[islandIdx1] - yList[islandIdx2];
		
		return dx*dx + dy*dy;
	}
	
	static long calcMstCost() {
		return Math.round(prim() * taxRate);
	}
}
