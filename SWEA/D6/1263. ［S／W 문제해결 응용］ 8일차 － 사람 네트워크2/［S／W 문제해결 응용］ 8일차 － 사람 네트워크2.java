import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1263. 사람 네트워크2
 * @author chahuigwang
 *	
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 사람(노드)의 수를 입력받는다.
 *		2-2. 각 노드 간의 거리를 저장할 배열을 생성한다.
 *		2-3. 네트워크 정보를 입력받아 배열에 저장한다.
 *
 *		@see #floydWarshall()
 *		2-4. 플로이드-워셜 알고리즘을 통해 각 노드간의 거리를 계산한다.
 *
 *		@see #findMinCC()
 *		2-5. 사람들의 CC 값들 중에서 최솟값을 찾아 반환한다.
 *
 *		2-6. 테스트 케이스 번호와 함께 정답을 출력한다.
 *		
 */

class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int nodeCount;
	static int[][] dist;
	static final int INF = (int) 1e9;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			// 2-4. 플로이드-워셜 알고리즘을 통해 각 노드간의 거리를 계산한다.
			floydWarshall();
			// 2-5. 사람들의 CC 값들 중에서 최솟값을 찾아 반환한다.
			// 2-6. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(findMinCC()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		// 2-1. 사람(노드)의 수를 입력받는다.
		nodeCount = Integer.parseInt(st.nextToken());
		
		// 2-2. 각 노드 간의 거리를 저장할 배열을 생성한다.
		dist = new int[nodeCount][nodeCount];
		
		// 2-3. 네트워크 정보를 입력받아 인접 행렬에 저장한다.
		int input;
		for(int node1 = 0; node1 < nodeCount; node1++) {
			for(int node2 = 0; node2 < nodeCount; node2++) {
				input = Integer.parseInt(st.nextToken());
				if(node1 == node2) dist[node1][node2] = 0;
				else dist[node1][node2] = (input == 0) ? INF : 1;
			}
		}
	}
	
	static void floydWarshall() {
		for(int via = 0; via < nodeCount; via++) {
			for(int start = 0; start < nodeCount; start++) {
				for(int end = 0; end < nodeCount; end++) {
					dist[start][end] = Math.min(dist[start][end], dist[start][via] + dist[via][end]);
				}
			}
		}
	}
	
	static int findMinCC() {
		int minCC = Integer.MAX_VALUE;
		int cc;
		for(int start = 0; start < nodeCount; start++) {
			cc = 0;
			for(int end = 0; end < nodeCount; end++) {
				cc += dist[start][end];
			}
			minCC = Math.min(minCC, cc);
		}
		return minCC;
	}
}
