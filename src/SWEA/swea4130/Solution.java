package SWEA.swea4130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SW Expert Academy
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *      2-1. 자석을 회전 시키는 횟수를 입력 받는다.
 *      2-2. 자석을 저장할 리스트 magnets를 생성한다.
 *      2-3. 4개의 자석 정보를 입력받고 magnets에 추가한다.
 *      2-4. 자석을 회전 시키는 횟수 rotateCount만큼 다음을 반복한다.
 *          2-4-1. 자석 번호 magnetNo(0~3)을 입력받는다.
 *          2-4-2. 회전 방향 direction(1 or -1)을 입력받는다.
 *          2-4-3. 현재 상태의 자석 번호, 회전 방향, 자석 리스트를 임시 변수 originMagnetNo, originDirection, originMagnets에 저장한다.
 *          2-4-4. 입력으로 주어진 자석 번호의 왼쪽에 있는 자석들을 보며 붙어있는 날의 자성이 다르다면 반대 방향으로 회전시킨다.
 *                  @see #rotate(LinkedList, int)
 *                  1. 회전 방향이 시계 방향이라면 @see #rotateClockWise(LinkedList)를 호출한다.
 *                  2. 회전 방향이 반시계 방향이라면 @see #rotateCounterClockWise(LinkedList)를 호출한다.
 *          2-4-5. 자석 번호, 회전 방향을 원상 복구한다.
 *          2-4-6. 입력으로 주어진 자석 번호의 오른쪽에 있는 자석들을 보며 붙어있는 날의 자성이 다르다면 반대 방향으로 회전시킨다.
 *                  @see #rotate(LinkedList, int)
 *          2-4-7. 입력에 주어진 자석 번호에 해당하는 자석을 회전시킨다.
 *          2-4-8. @see #getScore(List) 최종 결과의 점수를 계산한다.
 *          2-4-9. 테스트 케이스 번호와 함께 점수를 출력한다.
 *
 */

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        int T = Integer.parseInt(st.nextToken());
        for(int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine().trim());
            int rotateCount = Integer.parseInt(st.nextToken());

            List<LinkedList<Integer>> magnets = new ArrayList<>();
            for(int magentNo = 0; magentNo < 4; magentNo++) { // magnetNo : 자석 번호 (0~3)
                LinkedList<Integer> magnet = new LinkedList<>();
                st = new StringTokenizer(br.readLine().trim());
                for(int magentIdx = 0; magentIdx < 8; magentIdx++) { // magentIdx : 자석의 한칸 (0~7)
                    magnet.add(Integer.parseInt(st.nextToken()));
                }
                magnets.add(magnet);
            }

            for(int iter = 0; iter < rotateCount; iter++) {
                st = new StringTokenizer(br.readLine().trim());
                int magnetNo = Integer.parseInt(st.nextToken()) - 1;
                int direction = Integer.parseInt(st.nextToken());

                int originMagnetNo = magnetNo;
                int originDirection = direction;
                List<LinkedList<Integer>> originMagnets = copyList(magnets);

                while(--magnetNo >= 0) { // 왼쪽에 있는 자석들을 검사
                    LinkedList<Integer> cur = originMagnets.get(++magnetNo);
                    LinkedList<Integer> left = originMagnets.get(--magnetNo);
                    if(!cur.get(6).equals(left.get(2))) { // 다른 극이라면
                        direction = direction*(-1);
                        rotate(magnets.get(magnetNo), direction);
                    }
                    else break;
                }
                magnetNo = originMagnetNo;
                direction = originDirection;

                while(++magnetNo <= 3) {
                    LinkedList<Integer> cur = originMagnets.get(--magnetNo);
                    LinkedList<Integer> right = originMagnets.get(++magnetNo);
                    if(!cur.get(2).equals(right.get(6))) {
                        direction = direction*(-1);
                        rotate(magnets.get(magnetNo), direction);
                    }
                    else break;
                }

                rotate(magnets.get(originMagnetNo), originDirection);
            }

            sb = new StringBuilder();
            sb.append("#").append(testCase).append(" ").append(getScore(magnets));
            System.out.println(sb.toString());
        }
    }

    static List<LinkedList<Integer>> copyList(List<LinkedList<Integer>> list) {
        List<LinkedList<Integer>> copied = new ArrayList<>();
        for(LinkedList<Integer> element : list) {
            copied.add(new LinkedList<>(element));
        }
        return copied;
    }

    static void rotate(LinkedList<Integer> list, int direction) {
        if(direction == 1) {
            rotateClockWise(list);
        }
        else { // direction == -1
            rotateCounterClockWise(list);
        }
    }

    static void rotateClockWise(LinkedList<Integer> list) {
        list.addFirst(list.removeLast());
    }

    static void rotateCounterClockWise(LinkedList<Integer> list) {
        list.addLast(list.removeFirst());
    }

    static int getScore(List<LinkedList<Integer>> list) {
        int score = 0;
        for(int idx = 0; idx < 4; idx++){
            int cur = list.get(idx).get(0);
            if(cur == 1) {
                score += (int) Math.pow(2, idx);
            }
        }
        return score;
    }
}
