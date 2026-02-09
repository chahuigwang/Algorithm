public class NextPermutationCombination {

    static int[] elementList = {1, 2, 3, 4};
    static final int ELEMENT_COUNT = 4;
    static final int SELECT_COUNT = 2;

    // 1이면 선택, 0이면 미선택
    static int[] select = new int[ELEMENT_COUNT];

    public static void main(String[] args) {
        // select 배열을 사전 순으로 가장 작은 상태로 설정
        for(int selectIdx = ELEMENT_COUNT - SELECT_COUNT; selectIdx < ELEMENT_COUNT; selectIdx++) { // 뒤의 SELECT_COUNT개를 1로 설정
            select[selectIdx] = 1;
        }

        do {
            printCombination();
        } while(nextPermutation());
    }

    static void printCombination() {
        for(int idx = 0; idx < ELEMENT_COUNT; idx++) {
            if(select[idx] == 1) {
                System.out.print(elementList[idx] + " ");
            }
        }
        System.out.println();
    }

    // select 배열을 nextPermutation
    static boolean nextPermutation() {
        //1. 피벗(i - 1) 찾기
        int i = ELEMENT_COUNT - 1;
        while(i > 0 && select[i-1] >= select[i]) i--;
        if(i == 0) return false;

        // 2. 교환 대상 찾기
        int j = ELEMENT_COUNT - 1;
        while(select[i-1] >= select[j]) j--;

        // 3. i-1번째 값과 j번째 값 교환
        swap(i - 1, j);

        // 4. i부터 끝까지 오름차순 정렬
        reverse(i, ELEMENT_COUNT - 1);

        return true;
    }

    // idx1번째 값과 idx2번째 값 교환
    static void swap(int idx1, int idx2) {
        int temp = select[idx1];
        select[idx1] = select[idx2];
        select[idx2] = temp;
    }

    // start부터 end까지의 값을 뒤집는다.
    static void reverse(int start, int end) {
        while(start < end) {
            swap(start++, end--);
        }
    }
}
