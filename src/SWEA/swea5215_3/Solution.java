package SWEA.swea5215_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static class Ingredient {
        int tasteScore;
        int cal;

        Ingredient(int tasteScore, int cal) {
            this.tasteScore = tasteScore;
            this.cal = cal;
        }
    }

    static Ingredient[] ingredients;

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numIngredients; // 재료의 개수
    static int calLimit; // 칼로리 제한

    static int maxTotalScore; // 최대 점수

    static int[] select;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int numTestCase = Integer.parseInt(br.readLine().trim());

        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            maxTotalScore = 0;
            inputTestCase();

            for(int selectCount = 0; selectCount <= numIngredients; selectCount++) { // 0개부터 numIngredients개까지 선택
                selectIngredients(selectCount);
            }

            sb.append("#").append(testCaseNo).append(" ").append(maxTotalScore).append("\n");
        }
        System.out.println(sb);
    }

    static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numIngredients = Integer.parseInt(st.nextToken());
        calLimit = Integer.parseInt(st.nextToken());

        ingredients = new Ingredient[numIngredients];

        for(int ingredientIdx = 0; ingredientIdx < numIngredients; ingredientIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            int curTasteScore = Integer.parseInt(st.nextToken());
            int curCal = Integer.parseInt(st.nextToken());
            ingredients[ingredientIdx] = new Ingredient(curTasteScore, curCal);
        }
    }

    // numIngredients개의 재료 중 num개 선택
    static void selectIngredients(int num) {
        select = new int[numIngredients];
        for(int selectIdx = numIngredients - num; selectIdx < numIngredients; selectIdx++) {
            select[selectIdx] = 1; // 뒤 num개를 1로 설정
        }

        do {
            updateMaxTotalScore();
        } while(nextPermutation());
    }

    // select[idx] == 1 인 idx의 재료의 점수를 합한 뒤 maxTotalScore 업데이트
    static void updateMaxTotalScore() {
        int totalScore = 0;
        int totalCal = 0;
        for(int idx = 0; idx < numIngredients; idx++) {
            if(select[idx] == 1) {
                int curScore = ingredients[idx].tasteScore;
                int curCal = ingredients[idx].cal;
                totalCal += curCal;
                if(totalCal > calLimit) return;
                totalScore += curScore;
            }
        }
        maxTotalScore = Math.max(maxTotalScore, totalScore);
    }

    static boolean nextPermutation() {
        int i = numIngredients - 1;
        while(i > 0 && select[i-1] >= select[i]) i--;
        if(i == 0) return false;

        int j = numIngredients - 1;
        while(select[i-1] >= select[j]) j--;

        swap(i - 1, j);
        reverse(i, numIngredients - 1);
        return true;
    }

    // select 배열의 idx1 위치의 값과 idx2 위치의 값을 교환
    static void swap(int idx1, int idx2) {
        int temp = select[idx1];
        select[idx1] = select[idx2];
        select[idx2] = temp;
    }

    // select 배열의 start부터 end까지의 값을 뒤집는다.
    static void reverse(int start, int end) {
        while(start < end) {
            swap(start++, end--);
        }
    }
}
