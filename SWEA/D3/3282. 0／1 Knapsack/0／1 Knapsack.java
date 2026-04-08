import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 3282. 0/1 Knapsack
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 물건의 개수와 가방의 부피를 입력받는다.
 *		2-2. 물건의 부피와 가치를 저장할 배열을 각각 생성하고, 정보를 입력받아 배열에 저장한다.
 *		2-3. 부피마다 담을 수 있는 최대 가치를 저장할 배열(dp 테이블)을 생성한다.
 *
 *		@see #calcMaxValue()
 *		2-4. 모든 물건들에 대해, 해당 물건을 넣을 때와 안 넣을 때의 최대 가치로 최대 가치 배열을 갱신한다.
 *
 *		2-5. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int itemCount;
	static int volumeLimit;
	
	static int[] volumes;
	static int[] values;
	
	static int[] maxValues;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			// 2-5. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(calcMaxValue()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 물건의 개수와 가방의 부피를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		itemCount = Integer.parseInt(st.nextToken());
		volumeLimit = Integer.parseInt(st.nextToken());
		
		// 2-2. 물건의 부피와 가치를 저장할 배열을 각각 생성하고, 정보를 입력받아 배열에 저장한다.
		volumes = new int[itemCount];
		values = new int[itemCount];
		
		for(int itemIdx = 0; itemIdx < itemCount; itemIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			volumes[itemIdx] = Integer.parseInt(st.nextToken());
			values[itemIdx] = Integer.parseInt(st.nextToken());
		}
		
		// 2-3. 부피마다 담을 수 있는 최대 가치를 저장할 배열(dp 테이블)을 생성한다.
		maxValues = new int[volumeLimit + 1];
	}
	
	static int calcMaxValue() {
		// 2-4. 모든 물건들에 대해,
		for(int itemIdx = 0; itemIdx < itemCount; itemIdx++) {
			for(int volume = volumeLimit; volume >= volumes[itemIdx]; volume--) {
				// 해당 물건을 넣을 때와 안 넣을 때의 최대 가치로 최대 가치 배열을 갱신한다.
				maxValues[volume] = Math.max(
						maxValues[volume],
						maxValues[volume - volumes[itemIdx]] + values[itemIdx]);
			}
		}
		return maxValues[volumeLimit];
	}
}
