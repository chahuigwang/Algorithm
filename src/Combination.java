public class Combination {

    static int[] arr = {1, 2, 3};
    static int[] comb = new int[2];
    static final int SELECT_COUNT = 2;

    public static void main(String[] args) {
        combination(0, 0);
    }

    static void combination(int selectedCount, int start) {
        if(selectedCount == SELECT_COUNT) {
            for(int x : comb) {
                System.out.print(x + " ");
            }
            System.out.println();
            return;
        }

        for(int idx = start; idx < arr.length; idx++) {
            comb[selectedCount] = arr[idx];
            combination(selectedCount + 1, idx + 1);
        }
    }
}
