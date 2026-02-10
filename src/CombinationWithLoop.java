public class CombinationWithLoop {

    static int[] elementList = {1, 2, 3};
    static final int ELEMENT_COUNT = 3;
    static final int SELECT_COUNT = 2;
    static int[] selectElementList = new int[SELECT_COUNT];

    public static void main(String[] args) {
        combination(0, 0);
    }

    static void combination(int selectIdx, int elementIdx) {
        // 1. 모든 원소를 선택했다면 종료
        if(selectIdx == SELECT_COUNT) {
            for(int idx = 0; idx < SELECT_COUNT; idx++) {
                System.out.print(selectElementList[idx] + " ");
            }
            System.out.println();
            return;
        }

        for(int idx = elementIdx; idx < ELEMENT_COUNT; idx++) {
            selectElementList[selectIdx] = elementList[idx];
            combination(selectIdx + 1, idx + 1);
        }
    }
}
