import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * SWEA 3124. 최소 스패닝 트리
 * @author SSAFY
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 정점의 개수를 입력받는다.
 *		2-2. 간선의 개수를 입력받는다.
 *		2-3. 인접 리스트를 생성하고, 정보를 입력받아 저장한다.
 *		2-4. MST 포함 여부를 저장할 배열을 생성한다.
 *
 *		@see #prim()
 *		2-5. 우선순위 큐를 생성하고 시작(1번) 정점을 삽입한다.
 *		2-6. 우선순위 큐가 빌 때까지,
 *			2-6-1. 우선순위 큐에서 정점을 꺼낸다.(선택한 정점들과 인접하는 정점들 중에서 최소 비용의 간선이 존재하는 정점)
 *			2-6-2. 이미 MST에 포함된 정점이라면 넘어간다.
 *			2-6-3. 현재 정점을  MST에 포함 처리한다.
 *			2-6-4. 전체 가중치에 현재 정점으로 가는 가중치를 더한다.
 *			2-6-5. 모든 정점이 MST에 포함되었다면 종료한다.
 *			2-6-6. MST에 새롭게 추가된 정점의 인접 정점들을 우선순위 큐에 삽입한다.
 *		2-7. 전체 가중치를 반환한다.
 *
 *		2-8. 테스트 케이스 번호와 함께 MST의 가중치를 출력한다.
 *			
 */

class Solution {
	
	static class Edge {
		int to;
		int weight;
		Edge next;
		
		Edge(int to, int weight, Edge next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
	
	static class Vertex implements Comparable<Vertex> {
		int vertexNo;
		int weight;
		
		Vertex(int vertexNo, int weight) {
			this.vertexNo = vertexNo;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int vertextCount;
	static int edgeCount;
	static Edge[] adjList;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			// 2-8. 테스트 케이스 번호와 함께 MST의 가중치를 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(prim()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		// 2-1. 정점의 개수를 입력받는다.
		vertextCount = Integer.parseInt(st.nextToken());
		// 2-2. 간선의 개수를 입력받는다.
		edgeCount = Integer.parseInt(st.nextToken());
		
		// 2-3. 인접 리스트를 생성하고, 정보를 입력받아 저장한다.
		adjList = new Edge[vertextCount + 1];
		int from, to, weight;
		for(int edgeIdx = 0; edgeIdx < edgeCount; edgeIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Edge(to, weight, adjList[from]);
			adjList[to] = new Edge(from, weight, adjList[to]);
		}
		
		// 2-4. MST 포함 여부를 저장할 배열을 생성한다.
		visited = new boolean[vertextCount + 1];
	}
	
	static long prim() {
		// 2-5. 우선순위 큐를 생성하고 시작(1번) 정점을 삽입한다.
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(1, 0));
		
		long totalWeight = 0;
		int selectedVertexCount = 0;
		
		Vertex curVertex;
		// 2-6. 우선순위 큐가 빌 때까지,
		while(!pq.isEmpty()) {
			// 2-6-1. 우선순위 큐에서 정점을 꺼낸다.(선택한 정점들과 인접하는 정점들 중에서 최소 비용의 간선이 존재하는 정점)
			curVertex = pq.poll();
			// 2-6-2. 이미 MST에 포함된 정점이라면 넘어간다.
			if(visited[curVertex.vertexNo]) continue;
			// 2-6-3. 현재 정점을  MST에 포함 처리한다.
			visited[curVertex.vertexNo] = true;
			// 2-6-4. 전체 가중치에 현재 정점으로 가는 가중치를 더한다.
			totalWeight += curVertex.weight;
			// 2-6-5. 모든 정점이 MST에 포함되었다면 종료한다.
			if(++selectedVertexCount == vertextCount) break;
			// 2-6-6. MST에 새롭게 추가된 정점의 인접 정점들을 우선순위 큐에 삽입한다.
			for(Edge next = adjList[curVertex.vertexNo]; next != null; next = next.next) {
				if(!visited[next.to]) {
					pq.offer(new Vertex(next.to, next.weight));
				}
			}
		}
		// 2-7. 전체 가중치를 반환한다.
		return totalWeight;
	}
}
