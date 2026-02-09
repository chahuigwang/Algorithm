import java.util.Arrays;

public class NextPermutation {

    static int[] elementList = {1, 2, 3, 4};
    static final int N = 4;

    public static void main(String[] args) {
        Arrays.sort(elementList);

        do {
            System.out.println(Arrays.toString(elementList));
        } while(nextPermutation());
    }

    static boolean nextPermutation() {
        //1. 피벗(i - 1) 찾기
        int i = N - 1;
        while(i > 0 && elementList[i-1] >= elementList[i]) i--;
        if(i == 0) return false;

        // 2. 교환 대상 찾기
        int j = N - 1;
        while(elementList[i-1] >= elementList[j]) j--;

        // 3. i-1번째 값과 j번째 값 교환
        swap(i - 1, j);

        // 4. i부터 끝까지 오름차순 정렬
        int k = N - 1;
        while(i < k) {
            swap(i++, k--);
        }

        return true;
    }

    static void swap(int idx1, int idx2) {
        int temp = elementList[idx1];
        elementList[idx1] = elementList[idx2];
        elementList[idx2] = temp;
    }
}
