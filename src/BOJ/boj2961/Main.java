package BOJ.boj2961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Ingredient {
        final int sour;
        final int bitter;

        Ingredient(int sour, int bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }
    }

    static Ingredient[] ingredients;

    static int numIngredients;

    static int minSourBitterDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numIngredients = Integer.parseInt(br.readLine().trim());

        ingredients = new Ingredient[numIngredients];
        for(int ingredientIdx = 0; ingredientIdx < numIngredients; ingredientIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            int curSour = Integer.parseInt(st.nextToken());
            int curBitter = Integer.parseInt(st.nextToken());
            ingredients[ingredientIdx] = new Ingredient(curSour, curBitter);
        }

        minSourBitterDiff = Integer.MAX_VALUE;
        powerSet(0, 1, 0, 0);
        System.out.println(minSourBitterDiff);
    }

    static void powerSet(int visitIdx, int sour, int bitter, int selectedCount) {
        if(visitIdx == numIngredients) {
            if(selectedCount == 0) return;
            minSourBitterDiff = Math.min(minSourBitterDiff, Math.abs(sour - bitter));
            return;
        }

        int curSour = ingredients[visitIdx].sour;
        int curBitter = ingredients[visitIdx].bitter;
        powerSet(visitIdx + 1, sour*curSour, bitter + curBitter, selectedCount + 1); // 현재 요소 선택
        powerSet(visitIdx + 1, sour, bitter, selectedCount); // 현재 요소 선택 x
    }
}
