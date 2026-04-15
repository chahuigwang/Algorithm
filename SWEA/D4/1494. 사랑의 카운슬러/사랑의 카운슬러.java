import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1494. 사랑의 카운슬러
 * @author chahuigwang
 * 	
 * 	@see #main(String[])
 * 	1. 테스트 케이스 개수를 입력받는다.
 * 	2. 각 테스트 케이스마다,
 * 
 * 		@see #initTestCase()
 * 		2-1. 지렁이의 수를 입력받는다.
 * 		2-2. 지렁이들의 위치를 저장할 배열을 생성한다.
 * 		2-3. 지렁이들의 위치를 입력받아 배열에 저장한다.
 * 		2-4. 선택 여부를 저장할 배열을 생성한다.
 * 		2-5. 지렁이의 움직인 벡터의 합의 크기의 최솟값을 초기화한다.
 * 
 * 		@see #findMinVetorSumSize(int, int)
 * 		2-6. 각 지렁이를 선택하는 경우와 선택하지 않는 경우를 고려하여 가능한 모든 경우의 수를 만든다.
 * 		2-7. 전체 지렁이 중 절반이 선택되었다면,
 * 			
 * 			@see #updateMinVetorSumSize()
 * 			2-7-1. 벡터의 합의 크기의 최솟값을 갱신한다.
 * 		
 * 		2-8. 테스트 케이스 번호와 함께 정답을 출력한다.
 *
 */

class Solution {
	
	static class WormPosition {
		int x;
		int y;
		
		WormPosition(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int wormCount;
	static WormPosition[] wormPositions;
	
	static boolean[] selected;
	static long minVectorSumSize;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			findMinVetorSumSize(0, 0);
			// 2-8. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(minVectorSumSize).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 지렁이의 수를 입력받는다.
		wormCount = Integer.parseInt(br.readLine().trim());
		
		// 2-2. 지렁이들의 위치를 저장할 배열을 생성한다.
		wormPositions = new WormPosition[wormCount];
		
		// 2-3. 지렁이들의 위치를 입력받아 배열에 저장한다.
		int x, y;
		for(int wormIdx = 0; wormIdx < wormCount; wormIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			wormPositions[wormIdx] = new WormPosition(x, y);
		}
		
		// 2-4. 선택 여부를 저장할 배열을 생성한다.
		selected = new boolean[wormCount];
		
		// 2-5. 지렁이의 움직인 벡터의 합의 크기의 최솟값을 초기화한다.
		minVectorSumSize = Long.MAX_VALUE;
	}
	
	static void findMinVetorSumSize(int selectCount, int startIdx) {
		// 2-7. 전체 지렁이 중 절반이 선택되었다면,
		if(selectCount == wormCount / 2) {
			// 2-7-1. 벡터의 합의 크기의 최솟값을 갱신한다.
			updateMinVetorSumSize();
			return;
		}
		
		// 2-6. 각 지렁이를 선택하는 경우와 선택하지 않는 경우를 고려하여 가능한 모든 경우의 수를 만든다.
		for(int wormIdx = startIdx; wormIdx < wormCount; wormIdx++) {
			selected[wormIdx] = true;
			findMinVetorSumSize(selectCount + 1, wormIdx + 1);
			selected[wormIdx] = false;
		}
	}
	
	static void updateMinVetorSumSize() {
		int totalX = 0;
		int totalY = 0;
		for(int wormIdx = 0; wormIdx < wormCount; wormIdx++) {
			if(selected[wormIdx]) {
				totalX += wormPositions[wormIdx].x;
				totalY += wormPositions[wormIdx].y;
			}
			else {
				totalX -= wormPositions[wormIdx].x;
				totalY -= wormPositions[wormIdx].y;
			}
		}
		long vetorSumSize = (long)totalX * totalX + (long)totalY * totalY;
		minVectorSumSize = Math.min(minVectorSumSize, vetorSumSize);
	}
}
