package SWEA.swea5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * SW Expert Academy 5648
 * @author SSAFY
 *
 *	@see #main(String[])
 *	1. 테스트 케이스 개수를 입력받는다.
 *	2. 각 테스트 케이스마다,
 *
 *		@see #inputTestCase()
 *		2-1. 원자들의 수를 입력받는다.
 *		2-2. 원자 배열을 생성한다.
 *		2-3. 원자 정보를 입력받아 원자 배열에 저장한다.
 *
 *      @see #atomMap
 *      key: 원자의 x좌표, y좌표(Position 객체)
 *      value: 원자의 인덱스(atoms 중 몇 번째 원자)
 *
 *      2-4. MAX_STEP( = 4000)만큼 다음을 반복한다.
 *          @see #afterHalfSecond()
 *          2-4-1. 0.5초 후의 원자들의 위치 정보를 atomMap에 저장
 *
 *          @see #processCrush()
 *          2-4-2. 한 위치에 원자가 두 개 이상있다면 -> 충돌 발생
 *              2-4-2-1. totalEnergy에 해당 위치에 있는 모든 원자들의 energy를 누적
 *              2-4-2-2. 해당 위치의 원자들은 소멸해야 하므로 null로 변경
 */

class Solution {

    static class Atom {
        double x;
        double y;
        int direction;
        int energy;

        Atom(double x, double y, int direction, int energy) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.energy = energy;
        }
    }

    static class Position {
        double x;
        double y;

        Position(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Position)) {
                return false;
            }
            Position position = (Position) o;
            return Double.compare(x, position.x) == 0 && Double.compare(y, position.y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numAtoms;

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;

    static Atom[] atoms;

    static Map<Position, List<Integer>> atomMap;

    static int totalEnergy;

    static final int MAX_STEP = 4000;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 1. 테스트 케이스 개수를 입력받는다.
        int numTestCase = Integer.parseInt(br.readLine().trim());

        // 2. 각 테스트 케이스마다,
        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            totalEnergy = 0;
            inputTestCase();
            atomMap = new HashMap<>();

            for(int step = 0; step < MAX_STEP; step++) {
                if(allNull()) break;
                afterHalfSecond();
                processCrush();
            }

            sb.append("#").append(testCaseNo).append(" ").append(totalEnergy).append("\n");
        }
        System.out.println(sb);
    }

    static void inputTestCase() throws IOException {
        // 2-1. 원자들의 수를 입력받는다.
        numAtoms = Integer.parseInt(br.readLine().trim());
        // 2-2. 원자 배열을 생성한다.
        atoms = new Atom[numAtoms];

        // 2-3. 원자 정보를 입력받아 원자 배열에 저장한다.
        for(int atomIdx = 0; atomIdx < numAtoms; atomIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            double curX = Integer.parseInt(st.nextToken());
            double curY = Integer.parseInt(st.nextToken());
            int curDirection = Integer.parseInt(st.nextToken());
            int curEnergy = Integer.parseInt(st.nextToken());

            atoms[atomIdx] = new Atom(curX, curY, curDirection, curEnergy);
        }
    }

    // 2-4-1. 0.5초 후의 원자들의 위치 정보를 atomMap에 저장
    static void afterHalfSecond() {
        atomMap.clear();
        for(int atomIdx = 0; atomIdx < numAtoms; atomIdx++) {
            Atom curAtom = atoms[atomIdx];
            if(curAtom == null) continue;
            if(atoms[atomIdx].direction == UP) {
                double ny = curAtom.y + 0.5;
                if(ny > 1000) {
                    curAtom = null;
                    continue;
                }
                curAtom.y = ny;
            }
            else if(atoms[atomIdx].direction == DOWN) {
                double ny = curAtom.y - 0.5;
                if(ny < -1000) {
                    curAtom = null;
                    continue;
                }
                curAtom.y = ny;
            }
            else if(atoms[atomIdx].direction == LEFT) {
                double nx = curAtom.x - 0.5;
                if(nx < -1000) {
                    curAtom = null;
                    continue;
                }
                curAtom.x = nx;
            }
            else { // atom.direction == RIGHT
                double nx = curAtom.x + 0.5;
                if(nx > 1000) {
                    curAtom = null;
                    continue;
                }
                curAtom.x = nx;
            }

            atomMap.computeIfAbsent(new Position(atoms[atomIdx].x, atoms[atomIdx].y), key -> new ArrayList<>()).add(atomIdx);
        }
    }

    static void processCrush() {
        for(List<Integer> atomIndexes : atomMap.values()){
            // 2-4-2. 한 위치에 원자가 두 개 이상있다면 -> 충돌 발생
            if(atomIndexes.size() >= 2) {
                for(int atomIdx : atomIndexes) {
                    // 2-4-2-1. totalEnergy에 해당 위치에 있는 모든 원자들의 energy를 누적
                    totalEnergy += atoms[atomIdx].energy;
                    // 2-4-2-2. 해당 위치의 원자들은 소멸해야 하므로 null로 변경
                    atoms[atomIdx] = null;
                }
            }
        }
    }

    static boolean allNull() {
        for(Atom atom : atoms) {
            if(atom != null) return false;
        }
        return true;
    }
}
