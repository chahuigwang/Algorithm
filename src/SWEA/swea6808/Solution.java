package SWEA.swea6808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int[] player1Cards = new int[9]; // 규영이의 카드
    static int[] player2Cards; // 인영이의 카드(순서 고정 x)

    static boolean[] selected;
    static int[] perm;

    static final int PERMUTATION_LENGTH = 9;

    static int winCount;
    static int loseCount;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        int numTestCase = Integer.parseInt(st.nextToken());

        for(int testCase = 1; testCase <= numTestCase; testCase++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int idx = 0; idx < 9; idx++) {
                player1Cards[idx] = Integer.parseInt(st.nextToken());
            }
            player2Cards = getDiffArr(player1Cards);

            selected = new boolean[PERMUTATION_LENGTH];
            perm = new int[PERMUTATION_LENGTH];

            winCount = 0;
            loseCount = 0;
            permutation(0);

            sb = new StringBuilder();
            sb.append("#").append(testCase).append(" ").append(winCount).append(" ").append(loseCount);

            System.out.println(sb.toString());
        }
    }

    static int[] getDiffArr(int[] arr) {
        boolean[] has = new boolean[19]; // 1~18
        for(int n : arr) {
            has[n] = true;
        }

        int[] diffArr = new int[9];
        int idx = 0;
        for(int i = 1; i <= 18; i++){
            if(!has[i]) {
                diffArr[idx++] = i;
            }
        }
        return diffArr;
    }

    static int player1Wins(int[] cards1, int[] cards2) { // player1이 이기면 1 반환, 지면 -1 반환, 무승부면 0 반환
        int score = 0;
        for(int idx = 0; idx < cards1.length; idx++) {
            int curScore = cards1[idx] + cards2[idx];
            if(cards1[idx] > cards2[idx]) {
                score += curScore;
            }
            else score -= curScore;
        }
        return (score > 0) ? 1 : ((score == 0) ? 0 : -1);
    }

    static void permutation(int selectedCount) {
        if(selectedCount == PERMUTATION_LENGTH) {
            int result = player1Wins(player1Cards, perm);
            if(result == 1) winCount++;
            else if(result == -1) loseCount++;
            return;
        }

        for(int idx = 0; idx < PERMUTATION_LENGTH; idx++) {
            if(selected[idx]) continue;

            selected[idx] = true;
            perm[selectedCount] = player2Cards[idx];
            permutation(selectedCount + 1);
            selected[idx] = false;
        }
    }
}