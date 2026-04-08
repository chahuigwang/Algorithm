import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 5215. 햄버거 다이어트
 * @author chahuigwang
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #initTestCase()
 *		2-1. 재료의 수, 제한 칼로리를 입력받는다.
 *		2-2. 재료들의 정보를 저장할 배열을 생성한다.
 *		2-3. 재료들의 정보를 입력받아 배열에 저장한다.
 *		2-4. dp 테이블을 생성한다. (maxScores[cal] = 칼로리 cal까지 재료를 선택할 때 최대 점수) 
 *
 *		@see #calcMaxScore()
 *		2-5. 모든 재료들에 대해, 해당 재료를 선택하지 않는 경우와 선택하는 경우 중 더 큰 점수로 dp 테이블을 갱신한다.
 *
 *		2-6. 테스트 케이스 번호와 함께 정답을 출력한다.
 *
 */

class Solution {
	
	static class Ingredient {
		int score;
		int calorie;
		
		Ingredient(int score, int calorie) {
			this.score = score;
			this.calorie = calorie;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int ingredientCount;
	static int calorieLimit;
	
	static Ingredient[] ingredients;
	
	static int[] maxScores;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스마다,
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			// 2-6. 테스트 케이스 번호와 함께 정답을 출력한다.
			sb.append("#").append(testCaseNo).append(" ").append(calcMaxScore()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		// 2-1. 재료의 수, 제한 칼로리를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		ingredientCount = Integer.parseInt(st.nextToken());
		calorieLimit = Integer.parseInt(st.nextToken());
		
		// 2-2. 재료들의 정보를 저장할 배열을 생성한다.
		ingredients = new Ingredient[ingredientCount];
		
		// 2-3. 재료들의 정보를 입력받아 배열에 저장한다.
		int score, calorie;
		for(int ingredientIdx = 0; ingredientIdx < ingredientCount; ingredientIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			score = Integer.parseInt(st.nextToken());
			calorie = Integer.parseInt(st.nextToken());
			ingredients[ingredientIdx] = new Ingredient(score, calorie);
		}
		
		// 2-4. dp 테이블을 생성한다. (maxScores[cal] = 칼로리 cal까지 재료를 선택할 때 최대 점수) 
		maxScores = new int[calorieLimit + 1];
	}
	
	static int calcMaxScore() {
		// 2-5. 모든 재료들에 대해,
		for(int ingredientIdx = 0; ingredientIdx < ingredientCount; ingredientIdx++) {
			for(int cal = calorieLimit; cal >= ingredients[ingredientIdx].calorie; cal--) {
				// 해당 재료를 선택하지 않는 경우와 선택하는 경우 중 더 큰 점수로 dp 테이블을 갱신한다.
				maxScores[cal] = Math.max(
						maxScores[cal],
						maxScores[cal - ingredients[ingredientIdx].calorie] + ingredients[ingredientIdx].score);
			}
		}
		
		return maxScores[calorieLimit];
	}
}
