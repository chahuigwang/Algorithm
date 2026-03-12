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
 *		2-2. x좌표들을 저장할 배열을 생성하고, x좌표들을 입력받아 배열에 저장한다.
 *		2-3. y좌표들을 저장할 배열을 생성하고, y좌표들을 입력받아 배열에 저장한다.
 *		2-4. 환경 부담 세율을 입력받는다.
 *
 *		@see #makeEdges()
 *		2-5. 두 섬 사이의 모든 간선을 만들고 배열에 저장한다.
 *		2-6. 간선들을 가중치 기준 오름차순으로 정렬한다.
 *		
 *		@see #makeSets()
 *		2-7. 모든 섬들을 단위 서로소 집합으로 만든다.
 *
 *		@see #kruskal()
 *		2-8. 크루스칼 알고리즘을 통해 최소 환경 부담금을 구한다.
 *
 *		2-9. 테스트 케이스 번호와 함께 최소 환경 부담금을 소수 첫째 자리에서 반올림한 정수를 출력한다.
 */

class Solution {

	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		double weight;
		
		Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int islandCount;
	static int[] xList;
	static int[] yList;
	static double taxRate;
	
	static Edge[] edges;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			// 2-9. 테스트 케이스 번호와 함께 최소 환경 부담금을 소수 첫째 자리에서 반올림한 정수를 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(Math.round(kruskal())).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 섬의 개수를 입력받는다.
		islandCount = Integer.parseInt(br.readLine().trim());
		
		// 2-2. x좌표들을 저장할 배열을 생성하고, x좌표들을 입력받아 배열에 저장한다.
		xList = new int[islandCount];
		st = new StringTokenizer(br.readLine().trim());
		for(int islandIdx = 0; islandIdx < islandCount; islandIdx++) {
			xList[islandIdx] = Integer.parseInt(st.nextToken());
		}
		
		// 2-3. y좌표들을 저장할 배열을 생성하고, y좌표들을 입력받아 배열에 저장한다.
		yList = new int[islandCount];
		st = new StringTokenizer(br.readLine().trim());
		for(int islandIdx = 0; islandIdx < islandCount; islandIdx++) {
			yList[islandIdx] = Integer.parseInt(st.nextToken());
		}
		
		// 2-4. 환경 부담 세율을 입력받는다.
		taxRate = Double.parseDouble(br.readLine().trim());
		
		makeEdges();
		makeSets();
	}
	
	static void makeEdges() {
		// 2-5. 두 섬 사이의 모든 간선을 만들고 배열에 저장한다.
		edges = new Edge[islandCount * (islandCount - 1) / 2];
		long dx;
		long dy;
		double weight;
		int idx = 0;
		for(int start = 0; start < islandCount; start++) {
		    for(int end = start + 1; end < islandCount; end++) {
		        dx = xList[start] - xList[end];
		        dy = yList[start] - yList[end];
		        weight = taxRate * (dx * dx + dy * dy);
		        edges[idx++] = new Edge(start, end, weight);
		    }
		}
		
		// 2-6. 간선들을 가중치 기준 오름차순으로 정렬한다.
		Arrays.sort(edges);
	}
	
	static void makeSets() {
		// 2-7. 모든 섬들을 단위 서로소 집합으로 만든다.
		parents = new int[islandCount];
		for(int islandIdx = 0; islandIdx < islandCount; islandIdx++) {
			parents[islandIdx] = islandIdx;
		}
	}
	
	static int findSet(int v) {
		if(v == parents[v]) return v;
		return parents[v] = findSet(parents[v]);
	}
	
	static boolean union(int v1, int v2) {
		int root1 = findSet(v1);
		int root2 = findSet(v2);
		
		if(root1 == root2) return false;
		
		parents[root2] = root1;
		return true;
	}
	
	static double kruskal() {
		int count = 0;
		double totalWeight = 0;
		for(Edge edge : edges) {
			if(union(edge.start, edge.end)) {
				totalWeight += edge.weight;
				if(++count == islandCount - 1) break;
			}
		}
		return totalWeight;
	}
}
