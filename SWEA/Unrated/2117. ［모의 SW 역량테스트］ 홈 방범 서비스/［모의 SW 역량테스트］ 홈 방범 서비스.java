import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 2117. 홈 방범 서비스
 * @author chahuigwang
 * 
 * 	@see #main(String[])
 * 	1. 테스트 케이스 개수를 입력받는다.
 * 	2. 각 테스트 케이스마다,
 * 
 * 		@see #initTestCase()
 * 		2-1. 도시의 한 변의 길이, 하나의 집이 지불할 수 있는 비용을 입력받는다.
 * 		2-2. 집의 위치를 저장할 리스트를 초기화하고, 정보를 입력 받아 리스트에 집의 위치 정보를 저장한다.
 * 		2-3. 서비스 가능한 최대 집의 수를 0으로 초기화한다.
 * 
 * 		@see #calcMaxServiceHouseCount()
 * 		2-4. 서비스 영역의 크기를 2부터 도시의 한 변의 길이 +1 만큼 늘려보며
 *		2-5. 도시의 모든 위치에서
 *		2-6. 서비스 영역 안에 있는 집의 개수를 센다.
 *		2-7. 수익을 계산한다.
 *		2-8. 수익이 0 이상이고, 영역 안에 있는 집의 개수가 서비스 가능한 최대 집의 수보다 크다면 서비스 가능한 최대 집의 수를 갱신한다.
 *
 *		2-9. 테스트 케이스 번호와 함께 정답을 출력한다.
 *
 */

class Solution {
	
	static class HousePosition {
		int row;
		int col;
		
		HousePosition(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int citySize;
	static int serviceFee;
	
	static List<HousePosition> housePositions = new ArrayList<>();
	
	static int maxServiceHouseCount;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			calcMaxServiceHouseCount();
			// 2-9. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(maxServiceHouseCount).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 도시의 한 변의 길이, 하나의 집이 지불할 수 있는 비용을 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		citySize = Integer.parseInt(st.nextToken());
		serviceFee = Integer.parseInt(st.nextToken());
		
		// 2-2. 집의 위치를 저장할 리스트를 초기화하고, 정보를 입력 받아 리스트에 집의 위치 정보를 저장한다.
		housePositions.clear();
		int input;
		for(int row = 0; row < citySize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < citySize; col++) {
				input = Integer.parseInt(st.nextToken());
				if(input == 1) housePositions.add(new HousePosition(row, col));
			}
		}
		
		// 2-3. 서비스 가능한 최대 집의 수를 0으로 초기화한다.
		maxServiceHouseCount = 0;
	}
	
	static void calcMaxServiceHouseCount() {
		int serviceHouseCount;
		int cost;
		int profit;
		int distance;
		boolean updated;
		// 2-4. 서비스 영역의 크기를 2부터 도시의 한 변의 길이 +1 만큼 늘려보며
		for(int serviceArea = 1; serviceArea <= citySize + 1; serviceArea++) {
			cost = serviceArea * serviceArea + (serviceArea - 1) * (serviceArea - 1);
			updated = false;
			// 2-5. 도시의 모든 위치에서
			for(int row = 0; row < citySize; row++) {
				for(int col = 0; col < citySize; col++) {
					serviceHouseCount = 0;
					// 2-6. 서비스 영역 안에 있는 집의 개수를 센다.
					for(HousePosition housePosition : housePositions) {
						distance = Math.abs(row - housePosition.row) + Math.abs(col - housePosition.col);
						if(distance < serviceArea) serviceHouseCount++;
					}
					// 2-7. 수익을 계산한다.
					profit = serviceFee * serviceHouseCount - cost;
					// 2-8. 수익이 0 이상이고, 영역 안에 있는 집의 개수가 서비스 가능한 최대 집의 수보다 크다면 서비스 가능한 최대 집의 수를 갱신한다.
					if(profit >= 0) maxServiceHouseCount = Math.max(maxServiceHouseCount, serviceHouseCount);
				}
			}
		}
	}
}