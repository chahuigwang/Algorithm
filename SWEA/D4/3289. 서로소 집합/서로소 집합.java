import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 3289. 서로소 집합
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스 마다,
 *		
 *		@see #inputTestCase()
 *		2-1. 초기 집합의 개수를 입력받는다.
 *		2-2. 연산의 개수를 입력받는다.
 *		
 *		@see #makeSets()
 *		2-3. 부모 노드의 번호를 저장할 배열을 생성한다.
 *		2-4. {1}, {2}, ... {n} n개의 집합을 만든다.
 *
 *		2-5. 연산의 개수만큼 연산을 입력받는다.
 *
 *		@see #processOperator(int, int, int)
 *		2-6. 연산자가 0이라면 합집합 연산을 수행한다.
 *		2-7. 연산자가 1이라면 두 원소가 같은 집합에 포함되어 있는지를 확인한다.
 */

class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int setCount;
	static int opCount;
	
	static int[] parents;
	
	static StringBuilder ans;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스 마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			inputTestCase();
			sb.append("#").append(testCaseNo).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void inputTestCase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		// 2-1. 초기 집합의 개수를 입력받는다.
		setCount = Integer.parseInt(st.nextToken());
		// 2-2. 연산의 개수를 입력받는다.
		opCount = Integer.parseInt(st.nextToken());
		makeSets();
		ans = new StringBuilder();
		// 2-5. 연산의 개수만큼 연산을 입력받는다.
		int op, e1, e2;
		for(int opIdx = 0; opIdx < opCount; opIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			op = Integer.parseInt(st.nextToken());
			e1 = Integer.parseInt(st.nextToken());
			e2 = Integer.parseInt(st.nextToken());
			processOperator(op, e1, e2);
		}
	}
	
	static void makeSets() {
		// 2-3. 부모 노드의 번호를 저장할 배열을 생성한다.
		parents = new int[setCount + 1];
		// 2-4. {1}, {2}, ... {n} n개의 집합을 만든다.
		for(int idx = 1; idx <= setCount; idx++) {
			parents[idx] = idx;
		}
	}
	
	static int findSet(int e) {
		if(e == parents[e]) return e;
		return parents[e] = findSet(parents[e]);
	}
	
	static void union(int e1, int e2) {
		int root1 = findSet(e1);
		int root2 = findSet(e2);
		
		parents[root2] = root1; 
	}
	
	static boolean hasSameRoot(int e1, int e2) {
		int root1 = findSet(e1);
		int root2 = findSet(e2);
		
		return root1 == root2;
	}
	
	static void processOperator(int op, int e1, int e2) {
		// 2-6. 연산자가 0이라면 합집합 연산을 수행한다.
		if(op == 0) {
			union(e1, e2);
		}
		// 2-7. 연산자가 1이라면 두 원소가 같은 집합에 포함되어 있는지를 확인한다.
		else {
			boolean hasSameRoot = hasSameRoot(e1, e2);
			ans.append(hasSameRoot ? 1 : 0);
		}
	}
}
