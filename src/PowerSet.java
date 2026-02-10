public class PowerSet {

    static int[] elementList = {1, 2, 3};
    static final int ELEMENT_COUNT = 3;
    static boolean[] selected = new boolean[ELEMENT_COUNT];

    public static void main(String[] args) {
        powerSet(0);
    }

    public static void powerSet(int elementIdx) {
        // 1. 모든 원소를 확인했다면 종료
        if(elementIdx == ELEMENT_COUNT) {
            for(int idx = 0; idx < ELEMENT_COUNT; idx++) {
                if(selected[idx]) {
                    System.out.print(elementList[idx] + " ");
                }
            }
            System.out.println();
            return;
        }

        // 2. 원소 선택
        selected[elementIdx] = true;
        powerSet(elementIdx + 1);

        // 3. 원소 선택 x
        selected[elementIdx] = false;
        powerSet(elementIdx + 1);
    }
}
