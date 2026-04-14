import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 5643. 키 순서
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 학생들의 수를 입력받는다.
 *		2-2. 비교한 횟수를 입력받는다.
 *		2-3. 비교 결과를 저장할 배열을 생성하고 비교 결과를 입력받아 배열에 저장한다.
 *
 *		@see #compareHeight()
 *		2-4. 모든 학생을 한번씩 중간에 세운다.
 *		2-5. (작음 < 중간 < 큼 )을 만족한다면 비교 결과를 저장한다.
 *
 *		@see #calcKnowsHeightRankCount()
 *		2-6. 한 학생이 모든 학생과 키를 비교할 수 있다면 자신이 키가 몇 번째인지 알 수 있는 학생의 수를 1증가한다.
 *		2-7. 자신이 키가 몇 번째인지 알 수 있는 학생의 수를 반환한다.
 *
 *		2-8. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int studentCount;
	static int compareCount;
	
	static boolean[][] isShorter;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			compareHeight();
			// 2-8. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(calcKnowsHeightRankCount()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 학생들의 수를 입력받는다.
		studentCount = Integer.parseInt(br.readLine().trim());
		// 2-2. 비교한 횟수를 입력받는다.
		compareCount = Integer.parseInt(br.readLine().trim());
		
		// 2-3. 비교 결과를 저장할 배열을 생성하고 비교 결과를 입력받아 배열에 저장한다.
		isShorter = new boolean[studentCount + 1][studentCount + 1];
		int shorter, taller;
		for(int compareIdx = 0; compareIdx < compareCount; compareIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			shorter = Integer.parseInt(st.nextToken());
			taller = Integer.parseInt(st.nextToken());
			isShorter[shorter][taller] = true;
		}
	}
	
	static void compareHeight() {
		// 2-4. 모든 학생을 한번씩 중간에 세운다.
		for(int mid = 1; mid <= studentCount; mid++) {
			for(int shorter = 1; shorter <= studentCount; shorter++) {
				for(int taller = 1; taller <= studentCount; taller++) {
					// 2-5. (작음 < 중간 < 큼 )을 만족한다면 비교 결과를 저장한다.
					if(isShorter[shorter][mid] && isShorter[mid][taller]) isShorter[shorter][taller] = true;
				}
			}
		}
	}
	
	static int calcKnowsHeightRankCount() {
		int knowsHeightRankCount = 0;
		boolean knowsHeightRank;
		for(int student1 = 1; student1 <= studentCount; student1++) {
			knowsHeightRank = true;
			for(int student2 = 1; student2 <= studentCount; student2++) {
				if(student1 == student2) continue;
				if(!isShorter[student1][student2] && !isShorter[student2][student1]) {
					knowsHeightRank = false;
					break;
				}
			}
			// 2-6. 한 학생이 모든 학생과 키를 비교할 수 있다면 자신이 키가 몇 번째인지 알 수 있는 학생의 수를 1증가한다.
			if(knowsHeightRank) knowsHeightRankCount++;
		}
		// 2-7. 자신이 키가 몇 번째인지 알 수 있는 학생의 수를 반환한다.
		return knowsHeightRankCount;
	}
}
