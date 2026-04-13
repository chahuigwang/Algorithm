import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1149. RGB 거리
 * @author chahuigwang
 * 
 * 	@see #main(String[])
 * 	
 * 	@see #init()
 * 	1. 집의 수를 입력받는다.
 * 	2. 각 집의 색칠 비용을 저장할 리스트를 생성한다.
 * 	3. 각 집의 색칠 비용을 입력받아 리스트에 저장한다.
 * 
 * 	@see #calcMinColorCost()
 * 	4. 각 집을 직전 집과 다른 색으로 칠하는 비용을 계산한다.
 * 	5. 마지막 집을 칠하는 비용 중 최소 비용이 정답이 된다.
 * 
 * 	6. 정답을 출력한다.
 *
 */

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int houseCount;
	static int[][] minColorCost;
	static final int COLOR_COUNT = 3;
	
	public static void main(String[] args) throws IOException {
		init();
		// 6. 정답을 출력한다.
		System.out.println(calcMinColorCost());
	}
	
	static void init() throws IOException {
		// 1. 집의 수를 입력받는다.
		houseCount = Integer.parseInt(br.readLine().trim());
		
		// 2. 최소 색칠 비용을 저장할 2차원 배열을 생성한다.
		minColorCost = new int[houseCount][COLOR_COUNT];
		
		// 3. 각 집의 색칠 비용을 입력받아 2차원 배열에 저장한다.
		for(int houseIdx = 0; houseIdx < houseCount; houseIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colorIdx = 0; colorIdx < COLOR_COUNT; colorIdx++) {
				minColorCost[houseIdx][colorIdx] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static int calcMinColorCost() {
		// 4. 각 집을 직전 집과 다른 색으로 칠하는 비용을 계산한다.
		int minPrev;
		for(int houseIdx = 1; houseIdx < houseCount; houseIdx++) {
			for(int colorIdx = 0; colorIdx < COLOR_COUNT; colorIdx++) {
				minPrev = Integer.MAX_VALUE;
				for(int prevColorIdx = 0; prevColorIdx < COLOR_COUNT; prevColorIdx++) {
					if(colorIdx == prevColorIdx) continue;
					minPrev = Math.min(minPrev, minColorCost[houseIdx - 1][prevColorIdx]);
				}
				minColorCost[houseIdx][colorIdx] += minPrev;
			}
		}
		
		// 5. 마지막 집을 칠하는 비용 중 최소 비용이 정답이 된다.
		int minCost = Integer.MAX_VALUE;
		for(int colorCost : minColorCost[houseCount - 1]) {
			minCost = Math.min(minCost, colorCost);
		}
		return minCost;
	}
}
