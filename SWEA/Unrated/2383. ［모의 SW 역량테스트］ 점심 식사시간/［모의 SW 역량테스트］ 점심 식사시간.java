import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 2383. 점심 식사시간
 * @author chahuigwang
 * 
 * [전략]
 * - 각 사람마다 1번 또는 2번 계단 선택
 * - 시뮬레이션
 * 
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *	
 *		@see #initTestCase()
 *		2-1. 방의 한 변의 길이를 입력받는다.
 *		2-2. 사람의 정보와 계단의 정보를 저장할 리스트를 초기화한다.
 *		2-3. 방의 정보를 입력받아 리스트에 저장한다.
 *		2-4. 사람의 수를 저장한다.
 *		2-5. 선택한 계단 번호를 저장할 배열을 생성한다.
 *		2-6. 최소 이동 완료 시간을 Integer의 최댓값으로 초기화한다.
 *
 *		@see #selectStair(int)
 *		2-7. 각 사람이 1번 계단 또는 2번 계단을 선택하는 경우를 고려해 가능한 모든 선택을 만든다.
 *		2-8. 하나의 선택이 완성되면
 *
 *			@see #calcMinTotalMoveTime()
 *			2-8-1. 1번과 2번 계단에 도착하는 시간을 저장할 리스트를 생성한다.
 *			2-8-2. 각 사람이 선택한 계단까지의 거리 + 대기 시간 1분을 리스트에 추가한다.
 *			2-8-3. 1번과 2번 계단에 도착하는 시간의 리스트를 오름차순 정렬한다.
 *			2-8-4. 1번과 2번 계단에 있는 사람들이 모두 내려가는 데 걸리는 시간을 계산한다.
 *			2-8-5. 1번과 2번 계단에 있는 사람들이 모두 내려가는 데 걸리는 시간의 최댓값이 이동 완료 시간이 된다.
 *			2-8-6. 최소 이동 완료 시간을 갱신한다.
 *
 *		2-9. 테스트 케이스 번호와 함께 정답을 출력한다.
 *			
 */

class Solution {

	static class Person {
		int row;
		int col;
		
		Person(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static class Stair {
		int row;
		int col;
		int length;
		
		Stair(int row, int col, int length) {
			this.row = row;
			this.col = col;
			this.length = length;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int roomSize;
	
	static List<Person> persons = new ArrayList<>();
	static List<Stair> stairs = new ArrayList<>();
	
	static int personCount;
	static int[] selectedStairs;
	
	static int minTotalMoveTime;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			selectStair(0);
			// 2-9. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(minTotalMoveTime).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 방의 한 변의 길이를 입력받는다.
		roomSize = Integer.parseInt(br.readLine().trim());
		
		// 2-2. 사람의 정보와 계단의 정보를 저장할 리스트를 초기화한다.
		persons.clear();
		stairs.clear();
		
		// 2-3. 방의 정보를 입력받아 리스트에 저장한다.
		int input;
		for(int row = 0; row < roomSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < roomSize; col++) {
				input = Integer.parseInt(st.nextToken());
				if(input == 1) persons.add(new Person(row, col));
				else if(input >= 2) stairs.add(new Stair(row, col, input));
			}
		}
		
		// 2-4. 사람의 수를 저장한다.
		personCount = persons.size();
		
		// 2-5. 선택한 계단 번호를 저장할 배열을 생성한다.
		selectedStairs = new int[personCount];
		
		// 2-6. 최소 이동 완료 시간을 Integer의 최댓값으로 초기화한다.
		minTotalMoveTime = Integer.MAX_VALUE;
	}
	
	static void selectStair(int personIdx) {
		// 2-8. 하나의 선택이 완성되면
		if(personIdx == personCount) {
			calcMinTotalMoveTime();
			return;
		}
		
		// 2-7. 각 사람이 1번 계단 또는 2번 계단을 선택하는 경우를 고려해 가능한 모든 선택을 만든다.
		
		// 1번 계단 선택
		selectedStairs[personIdx] = 0;
		selectStair(personIdx + 1);
		
		// 2번 계단 선택
		selectedStairs[personIdx] = 1;
		selectStair(personIdx + 1);
	}
	
	static void calcMinTotalMoveTime() {
		Person person;
		int selectedStairNo;
		Stair stair;
		int distance;
		
		// 2-8-1. 1번과 2번 계단에 도착하는 시간을 저장할 리스트를 생성한다.
		List<Integer> stair1ArrivalTimes = new ArrayList<>();
		List<Integer> stair2ArrivalTimes = new ArrayList<>();
		
		for(int personIdx = 0; personIdx < personCount; personIdx++) {
			person = persons.get(personIdx);
			selectedStairNo = selectedStairs[personIdx];
			stair = stairs.get(selectedStairNo);
			distance = Math.abs(person.row - stair.row) + Math.abs(person.col - stair.col);
			
			// 2-8-2. 각 사람이 선택한 계단까지의 거리 + 대기 시간 1분을 리스트에 추가한다.
			if(selectedStairNo == 0) stair1ArrivalTimes.add(distance + 1);
			if(selectedStairNo == 1) stair2ArrivalTimes.add(distance + 1);
		}
		
		// 2-8-3. 1번과 2번 계단에 도착하는 시간의 리스트를 오름차순 정렬한다.
		Collections.sort(stair1ArrivalTimes);
		Collections.sort(stair2ArrivalTimes);
		
		// 2-8-4. 1번과 2번 계단에 있는 사람들이 모두 내려가는 데 걸리는 시간을 계산한다.
		int stair1Time = calcStairTime(stair1ArrivalTimes, stairs.get(0).length);
		int stair2Time = calcStairTime(stair2ArrivalTimes, stairs.get(1).length);
		
		// 2-8-5. 1번과 2번 계단에 있는 사람들이 모두 내려가는 데 걸리는 시간의 최댓값이 이동 완료 시간이 된다.
		int totalMoveTime = Math.max(stair1Time, stair2Time);
		
		// 2-8-6. 최소 이동 완료 시간을 갱신한다.
		minTotalMoveTime = Math.min(minTotalMoveTime, totalMoveTime);
	}
	
	static int calcStairTime(List<Integer> arrivalTimes, int stairLength) {
		int arrivalTimeSize = arrivalTimes.size();
		if(arrivalTimeSize == 0) return 0;
		
		for(int arrivalTimeIdx = 0; arrivalTimeIdx < arrivalTimeSize; arrivalTimeIdx++) {
			if(arrivalTimeIdx >= 3) {
				if(arrivalTimes.get(arrivalTimeIdx) < arrivalTimes.get(arrivalTimeIdx - 3) + stairLength)
					arrivalTimes.set(arrivalTimeIdx, arrivalTimes.get(arrivalTimeIdx - 3) + stairLength);
			}
		}
		
		return arrivalTimes.get(arrivalTimeSize - 1) + stairLength;
	}
}
