package SWEA.swea18654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 18654. 건우와 철준이의 땅따먹기
 * - 각 땅에 대해 건우가 차지하는 경우 / 철준이가 차지하는 경우를 나누어 가능한 모든 경우를 계산(부분 집합)
 * - 하나의 경우가 완성되었을 때 건우가 차지한 땅, 철준이가 차지한 땅 모두가 연결되어있는지 확인(dfs / bfs 아무거나 가능)
 * - 모두 연결되어 있다면, 점수의 차를 계산하고 최솟값 갱신
 *
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *
 *  @see #initTestCase()
 *  2-1. 땅의 개수를 입력받는다.
 *  2-2. 연결 정보를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
 *  2-3. 땅의 점수를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
 *  2-4. 최소 점수 차이를 정수의 최댓값으로 초기화한다.
 *
 *  @see #playLandGame(int) : 모든 땅에 대해서 건우가 차지하는 경우와 철준이가 차지하는 경우를 고려해 땅따먹기 게임을 진행한다.
 *  2-5. 모든 땅이 차지되었다면,
 *      2-5-1. 건우와 철준이가 각각 차지한 땅이 모두 연결되어있지 않다면 점수를 계산하지 않고 게임 종료
 *      2-5-2. 건우와 철준이의 점수차를 계산한다.
 *      2-5-3. 현재까지의 최소 점수차와 비교하여 더 낮은 값을 최소 점수차로 저장한다.
 *
 *  2-6. 테스트 케이스 번호와 함께 최소 점수 차를 출력한다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int landCount;
    static boolean[][] connected;
    static int[] landScores;

    static List<Integer> player1Lands = new ArrayList<>();
    static List<Integer> player2Lands = new ArrayList<>();

    static int minScoreDiff;

    static boolean[] visited1;
    static boolean[] visited2;

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수를 입력받는다.
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스마다,
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            playLandGame(1);
            // 2-6. 테스트 케이스 번호와 함께 최소 점수 차를 출력한다.
            sb.append("#").append(testCaseNo).append(" ").append(minScoreDiff).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        // 2-1. 땅의 개수를 입력받는다.
        landCount = Integer.parseInt(br.readLine().trim());

        // 2-2. 연결 정보를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
        connected = new boolean[landCount + 1][landCount + 1];
        for(int from = 1; from <= landCount; from++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int to = 1; to <= landCount; to++) {
                if(Integer.parseInt(st.nextToken()) == 1) connected[from][to] = true;
            }
        }

        // 2-3. 땅의 점수를 저장할 배열을 생성하고, 정보를 입력받아 저장한다.
        landScores = new int[landCount + 1];
        st = new StringTokenizer(br.readLine().trim());
        for(int landNo = 1; landNo <= landCount; landNo++) {
            landScores[landNo] = Integer.parseInt(st.nextToken());
        }

        // 2-4. 최소 점수 차이를 정수의 최댓값으로 초기화한다.
        minScoreDiff = Integer.MAX_VALUE;
    }

    static void playLandGame(int selectIdx) {
        // 2-5. 모든 땅이 차지되었다면,
        if(selectIdx == landCount + 1) {
            visited1 = new boolean[landCount + 1];
            visited2 = new boolean[landCount + 1];
            // 2-5-1. 건우와 철준이가 각각 차지한 땅이 모두 연결되어있지 않다면 점수를 계산하지 않고 게임 종료
            if(!allConnected(player1Lands, 1) || !allConnected(player2Lands, 2)) return;
            // 2-5-2. 건우와 철준이의 점수차를 계산한다.
            int scoreDiff = Math.abs(calcScore(player1Lands) - calcScore(player2Lands));
            // 2-5-3. 현재까지의 최소 점수차와 비교하여 더 낮은 값을 최소 점수차로 저장한다.
            minScoreDiff = Math.min(minScoreDiff, scoreDiff);
            return;
        }

        // 건우가 땅을 차지한다.
        player1Lands.add(selectIdx);
        playLandGame(selectIdx + 1);
        player1Lands.remove(Integer.valueOf(selectIdx));

        // 철준이가 땅을 차지한다.
        player2Lands.add(selectIdx);
        playLandGame(selectIdx + 1);
        player2Lands.remove(Integer.valueOf(selectIdx));
    }

    static boolean allConnected(List<Integer> lands, int playerNo) {
        if(lands.isEmpty()) return false;
        return lands.size() == countConnect(lands.get(0), playerNo);
    }

    static int countConnect(int landNo, int playerNo) {
        int connectCount = 1;

        if(playerNo == 1) {
            visited1[landNo] = true;
            for(int landIdx = 1; landIdx <= landCount; landIdx++) {
                if(player1Lands.contains(landIdx) && connected[landNo][landIdx] && !visited1[landIdx]) {
                    connectCount += countConnect(landIdx, playerNo);
                }
            }
        }
        else if(playerNo == 2) {
            visited2[landNo] = true;
            for(int landIdx = 1; landIdx <= landCount; landIdx++) {
                if(player2Lands.contains(landIdx) && connected[landNo][landIdx] && !visited2[landIdx]) {
                    connectCount += countConnect(landIdx, playerNo);
                }
            }
        }

        return connectCount;
    }


    static int calcScore(List<Integer> lands) {
        int score = 0;
        for(int landNo : lands) {
            score += landScores[landNo];
        }
        return score;
    }
}
