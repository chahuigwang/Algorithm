package SWEA.swea3421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numIngredients;
    static int numBadPairs;

    static int[][] badPairs;

    static List<Integer> selectedList;

    static int burgerCount; // 만들 수 있는 버거의 종류의 가지 수

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int numTestCase = Integer.parseInt(br.readLine().trim());

        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            st = new StringTokenizer(br.readLine().trim());

            numIngredients = Integer.parseInt(st.nextToken());
            numBadPairs = Integer.parseInt(st.nextToken());

            badPairs = new int[numBadPairs][2];
            for(int badPairIdx = 0; badPairIdx < numBadPairs; badPairIdx++) {
                st = new StringTokenizer(br.readLine().trim());
                int ingredient1 = Integer.parseInt(st.nextToken());
                int ingredient2 = Integer.parseInt(st.nextToken());
                badPairs[badPairIdx][0] = ingredient1;
                badPairs[badPairIdx][1] = ingredient2;
            }

            selectedList = new ArrayList<>();
            burgerCount = 0;
            powerSet(0);
            sb.append("#").append(testCaseNo).append(" ").append(burgerCount).append("\n");
        }
        System.out.println(sb);
    }

    static void powerSet(int visitIdx) {
        if(visitIdx == numIngredients) {
            if(!containsBadPair()) burgerCount++;
            return;
        }

        selectedList.add(visitIdx + 1);
        powerSet(visitIdx + 1);

        selectedList.remove(selectedList.size() - 1);
        powerSet(visitIdx + 1);
    }

    static boolean containsBadPair() {
        for(int[] pair : badPairs) {
            if(selectedList.contains(pair[0]) && selectedList.contains(pair[1])) return true;
        }
        return false;
    }
}
