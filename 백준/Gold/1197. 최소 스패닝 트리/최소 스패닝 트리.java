import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 1197. 최소 스패닝 트리
 * @author chahuigwang
 * 
 * @see #main(String[])
 * 
 * @see #init()
 * 1. 정점의 개수와 간선의 개수를 입력받는다.
 * 2. 간선 정보를 저장할 배열을 생성하고, 간선의 정보를 입력받아 배열에 저장한다.
 * 3. 간선 배열을 가중치 기준 오름차순으로 정렬한다.
 * 4. 정점의 부모를 저장할 배열을 생성한다.
 * 
 * @see #make()
 * 5. 모든 정점을 자기 자신이 부모인 집합으로 초기화한다.
 * 
 * @see #kruskal()
 * 6. 크루스칼 알고리즘을 통해 MST의 가중치를 구한다.
 * 
 * 7. 정답을 출력한다.
 *
 */

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int weight;
		
		Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int vertexCount;
	static int edgeCount;
	
	static Edge[] edges;
	
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(kruskal());
	}
	
	static void init() throws IOException {
		// 1. 정점의 개수와 간선의 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		
		// 2. 간선 정보를 저장할 배열을 생성하고, 간선의 정보를 입력받아 배열에 저장한다.
		edges = new Edge[edgeCount];
		int start, end, weight;
		for(int edgeIdx = 0; edgeIdx < edgeCount; edgeIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			edges[edgeIdx] = new Edge(start, end, weight);
		}
		
		// 3. 간선 배열을 가중치 기준 오름차순으로 정렬한다.
		Arrays.sort(edges);
		
		// 4. 정점의 부모를 저장할 배열을 생성한다.
		parents = new int[vertexCount + 1];
		
		// 5. 모든 정점을 자기 자신이 부모인 집합으로 초기화한다.
		make();
	}
	
	static void make() {
		for(int vertexIdx = 1; vertexIdx <= vertexCount; vertexIdx++) {
			parents[vertexIdx] = vertexIdx;
		}
	}
	
	static int find(int v) {
		if(parents[v] == v) return v;
		return parents[v] = find(parents[v]);
	}
	
	static boolean union(int v1, int v2) {
		int rootV1 = find(v1);
		int rootV2 = find(v2);
		
		if(rootV1 == rootV2) return false;
		
		parents[rootV2] = rootV1;
		return true;
	}
	
	static int kruskal() {
		int totalWeight = 0;
		int mstEdgeCount = 0;
		for(Edge edge : edges) {
			if(union(edge.start, edge.end)) {
				totalWeight += edge.weight;
				if(++mstEdgeCount == vertexCount - 1) break;
			}
		}
		return totalWeight;
	}
}
