package SWEA.swea5215_2;

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
            powerSet(0, 0, 0);
            sb.append("#").append(testCaseNo).append(" ").append(maxTotalScore).append("\n");
        }
        System.out.println(sb);
    }

    static void powerSet(int visitIdx, int totalScore, int totalCal) {
        if(totalCal > calLimit) return;

        if(visitIdx == numIngredients) {
            maxTotalScore = Math.max(maxTotalScore, totalScore);
            return;
        }

        int curTasteScore = ingredients[visitIdx].tasteScore;
        int curCal = ingredients[visitIdx].cal;
        powerSet(visitIdx + 1, totalScore + curTasteScore, totalCal + curCal); // 현재 원소 선택
        powerSet(visitIdx + 1, totalScore, totalCal); // 현재 원소 선택 x
    }
}
