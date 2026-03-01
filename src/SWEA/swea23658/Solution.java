package SWEA.swea23658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 23686. 준혁이의 블록 쌓기
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *
 *  @see #initTestCase()
 *  2-1. 블록을 쌓을 수 있는 위치의 수와 패널티를 입력받는다.
 *  2-2. 1번 기계가 각 위치마다 쌓을 수 있는 블록의 수를 저장할 배열을 생성하고, 정보를 입력받아 배열에 저장한다.
 *  2-3. 2번 기계가 각 위치마다 쌓을 수 있는 블록의 수를 저장할 배열을 생성하고, 정보를 입력받아 배열에 저장한다.
 *  2-4. 최대로 쌓을 수 있는 블록의 수를 정수의 최솟값으로 초기화한다.
 *
 *  @see #stackBlocks(int, int, int)  : 첫 번째 위치부터 마지막 위치까지, 1번 기계로 블록을 쌓는 경우와 2번 기계로 블록을 쌓는 경우를 모두 고려하여 완전 탐색한다.
 *  2-5. 마지막 위치까지 블록을 쌓았다면 쌓은 블록의 수와 현재까지 최대로 쌓은 블록의 수를 비교하여, 최댓값 갱신이 필요하다면 갱신한다.
 *  2-6. 처음으로 블록을 쌓는 경우(a) / 이전에 1번 기계로 블록을 쌓은 경우(b) / 이전에 2번 기계로 블록을 쌓은 경우(c)
 *      a)
 *          - 이전 기계를 1번으로 설정하고, 1번 기계의 현재 위치의 제한만큼 블록을 쌓고 다음 위치로 넘어간다.
 *          - 이전 기계를 2번으로 설정하고, 2번 기계의 현재 위치의 제한만큼 블록을 쌓고 다음 위치로 넘어간다.
 *      b)
 *          - 이전 기계를 1번으로 설정하고, (1번 기계의 현재 위치의 제한 - 패널티)만큼 블록을 쌓고 다음 위치로 넘어간다.
 *          - 이전 기계를 2번으로 설정하고, 2번 기계의 현재 위치의 제한만큼 블록을 쌓고 다음 위치로 넘어간다.
 *      c)
 *          - 이전 기계를 1번으로 설정하고, 1번 기계의 현재 위치의 제한만큼 블록을 쌓고 다음 위치로 넘어간다.
 *          - 이전 기계를 2번으로 설정하고, (2번 기계의 현재 위치의 제한 - 패널티)만큼 블록을 쌓고 다음 위치로 넘어간다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int positionCount;
    static int penalty;

    static int[] machine1Limits;
    static int[] machine2Limits;

    static int maxStackBlockCount;

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수를 입력받는다.
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 테스트 케이스마다,
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            stackBlocks(0, 0, 0);
            sb.append("#").append(testCaseNo).append(" ").append(maxStackBlockCount).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        // 2-1. 블록을 쌓을 수 있는 위치의 수와 패널티를 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        positionCount = Integer.parseInt(st.nextToken());
        penalty = Integer.parseInt(st.nextToken());

        // 2-2. 1번 기계가 각 위치마다 쌓을 수 있는 블록의 수를 저장할 배열을 생성하고, 정보를 입력받아 배열에 저장한다.
        machine1Limits = new int[positionCount];
        st = new StringTokenizer(br.readLine().trim());
        for(int position = 0; position < positionCount; position++) {
            machine1Limits[position] = Integer.parseInt(st.nextToken());
        }

        // 2-3. 2번 기계가 각 위치마다 쌓을 수 있는 블록의 수를 저장할 배열을 생성하고, 정보를 입력받아 배열에 저장한다.
        machine2Limits = new int[positionCount];
        st = new StringTokenizer(br.readLine().trim());
        for(int position = 0; position < positionCount; position++) {
            machine2Limits[position] = Integer.parseInt(st.nextToken());
        }

        // 2-4. 최대로 쌓을 수 있는 블록의 수를 정수의 최솟값으로 초기화한다.
        maxStackBlockCount = Integer.MIN_VALUE;
    }

    static void stackBlocks(int position, int prevMachineNo, int stackBlockCount) {
        if(position == positionCount) {
            maxStackBlockCount = Math.max(maxStackBlockCount, stackBlockCount);
            return;
        }

        if(prevMachineNo == 0) { // 처음으로 블록을 쌓는 경우
            stackBlocks(position + 1, 1, stackBlockCount + machine1Limits[position]); // 1번 기계로 블록을 쌓는다.
            stackBlocks(position + 1, 2, stackBlockCount + machine2Limits[position]); // 2번 기계로 블록을 쌓는다.
        }
        else if(prevMachineNo == 1) { // 이전에 1번 기계로 블록을 쌓았다면
            stackBlocks(position + 1, 1, stackBlockCount + machine1Limits[position] - penalty); // 1번 기계로 블록을 쌓는다.
            stackBlocks(position + 1, 2, stackBlockCount + machine2Limits[position]); // 2번 기계로 블록을 쌓는다.
        }
        else if(prevMachineNo == 2) { // 이전에 2번 기계로 블록을 쌓았다면
            stackBlocks(position + 1, 1, stackBlockCount + machine1Limits[position]); // 1번 기계로 블록을 쌓는다.
            stackBlocks(position + 1, 2, stackBlockCount + machine2Limits[position] - penalty); // 2번 기계로 블록을 쌓는다.
        }
    }
}
