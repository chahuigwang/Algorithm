package SWEA.swea5215_1;

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

    static int numIngredients;
    static int calLimit;

    static int maxTotalScore;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int numTestCase = Integer.parseInt(br.readLine().trim());

        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
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

            maxTotalScore = 0;
            for(int selectCount = 0; selectCount <= numIngredients; selectCount++) {
                combination(0, 0, selectCount, 0, 0);
            }
            sb.append("#").append(testCaseNo).append(" ").append(maxTotalScore).append("\n");
        }
        System.out.println(sb);
    }

    static void combination(int selectIdx, int elementIdx, int selectCount, int totalScore, int totalCal) {
        if(totalCal > calLimit) return;

        if(selectIdx == selectCount) {
            maxTotalScore = Math.max(maxTotalScore, totalScore);
            return;
        }

        if(elementIdx == numIngredients) return;

        int curTasteScore = ingredients[elementIdx].tasteScore;
        int curCal = ingredients[elementIdx].cal;
        combination(selectIdx + 1, elementIdx + 1, selectCount, totalScore + curTasteScore, totalCal + curCal);
        combination(selectIdx, elementIdx + 1, selectCount, totalScore, totalCal);
    }
}
