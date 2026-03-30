import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1267. 작업순서
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 10개의 각 테스트 케이스마다,
 *	
 *		@see #initTestCase()
 *		1-1. 정점의 개수와 간선의 개수를 입력받는다.
 *		1-2. 그래프를 저장할 배열을 생성한다.
 *		1-3. 진입 차수를 저장할 배열을 생성한다.
 *		1-4. 간선 정보를 입력받아 그래프 배열과 진입 차수 배열에 저장한다.
 *
 *		1-5. 테스트 케이스 번호를 출력한다.
 *
 *		1-6. 위상 정렬을 수행항 작업 순서를 출력한다.
 */

class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int TEST_CASE_COUNT = 10;
	
	static int vertextCount;
	static int edgeCount;
	
	static List<Integer>[] graph;
	static int[] indegree;
	
	static Queue<Integer> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		// 1. 10개의 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= TEST_CASE_COUNT; testCaseNo++) {
			initTestCase();
			// 1-5. 테스트 케이스 번호를 출력한다.
			sb.append("#").append(testCaseNo).append(" ");
			// 1-6. 위상 정렬을 수행하여 작업 순서를 출력한다.
			topologicalSort();
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 1-1. 정점의 개수와 간선의 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		vertextCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		
		// 1-2. 그래프를 저장할 배열을 생성한다.
		graph = new ArrayList[vertextCount + 1];
		for(int vertexIdx = 0; vertexIdx <= vertextCount; vertexIdx++) {
			graph[vertexIdx] = new ArrayList<>();
		}
		
		// 1-3. 진입 차수를 저장할 배열을 생성한다.
		indegree = new int[vertextCount + 1];
		
		// 1-4. 간선 정보를 입력받아 그래프 배열과 진입 차수 배열에 저장한다.
		st = new StringTokenizer(br.readLine().trim());
		int in, out;
		for(int edgeIdx = 0; edgeIdx < edgeCount; edgeIdx++) {
			in = Integer.parseInt(st.nextToken());
			out = Integer.parseInt(st.nextToken());
			graph[in].add(out);
			indegree[out]++;
		}
	}
	
	static void topologicalSort() {
		queue.clear();
		
		for(int vertexIdx = 1; vertexIdx <= vertextCount; vertexIdx++) {
			if(indegree[vertexIdx] == 0) queue.offer(vertexIdx);
		}
		
		int curVertex;
		while(!queue.isEmpty()) {
			curVertex = queue.poll();
			sb.append(curVertex).append(" ");
			
			for(int out : graph[curVertex]) {
				if(--indegree[out] == 0) queue.offer(out);
			}
		}
	}
}
