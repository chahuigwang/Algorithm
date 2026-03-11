import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 15686. 치킨 배달
 * @author chahuigwang
 * 
 * @see #main(String[])
 * 
 * @see #init()
 * 1. 맵의 한변의 크기를 입력받는다.
 * 2. 최대 치킨집의 개수를 입력받는다.
 * 3. 도시의 정보를 입력받아 집이 있는 위치와 치킨집이 있는 위치를 저장한다.
 * 4. 선택한 치킨집의 인덱스를 저장할 배열을 생성한다.
 * 5. 도시의 치킨 거리의 최솟값을 정수의 최댓값으로 초기화한다.
 * 
 * @see #selectChickenStores(int, int)
 * 6. 모든 치킨집을 순회하며 현재 치킨집을 선택하는 경우와 선택하지 않는 경우를 고려하여 가능한 모든 조합을 생성한다.
 * 7. 조합이 완성되면 치킨 거리를 계산하고 최솟값을 업데이트한다.
 *
 */

public class Main {

	static class Pos {
		int x;
		int y;
		
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int mapSize;
	static int maxChickenStoreCount;
	
	static List<Pos> homes = new ArrayList<>();
	static List<Pos> chickenStores = new ArrayList<>();
	static int homeCount;
	static int chickenStoreCount;
	
	static int[] selectChickenStoreIndxes;
	static int minChickenDistance;
	
	public static void main(String[] args) throws IOException {
		init();
		selectChickenStores(0, 0);
		System.out.println(minChickenDistance);
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		// 1. 맵의 한변의 크기를 입력받는다.
		mapSize = Integer.parseInt(st.nextToken());
		// 2. 최대 치킨집의 개수를 입력받는다.
		maxChickenStoreCount = Integer.parseInt(st.nextToken());
	
		// 3. 도시의 정보를 입력받아 집이 있는 위치와 치킨집이 있는 위치를 저장한다.
		homes.clear();
		chickenStores.clear();
		int input;
		for(int x = 0; x < mapSize; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int y = 0; y < mapSize; y++) {
				input = Integer.parseInt(st.nextToken());
				if(input == 1) homes.add(new Pos(x, y));
				else if(input == 2) chickenStores.add(new Pos(x, y));
			}
		}
		homeCount = homes.size();
		chickenStoreCount = chickenStores.size();
		
		// 4. 선택한 치킨집의 인덱스를 저장할 배열을 생성한다.
		selectChickenStoreIndxes = new int[maxChickenStoreCount];
		// 5. 도시의 치킨 거리의 최솟값을 정수의 최댓값으로 초기화한다.
		minChickenDistance = Integer.MAX_VALUE;
	}
	
	static void selectChickenStores(int selectIdx, int chickenStoreIdx) {
		// 7. 조합이 완성되면 치킨 거리를 계산하고 최솟값을 업데이트한다.
		if(selectIdx == maxChickenStoreCount) {
			minChickenDistance = Math.min(minChickenDistance, calcChickenDistance());
			return;
		}
		
		// 6. 모든 치킨집을 순회하며 현재 치킨집을 선택하는 경우와 선택하지 않는 경우를 고려하여 가능한 모든 조합을 생성한다.
		for(int idx = chickenStoreIdx; idx < chickenStoreCount; idx++) {
			selectChickenStoreIndxes[selectIdx] = idx;
			selectChickenStores(selectIdx + 1, idx + 1);
		}
	}
	
	static int calcChickenDistance() {
		int chickenDistance = 0;
		int minDist;
		Pos curHomePos;
		Pos curChickenStorePos;
		int curDist;
		for(int homeIdx = 0; homeIdx < homeCount; homeIdx++) {
			curHomePos = homes.get(homeIdx);
			minDist = Integer.MAX_VALUE;
			for(int selectIdx = 0; selectIdx < maxChickenStoreCount; selectIdx++) {
				curChickenStorePos = chickenStores.get(selectChickenStoreIndxes[selectIdx]);
				curDist = calcDistance(curHomePos, curChickenStorePos);
				if(curDist < minDist) minDist = curDist;
			}
			chickenDistance += minDist;
		}
		return chickenDistance;
	}
	
	static int calcDistance(Pos homePos, Pos chickenStorePos) {
		return Math.abs(homePos.x - chickenStorePos.x) + Math.abs(homePos.y - chickenStorePos.y);
	}
}
