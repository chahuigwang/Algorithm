package SWEA.swea25069;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 25069. 근원이의 면접
 *
 * [접근]
 * - 보너스를 받으면 현재 점수의 2배가 된다.
 * - 보너스를 1회 이상 받을 수 밖에 없는 경우라면 최대한 점수가 적을 때 보너스를 받아야 최종 점수가 낮아질 것이다.
 * - 점수가 적을 때 보너스를 받는다? -> 앞 문제에서 연속으로 문제를 맞춰 보너스를 받는다.
 *     - 반대로 말하면 뒤에서 보너스를 못 받게 해야한다.
 *     -> 뒤에서부터 바라봐서 K-1(보너스 조건 -1)개 맞추면 그 다음에 오답이 오게 한다. (예시 : 보너스 조건이 3일때 ...XOOXOO)
 *
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *
 *  @see #initTestCase()
 *  2-1. 문제 개수를 입력받는다.
 *  2-2. 맞춘 개수를 입력받는다.
 *  2-3. 틀린 개수를 (문제 개수 - 맞춘 개수)로 초기화한다.
 *  2-4. 보너스 조건을 입력받는다.
 *  2-5. 정답 여부를 저장할 배열 isCorrect를 생성하고 초기화한다.
 *
 *  @see #makeScoreLeast()
 *  2-6. 맞춘 개수가 보너스 조건보다 작다면 맞춘 개수가 최종 점수가 된다.
 *  2-7. 틀린 개수만큼 정답 여부 배열 뒤에서부터 K-1개의 정답 뒤에 오답이 오게끔 false를 삽입한다.
 *  	2-7-1. 만약 오답을 삽입할 위치가 배열의 범위를 벗어난다면, 더 이상 보너스를 받을 수 없다는 뜻이므로 맞춘 개수가 최종 점수가 된다.
 *
 *  @see #calculateScore()
 *  2-8. 완성된 정답 여부 배열을 보고 점수를 계산한다.
 *
 *  2-9. 테스트 케이스와 함께 정답을 출력한다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int problemCount;
    static int correctCount;
    static int incorrectCount;
    static int bonusCond;
    static boolean[] isCorrect;

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수를 입력받는다.
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스마다,
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            makeScoreLeast();
            // 2-9. 테스트 케이스와 함께 정답을 출력한다.
            sb.append("#").append(testCaseNo).append(" ").append(makeScoreLeast()).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        // 2-1. 문제 개수를 입력받는다.
        problemCount = Integer.parseInt(st.nextToken());
        // 2-2. 맞춘 개수를 입력받는다.
        correctCount = Integer.parseInt(st.nextToken());
        // 2-3. 틀린 개수를 (문제 개수 - 맞춘 개수)로 초기화한다.
        incorrectCount = problemCount - correctCount;
        // 2-4. 보너스 조건을 입력받는다.
        bonusCond = Integer.parseInt(st.nextToken());
        // 2-5. 정답 여부를 저장할 배열 isCorrect를 생성하고 초기화한다.
        isCorrect = new boolean[problemCount];
        Arrays.fill(isCorrect, true);
    }

    static int makeScoreLeast() {
        // 2-6. 맞춘 개수가 보너스 조건보다 작다면 맞춘 개수가 최종 점수가 된다.
        if(correctCount < bonusCond) return correctCount;
        // 2-7. 틀린 개수만큼 정답 여부 배열 뒤에서부터 K-1개의 정답 뒤에 오답이 오게끔 false를 삽입한다.
        int incorrectIdx;
        for(int incorrectCnt = 1; incorrectCnt <= incorrectCount; incorrectCnt++) {
            incorrectIdx = problemCount - bonusCond * incorrectCnt;
            // 2-7-1. 만약 오답을 삽입할 위치가 배열의 범위를 벗어난다면, 더 이상 보너스를 받을 수 없다는 뜻이므로 맞춘 개수가 최종 점수가 된다.
            if(incorrectIdx < 0) {
                return correctCount;
            }
            isCorrect[incorrectIdx] = false;
        }
        return calculateScore();
    }

    static int calculateScore() {
        int score = 0;
        int correctStreak = 0; // 연속 정답 개수
        for(int probIdx = 0; probIdx < problemCount; probIdx++) {
            if(isCorrect[probIdx]) {
                score++;
                correctStreak++;
                if(correctStreak == bonusCond) {
                    score *= 2;
                    correctStreak = 0;
                }
            }
            else correctStreak = 0;
        }
        return score;
    }
}

