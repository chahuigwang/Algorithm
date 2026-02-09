public class Permutation {

    static final int ELEMENT_COUNT = 3;
    static int[] elementList = {1, 2, 3};
    static boolean[] elementUsedCheckList = new boolean[ELEMENT_COUNT];
    static final int SELECT_COUNT = 2;
    static int[] selectElementList = new int[SELECT_COUNT];

    public static void main(String[] args) {
        permutation(0);
    }

    static void permutation(int selectIdx) {
        // 1. 기저 조건(종료 조건)
        // 모든 원소를 선택했다면,
        if(selectIdx == SELECT_COUNT) {
            for(int idx = 0; idx < SELECT_COUNT; idx++) {
                System.out.print(selectElementList[idx] + " ");
            }
            System.out.println();
            return;
        }

        // 2. 전처리 로직
        // 아직 다 선택하지 않았다면,
        for(int elementIdx = 0; elementIdx < ELEMENT_COUNT; elementIdx++) {
            // 이미 선택한 원소라면 -> 패스
            if(elementUsedCheckList[elementIdx]) continue;

            // 해당 원소를 아직 사용하지 않았다.
            elementUsedCheckList[elementIdx] = true;
            selectElementList[selectIdx] = elementList[elementIdx];

            // 3. 재귀 호출
            permutation(selectIdx + 1);

            // 4. 후처리 로직
            elementUsedCheckList[elementIdx] = false;
        }
    }
}
