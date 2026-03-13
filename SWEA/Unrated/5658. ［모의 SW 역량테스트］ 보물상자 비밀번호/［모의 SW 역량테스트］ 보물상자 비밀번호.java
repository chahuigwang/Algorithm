import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * SWEA 5658. 보물상자 비밀번호
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *	
 *		@see #initTestCase()
 *		2-1. 숫자의 개수, 비밀번호가 몇 번째로 큰 수인지를 입력받는다.
 *		2-2. 숫자 문자열을 입력받는다.
 *		2-3. 한 변에 있는 숫자의 개수를 계산해 저장한다.
 *		2-4. 회전 횟수를 계산해 저장한다.(회전 횟수 = 한 변에 있는 숫자의 개수 - 1)
 *
 *		@see #findPassword()
 *		2-5. 초기 상태에 한 변에 있는 16진수들을 10진수로 변환해서 Set에 넣는다.
 *		2-6. 회전 횟수 만큼,
 *			2-6-1. 회전한다.
 *			2-6-2. 한 변에 있는 16진수들을 10진수로 변환해서 Set에 넣는다.
 *		2-7. Set의 K번째 원소가 비밀번호가 된다.
 *
 *		2-8. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int numCount;
	static int nthLargest;
	static String numStr;
	static int oneSideNumCount; // 한 변에 있는 숫자의 개수
	static int rotateCount;
	
	static Set<Integer> possibleNums = new TreeSet<>((num1, num2) -> Integer.compare(num2, num1));
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			// 2-8. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(findPassword()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		// 2-1. 숫자의 개수, 비밀번호가 몇 번째로 큰 수인지를 입력받는다.
		numCount = Integer.parseInt(st.nextToken());
		nthLargest = Integer.parseInt(st.nextToken());
		// 2-2. 숫자 문자열을 입력받는다.
		numStr = br.readLine().trim();
		// 2-3. 한 변에 있는 숫자의 개수를 계산해 저장한다.
		oneSideNumCount = numCount / 4;
		// 2-4. 회전 횟수를 계산해 저장한다.(회전 횟수 = 한 변에 있는 숫자의 개수 - 1)
		rotateCount = oneSideNumCount - 1;
		possibleNums.clear();
	}
	
	static void rotate() {
		numStr = numStr.substring(numCount - 1) + numStr.substring(0, numCount - 1);
	}
	
	static void addPossibleNumbers() {
		for(int numIdx = 0; numIdx <= oneSideNumCount * 3; numIdx += oneSideNumCount) {
			possibleNums.add(Integer.parseInt(numStr.substring(numIdx, numIdx + oneSideNumCount), 16));
		}
	}
	
	static int findPassword() {
		// 2-5. 초기 상태에 한 변에 있는 16진수들을 10진수로 변환해서 Set에 넣는다.
		addPossibleNumbers();
		// 2-6. 회전 횟수 만큼,
		for(int rotateCnt = 0; rotateCnt < rotateCount; rotateCnt++) {
			// 2-6-1. 회전한다.
			rotate();
			// 2-6-2. 한 변에 있는 16진수들을 10진수로 변환해서 Set에 넣는다.
			addPossibleNumbers();
		}
		// 2-7. Set의 K번째 원소가 비밀번호가 된다.
		return possibleNums.stream().skip(nthLargest - 1).findFirst().get();
	}
}
