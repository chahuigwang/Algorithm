import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 7465. 창용 마을 무리의 개수
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 사람의 수, 서로를 알고 있는 사람의 관계 수를 입력받는다.
 *		2-2. 사람들의 관계를 저장할 리스트 배열을 생성하고, 정보를 입력받아 저장한다.
 *		2-3. 방문 여부를 저장할 배열을 생성한다.
 *	
 *		@see #countGroup()
 *		2-4. 모든 사람들을 순서대로 순회하며,
 *			2-4-1. 방문하지 않은 사람들을 무리로 만든다.
 *
 *				@see #makeGroup(int)
 *				2-4-1-1. 방문 처리한다.
 *				2-4-1-2. 방문하지 않은 이웃에서 재귀 호출한다.
 *			
 *			2-4-2. 무리의 수를 1 증가시킨다.
 *		
 *		2-5. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int peopleCount;
	static int relationCount;
	
	static List<Integer>[] relation;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			// 2-5. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(countGroup()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 사람의 수, 서로를 알고 있는 사람의 관계 수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		peopleCount = Integer.parseInt(st.nextToken());
		relationCount = Integer.parseInt(st.nextToken());
		// 2-2. 사람들의 관계를 저장할 리스트 배열을 생성하고, 정보를 입력받아 저장한다.
		relation = new ArrayList[peopleCount + 1];
		for(int peopleIdx = 1; peopleIdx <= peopleCount; peopleIdx++) {
			relation[peopleIdx] = new ArrayList<>();
		}
		
		int from, to;
		for(int relationIdx = 0; relationIdx < relationCount; relationIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			relation[from].add(to);
			relation[to].add(from);
		}
		
		// 2-3. 방문 여부를 저장할 배열을 생성한다.
		visited = new boolean[peopleCount + 1];
	}
	
	static int countGroup() {
		int groupCount = 0;
		
		// 2-4. 모든 사람들을 순서대로 순회하며,
		for(int peopleIdx = 1; peopleIdx <= peopleCount; peopleIdx++) {
			if(!visited[peopleIdx]) {
				// 2-4-1. 방문하지 않은 사람들을 무리로 만든다.
				makeGroup(peopleIdx);
				// 2-4-2. 무리의 수를 1 증가시킨다.
				groupCount++;
			}
		}
		return groupCount;
	}
	
	static void makeGroup(int peopleIdx) {
		// 2-4-1-1. 방문 처리한다.
		visited[peopleIdx] = true;
		
		for(int neighbor : relation[peopleIdx]) {
			// 2-4-1-2. 방문하지 않은 이웃에서 재귀 호출한다.
			if(!visited[neighbor]) makeGroup(neighbor);
		}
	}
}
