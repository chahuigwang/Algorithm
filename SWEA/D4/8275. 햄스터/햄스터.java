import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 8275. 햄스터
 * [문제]
 * - N개의 햄스터 우리(1 ~ N번)
 * - 각 우리에 0 ~ X 마리의 햄스터
 * - M개의 기록
 * 	- 각 기록 : l번 우리에서 r번 우리까지의 햄스터 수를 세었더니 s마리
 * 
 * @author chahuigwang
 * 
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 * 
 * 	@see #initTestCase()
 * 	2-1. 우리의 개수, 우리 당 최대 햄스터 수, 기록의 개수를 입력받는다.
 * 	2-2. 기록들을 저장할 리스트를 초기화한다.
 * 	2-3. 기록들의 정보를 입력받아 리스트에 저장한다.
 * 	2-4. 각 우리의 햄스터 개수를 저장할 배열을 생성한다.
 *  2-5. 최대 전체 햄스터 수를 -1로 초기화한다.
 *  2-6. 가능한 방법이 있는지 여부를 초기화한다.
 *  2-7. 정답 배열을 초기화한다.
 *  
 *  @see #findPossibleArrangement(int)
 *  2-8. 각 케이지에 0마리부터 X마리까지 넣는 경우를 고려하여 모든 배치를 만든다.
 *  2-9. 하나의 배치가 완성되면 다음을 수행한다.
 *  	2-9-1. 현재 배치가 기록을 만족한다면 다음을 수행한다.
 *  		2-9-1-1. 가능한 가능한 방법이 있는지 여부를 true로 저장한다.
 *  		2-9-1-2. 총 햄스터 수를 계산한다.
 *  		2-9-1-3. 총 햄스터 수가 최대 햄스터 수보다 크다면
 *  			2-9-1-3-1. 최대 햄스터 수를 갱신한다.
 *  			2-9-1-3-2. 정답 배열에 현재 배치를 저장한다.
 *
 *	2-10. 테스트 케이스 번호와 함께 정답을 출력한다.
 */

class Solution {

	static class Record {
		int startCageNo;
		int endCageNo;
		int hamsterCount;
		
		Record(int startCageNo, int endCageNo, int hamsterCount) {
			this.startCageNo = startCageNo;
			this.endCageNo = endCageNo;
			this.hamsterCount = hamsterCount;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int cageCount; // 우리의 개수
	static int maxHamsterCountPerCage; // 우리 당 최대 햄스터 수
	static int recordCount; // 기록의 개수
	
	static List<Record> records = new ArrayList<>();
	
	static int[] cageHamsterCountList;
	
	static int[] answerArr;
	
	static int maxTotalHamsterCount;
	
	static boolean found;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			findPossibleArrangement(1);
			sb.append("#").append(testCaseNo).append(" ").append(found ? arrayToString(answerArr) : -1).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 우리의 개수, 우리 당 최대 햄스터 수, 기록의 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		cageCount = Integer.parseInt(st.nextToken());
		maxHamsterCountPerCage = Integer.parseInt(st.nextToken());
		recordCount = Integer.parseInt(st.nextToken());
		
		// 2-2. 기록들을 저장할 리스트를 초기화한다.
		records.clear();
		
		// 2-3. 기록들의 정보를 입력받아 리스트에 저장한다.
		int startCageNo, endCageNo, hamsterCount;
		for(int recordIdx = 0; recordIdx < recordCount; recordIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			startCageNo = Integer.parseInt(st.nextToken());
			endCageNo = Integer.parseInt(st.nextToken());
			hamsterCount = Integer.parseInt(st.nextToken());
			records.add(new Record(startCageNo, endCageNo, hamsterCount));
		}
		
		// 2-4. 각 우리의 햄스터 개수를 저장할 배열을 생성한다.
		cageHamsterCountList = new int[cageCount + 1];
		
		// 2-5. 최대 전체 햄스터 수를 -1로 초기화한다.
		maxTotalHamsterCount = -1;
		
		// 2-6. 가능한 방법이 있는지 여부를 초기화한다.
		found = false;
		
		// 2-7. 정답 배열을 초기화한다.
		answerArr = new int[cageCount + 1];
	}
	
	static void findPossibleArrangement(int cageIdx) {
		// 2-9. 하나의 배치가 완성되면 다음을 수행한다.
		if(cageIdx == cageCount + 1) {
			// 2-9-1. 현재 배치가 기록을 만족한다면 다음을 수행한다.
			if(safisfiesRecords()) {
				// 2-9-1-1. 가능한 가능한 방법이 있는지 여부를 true로 저장한다.
				found = true;
				// 2-9-1-2. 총 햄스터 수를 계산한다.
				int total = totalArr(cageHamsterCountList);
				// 2-9-1-3. 총 햄스터 수가 최대 햄스터 수보다 크다면
				if(total > maxTotalHamsterCount) {
					// 2-9-1-3-1. 최대 햄스터 수를 갱신한다.
					maxTotalHamsterCount = total;
					// 2-9-1-3-2. 정답 배열에 현재 배치를 저장한다.
					answerArr = Arrays.copyOf(cageHamsterCountList, cageCount + 1);
				}
			}
			return;
		}
		
		// 2-8. 각 케이지에 0마리부터 X마리까지 넣는 경우를 고려하여 모든 배치를 만든다.
		for(int hamsterCountPerCage = 0; hamsterCountPerCage <= maxHamsterCountPerCage; hamsterCountPerCage++) {
			cageHamsterCountList[cageIdx] = hamsterCountPerCage;
			findPossibleArrangement(cageIdx + 1);
		}
	}
	
	static boolean safisfiesRecords() {
		int start, end, recordCount;
		int count;
		for(Record record : records) {
			start = record.startCageNo;
			end = record.endCageNo;
			recordCount = record.hamsterCount;
			count = 0;
			for(int cageIdx = start; cageIdx <= end; cageIdx++) {
				count += cageHamsterCountList[cageIdx];
			}
			if(count != recordCount) return false;
		}
		return true;
	}
	
	static int totalArr(int[] arr) {
		int total = 0;
		for(int num : arr) {
			total += num;
		}
		return total;
	}
	
	static String arrayToString(int[] arr) {
		String result = "";
		for(int cageIdx = 1; cageIdx <= cageCount; cageIdx++) {
			result += answerArr[cageIdx] + " ";
		}
		return result.trim();
	}
}