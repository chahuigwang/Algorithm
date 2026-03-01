package SWEA.swea22416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 22416. 현정 공주의 보물 검사
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 *
 *  @see #initTestCase()
 *  2-1. 방의 개수와 시작 위치를 입력받는다.
 *  2-2. 보물의 위치를 저장할 리스트 treasurePoints를 생성한다.
 *  2-3. 방의 정보를 입력받아 보물이 있는 위치를 treasurePoints에 삽입한다.
 *
 *  @see #getMinMoveCount()
 *  2-4. 시작 위치에서 왼쪽, 오른쪽으로 0 ~ (방의 개수 - 1)칸 이동하며,
 *      2-4-1. 현재 위치에서 모든 보물을 확인할 수 있는지 검사한다.
 *
 *      @see #canCheckAllTreasures(int)
 *      2-4-1-1. 현재 위치에서 모든 보물을 확인할 수 있는지 여부를 반환한다.
 *
 *          @see #findClosestTreasure(int)
 *          2-4-1-1-1. 입력 위치에서 가장 가까운 보물의 위치의 리스트를 반환한다.
 *              2-4-1-1-1-1. 입력 위치가 보물의 위치라면 입력 위치를 리스트에 담고 리스트를 반환한다.
 *              2-4-1-1-1-2. 입력 위치에서 왼쪽, 오른쪽으로 한칸씩 이동하며 이동한 위치에 보물이 있다면 리스트에 이동한 위치를 삽입힌다.
 *              2-4-1-1-1-3. 가장 가까운 보물의 위치가 담긴 리스트를 반환한다.
 *
 *          2-4-1-1-2. 모든 보물을 모두 확인했다면 true를 반환한다.
 *          2-4-1-1-3. 가장 가까운 보물이 두개라면(결정 장애로 화가 나게 되므로) false를 반환한다.
 *          2-4-1-1-4. 가장 가까운 보물을 방문 처리한다.(리스트에서 제거)
 *          2-4-1-1-5. 가장 가까운 보물에서 해당 메서드를 다시 호출(재귀)하고, 그 결과가 현재 위치에서 모든 보물을 확인할 수 있는지 여부가 된다.
 *          2-4-1-1-6. 다음 검사를 위해 리스트에서 제거했던 보물의 위치를 다시 추가한다.(백트래킹)
 *
 *      2-4-2. 현재 위치에서 모든 보물을 확인할 수 있다면 현재까지 이동한 칸수가 정답이된다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int roomCount;
    static int startPoint;
    static List<Integer> treasurePoints;
    static int moveCount;

    public static void main(String[] args) throws IOException {
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            initTestCase();
            getMinMoveCount();
            sb.append("#").append(testCaseNo).append(" ").append(moveCount).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        // 2-1. 상자의 개수와 시작 위치를 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        roomCount = Integer.parseInt(st.nextToken());
        startPoint = Integer.parseInt(st.nextToken());

        // 2-2. 보물의 위치를 저장할 리스트 treasurePoints를 생성한다.
        treasurePoints = new ArrayList<>();

        // 2-3. 방의 정보를 입력받아 보물이 있는 위치를 treasurePoints에 삽입한다.
        st = new StringTokenizer(br.readLine().trim());
        int existsTreasure;
        for(int roomIdx = 1; roomIdx <= roomCount; roomIdx++) {
            existsTreasure = Integer.parseInt(st.nextToken());
            if(existsTreasure == 1) treasurePoints.add(roomIdx);

        }
    }

    static void getMinMoveCount() {
        // 2-4. 시작 위치에서 왼쪽, 오른쪽으로 0 ~ (방의 개수 - 1)칸 이동하며,
        for(moveCount = 0; moveCount < roomCount; moveCount++) {
            // 2-4-1. 현재 위치에서 모든 보물을 확인할 수 있는지 검사한다.
            if(canCheckAllTreasures(startPoint - moveCount) || canCheckAllTreasures(startPoint + moveCount)) break;
        }
    }

    static boolean canCheckAllTreasures(int point) {
        // 2-4-1-1-2. 모든 보물을 모두 확인했다면 true를 반환한다.
        if(treasurePoints.isEmpty()) return true;

        // 2-4-1-1-3. 가장 가까운 보물이 두개라면(결정 장애로 화가 나게 되므로) false를 반환한다.
        List<Integer> closestTreasurePoints = findClosestTreasure(point);
        if(closestTreasurePoints.size() == 2) return false;

        int closestTreasurePoint = closestTreasurePoints.get(0);

        treasurePoints.remove(Integer.valueOf(closestTreasurePoint));
        boolean canCheckAllTreasures = canCheckAllTreasures(closestTreasurePoint);
        treasurePoints.add(closestTreasurePoint);
        return canCheckAllTreasures;
    }

    static List<Integer> findClosestTreasure(int point) {
        List<Integer> closestTreasurePoints = new ArrayList<>();

        // 2-4-1-1-1-1. 입력 위치가 보물의 위치라면 입력 위치를 리스트에 담고 리스트를 반환한다.
        if(treasurePoints.contains(point)) { // 입력 위치가 보물의 위치라면
            closestTreasurePoints.add(point);
            return closestTreasurePoints;
        }

        int left, right;
        for(int move = 1; move < roomCount; move++) {
            left = point - move;
            right = point + move;

            // 2-4-1-1-1-2. 입력 위치에서 왼쪽, 오른쪽으로 한칸씩 이동하며 이동한 위치에 보물이 있다면 리스트에 이동한 위치를 삽입힌다.
            if(treasurePoints.contains(left) && treasurePoints.contains(right)) { // 왼쪽, 오른쪽 모두 보물이 있다면
                closestTreasurePoints.add(left);
                closestTreasurePoints.add(right);
                break;
            }
            else if(treasurePoints.contains(left)) { // 왼쪽에 보물이 있다면
                closestTreasurePoints.add(left);
                break;
            }
            else if(treasurePoints.contains(right)){ // 오른쪽에 보물이 있다면
                closestTreasurePoints.add(right);
                break;
            }
        }

        // 2-4-1-1-1-3. 가장 가까운 보물의 위치가 담긴 리스트를 반환한다.
        return closestTreasurePoints;
    }
}
